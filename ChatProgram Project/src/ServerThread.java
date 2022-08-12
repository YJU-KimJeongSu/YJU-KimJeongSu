import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	Socket cSocket = null;

	public ServerThread(Socket cSocket) {
		Server.model.addElement("[스레드 시작] 새 클라이언트 연결");
		try {
			this.cSocket = cSocket;
		} catch (Exception e) {}
	}
	
	public boolean checkDuplicateNickname (String nickname) {
		boolean b = false;
		// Value에 nickname이 포함되어 있으면(=중복이면) true 반환
		if (Server.nickname.containsValue(nickname)) b = true; 
		return b;
	}
	
	public boolean checkDuplicateRoomName (String roomname) {
		boolean b = false;
		
		if (Server.roomList.contains(roomname)) b = true;
		return b;
	}
	
	public synchronized void addServerState(String element) {
		// 이유는 모르겠지만 문자열 변경 후 바로 model.addElemnt하면 리스트 사라짐(잠시 뒤 다시 addElement해야 나옴)
		// 대신 지연 주연 안사라짐
		try { Thread.sleep(75); }
		catch (InterruptedException e) {}
		Server.model.addElement(element);
		
	}
	
	BufferedReader tempBr;
	String tempNickname;
	public synchronized void sendToAllUser(String message) {
		for (BufferedWriter tBw : Server.roomMember.keySet()) {
			try {
				Thread.sleep(50);
				tBw.write(message + "\n");
				tBw.flush();
				
				// 버퍼라이터 -> 버퍼리더 -> 닉네임
				tempBr = Server.users.get(tBw);
				tempNickname = Server.nickname.get(tempBr);
				addServerState(tempNickname + "에게 " + message + "전송");
			} catch (IOException | InterruptedException e) {}
		}
	}

	public void run() {
		// Server.roomList : Vector<String>으로 채팅방 기록
		// Server.roomMember : HashMap<BufferedWriter, String>으로 버퍼라이터마다 채팅방 부여
		// Server.nickname : HashMap<BufferedReader, String>으로 버퍼리더 하나마다 닉네임 부여
		// users : HashMap<BufferedWriter, BufferedReader> 나중에 추가 / 버퍼라이터-버퍼리더 짝지어서 사용자 찾기 편하게 하기 위해서

		// 받을 데이터 형식. 오타나 대소문자로 시간 낭비 많이 했으니 복붙할것. \n 중요
		// LOGIN;nickname
		// CHAT;챗내용
		// MAKEROOM;방이름
		// JoinedRoom;방이름
		
		// 보낼 데이터 형식
		// LoginFailed
		// LoginSuccess
		// Chat;방이름:닉네임:내용
		// MakeRoomFailed
		// MakeRoomSuccess
		// NowRoomList;방이름:방이름:방이름:
		// JoinRoom;닉네임
		
		try {
			// 해당 소켓(사용자)의 입/출력 담당
			BufferedReader br = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(cSocket.getOutputStream()));
			String input;
			
			while (true) {
				input = br.readLine(); // 로그인 용으로 받아오기
				addServerState("[ Data Received ] " + input);
				input = input.replaceAll("LOGIN;", "");
				// 현재 input에 닉네임만 들어가있음
				
				// true면 중복, false면 중복아님
				if (checkDuplicateNickname(input)) {
					bw.write("LoginFailed\n");
					bw.flush();
					addServerState("LoginFailed : 중복된 닉네임 생성 시도");
				} 
				else {
					Server.nickname.put(br, input);
					Server.roomMember.put(bw, null); // 채팅방에 안들어가있어도 일단 roomMember에 넣기
					Server.users.put(bw, br);
					bw.write("LoginSuccess\n");
					bw.flush();
					addServerState(input + " 로그인 성공");
					break;
				}
			}
			
			// 해당 유저 닉네임 받아오기
			String nickname = Server.nickname.get(br);

			// 접속했으니 현재 채팅방 목록 보여주기
			String tempMessage0 = "NowRoomList;";
			for (String str : Server.roomList) tempMessage0 += str + ":";
			bw.write(tempMessage0 + "\n");
			bw.flush();
			addServerState(nickname + "에게 " + tempMessage0 + "전송");

			// 로그인 했으니 본격적 채팅 시작
			while (true) {
				// 입력 대기
				input = br.readLine();
				addServerState("[ " + nickname + "'s Data Received ] " + input);

				// 채팅방 생성
				if (input.startsWith("MakeRoom")) {
					input = input.replaceAll("MakeRoom;", "");
					// 이름 중복
					if (checkDuplicateRoomName(input)) {
						bw.write("MakeRoomFailed\n");
						bw.flush();
						addServerState(nickname + " : 중복된 이름의 채팅방 생성 시도");
					}
					// 정상
					else {
						Server.roomList.add(input);
						bw.write("MakeRoomSuccess\n");
						bw.flush();
						addServerState(nickname + " : " + input + "채팅방 생성");
						String tempMessage1 = "NowRoomList;";
						for (String str : Server.roomList) tempMessage1 += str + ":";
						sendToAllUser(tempMessage1);
					}
				}
				
				// 채팅방 들어가기
				else if (input.startsWith("JoinedRoom")) {
					input = input.replaceAll("JoinedRoom;", "");
					// 해당 유저의 채팅방 정보 변경하고
					// 해당 채팅방의 모든 사람에게 메세지 뿌리기
					Server.roomMember.remove(bw);
					Server.roomMember.put(bw, input);
					addServerState(nickname + " : " + input + "채팅방 접속");
					// 모든 버퍼라이터 순회하면서 채팅방이 input이랑 같으면 접속 메세지 보내기
					for (BufferedWriter tBw : Server.roomMember.keySet()) {
						// Server.roomMember.get(tBw)도 리턴값이 String인데
						// Server.roomMember.get(tBw).equals는 안먹힘
						if (input.equals(Server.roomMember.get(tBw))) {
							tBw.write("JoinRoom;" + nickname + "\n");
							tBw.flush();
							// 버퍼라이터 -> 버퍼리더 -> 닉네임
							BufferedReader tBr = Server.users.get(tBw);
							String tempMessage2 = Server.nickname.get(tBr);
							addServerState(tempMessage2 + "에게 " + nickname + "이 " + input + "에 접속했음을 전달");
						}
					}
				}
				
				// 그냥 채팅
				else if (input.startsWith("Chat")) {
					
				}
				
				// 이거 뜨면 뭔가 잘못된거
				else {
					System.out.println("데이터 형식 에러");
				}
			}
		} catch (IOException e) {

		}
	}
	
	
}
