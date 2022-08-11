import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	Socket cSocket = null;
	
	public ServerThread(Socket cSocket) {
		this.cSocket = cSocket;
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
			
			String input = br.readLine(); // 로그인 용으로 받아오기
			Server.model.addElement("[ Data Received ] " + input);
			if (input.startsWith("LOGIN;")) {
				// 닉네임 중복 방지 기능 넣기
				input.replaceAll("LOGIN;", "");
				Server.nickname.put(br, input); // 닉네임으로 br(사용자별 입력) 구분 가능
				Server.roomMember.put(bw, null); // 로그인했을 떄는 접속중인 방 없음
				Server.model.addElement("LOGIN : " + input);
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
}
