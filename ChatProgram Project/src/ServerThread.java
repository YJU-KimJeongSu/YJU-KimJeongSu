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
		Server.model.addElement("(������ ����)�� Ŭ���̾�Ʈ ����");
		try {
			this.cSocket = cSocket;
		} catch (Exception e) {}
	}
	
	public boolean checkNickname(String nickname) {
		boolean b = false;
		// Value�� nickname�� ���ԵǾ� ������ true ��ȯ
		if (Server.nickname.containsValue(nickname)) { b = true; }
		return b;
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
			String input;
			
			while (true) {
				input = br.readLine(); // �α��� ������ �޾ƿ���
				addServerState("[ Data Received ] " + input);
				
				input = input.replaceAll("LOGIN;", "");
				// ���� input�� �г��Ӹ� ������
				
				// true�� �ߺ�, false�� �ߺ��ƴ�
				if (checkNickname(input)) {
					addServerState("LoginFailed : �ߺ��� �г���");
					bw.write("LoginFailed\n");
					bw.flush();
				} else {
					addServerState(input + "�α��� ����");
					Server.nickname.put(br, input);
					Server.roomMember.put(bw, null);
					bw.write("LoginSuccess\n");
					bw.flush();
					break;
				}
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
	
	public void addServerState(String element) {
		// ������ �𸣰����� ���ڿ� ���� �� �ٷ� model.addElemnt�ϸ� ����Ʈ �����(��� �� �ٽ� addElement�ؾ� ����)
		// ��� ���� �ֿ� �Ȼ����
		try { Thread.sleep(75); }
		catch (InterruptedException e) {}
		Server.model.addElement(element);
		
	}
}
