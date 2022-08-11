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
		// Server.roomList : Vector<String>���� ä�ù� ���
		// Server.roomMember : HashMap<BufferedWriter, String>���� ���۶����͸��� ä�ù� �ο�
		// Server.nickname : HashMap<BufferedReader, String>���� ���۸��� �ϳ����� �г��� �ο�
		
		// ���� ������ ����
		// LOGIN;nickname
		// CHAT;ê����
		// MAKEROOM;���̸�
		// JOINROOM;���̸�
		try {
			// �ش� ����(�����)�� ��/��� ���
			BufferedReader br = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(cSocket.getOutputStream()));
			
			String input = br.readLine(); // �α��� ������ �޾ƿ���
			Server.model.addElement("[ Data Received ] " + input);
			if (input.startsWith("LOGIN;")) {
				// �г��� �ߺ� ���� ��� �ֱ�
				input.replaceAll("LOGIN;", "");
				Server.nickname.put(br, input); // �г������� br(����ں� �Է�) ���� ����
				Server.roomMember.put(bw, null); // �α������� ���� �������� �� ����
				Server.model.addElement("LOGIN : " + input);
			}
			
			// �α��� ������ ������ ä�� ����
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
