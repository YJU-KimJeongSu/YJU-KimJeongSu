import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
	Socket cSocket = null;

	public ServerThread(Socket cSocket) {
		Server.model.addElement("(스레드 시작)새 클라이언트 연결");
		try {
			this.cSocket = cSocket;
		} catch (Exception e) {}
	}
	
	public boolean checkNickname(String nickname) {
		boolean b = false;
		// Value에 nickname이 포함되어 있으면 true 반환
		if (Server.nickname.containsValue(nickname)) { b = true; }
		return b;
	}

	public void run() {
		// Server.roomList : Vector<String>으로 채팅방 기록
		// Server.roomMember : HashMap<BufferedWriter, String>으로 버퍼라이터마다 채팅방 부여
		// Server.nickname : HashMap<BufferedReader, String>으로 버퍼리더 하나마다 닉네임 부여

		// 받을 데이터 형식
		// LOGIN;nickname
		// CHAT;챗내용
		// MAKEROOM;방이름
		// JOINROOM;방이름
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
				if (checkNickname(input)) {
					addServerState("LoginFailed : 중복된 닉네임");
					bw.write("LoginFailed\n");
					bw.flush();
				} else {
					addServerState(input + "로그인 성공");
					Server.nickname.put(br, input);
					Server.roomMember.put(bw, null);
					bw.write("LoginSuccess\n");
					bw.flush();
					break;
				}
			}


			// 로그인 했으니 본격적 채팅 시작
			while (true) {
				input = br.readLine();
				String nickname = Server.nickname.get(br);
				Server.model.addElement("[ " + nickname + "'s Data Received ] " + input);


				switch (input) {
				}
			}
		} catch (IOException e) {

		}
	}
	
	public void addServerState(String element) {
		// 이유는 모르겠지만 문자열 변경 후 바로 model.addElemnt하면 리스트 사라짐(잠시 뒤 다시 addElement해야 나옴)
		// 대신 지연 주연 안사라짐
		try { Thread.sleep(75); }
		catch (InterruptedException e) {}
		Server.model.addElement(element);
		
	}
}
