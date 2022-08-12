import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	Socket cSocket = null;

	public ServerThread(Socket cSocket) {
		Server.model.addElement("[������ ����] �� Ŭ���̾�Ʈ ����");
		try {
			this.cSocket = cSocket;
		} catch (Exception e) {}
	}
	
	public boolean checkDuplicateNickname (String nickname) {
		boolean b = false;
		// Value�� nickname�� ���ԵǾ� ������(=�ߺ��̸�) true ��ȯ
		if (Server.nickname.containsValue(nickname)) b = true; 
		return b;
	}
	
	public boolean checkDuplicateRoomName (String roomname) {
		boolean b = false;
		
		if (Server.roomList.contains(roomname)) b = true;
		return b;
	}
	
	public synchronized void addServerState(String element) {
		// ������ �𸣰����� ���ڿ� ���� �� �ٷ� model.addElemnt�ϸ� ����Ʈ �����(��� �� �ٽ� addElement�ؾ� ����)
		// ��� ���� �ֿ� �Ȼ����
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
				
				// ���۶����� -> ���۸��� -> �г���
				tempBr = Server.users.get(tBw);
				tempNickname = Server.nickname.get(tempBr);
				addServerState(tempNickname + "���� " + message + "����");
			} catch (IOException | InterruptedException e) {}
		}
	}

	public void run() {
		// Server.roomList : Vector<String>���� ä�ù� ���
		// Server.roomMember : HashMap<BufferedWriter, String>���� ���۶����͸��� ä�ù� �ο�
		// Server.nickname : HashMap<BufferedReader, String>���� ���۸��� �ϳ����� �г��� �ο�
		// users : HashMap<BufferedWriter, BufferedReader> ���߿� �߰� / ���۶�����-���۸��� ¦��� ����� ã�� ���ϰ� �ϱ� ���ؼ�

		// ���� ������ ����. ��Ÿ�� ��ҹ��ڷ� �ð� ���� ���� ������ �����Ұ�. \n �߿�
		// LOGIN;nickname
		// CHAT;ê����
		// MAKEROOM;���̸�
		// JoinedRoom;���̸�
		
		// ���� ������ ����
		// LoginFailed
		// LoginSuccess
		// Chat;���̸�:�г���:����
		// MakeRoomFailed
		// MakeRoomSuccess
		// NowRoomList;���̸�:���̸�:���̸�:
		// JoinRoom;�г���
		
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
				if (checkDuplicateNickname(input)) {
					bw.write("LoginFailed\n");
					bw.flush();
					addServerState("LoginFailed : �ߺ��� �г��� ���� �õ�");
				} 
				else {
					Server.nickname.put(br, input);
					Server.roomMember.put(bw, null); // ä�ù濡 �ȵ��־ �ϴ� roomMember�� �ֱ�
					Server.users.put(bw, br);
					bw.write("LoginSuccess\n");
					bw.flush();
					addServerState(input + " �α��� ����");
					break;
				}
			}
			
			// �ش� ���� �г��� �޾ƿ���
			String nickname = Server.nickname.get(br);

			// ���������� ���� ä�ù� ��� �����ֱ�
			String tempMessage0 = "NowRoomList;";
			for (String str : Server.roomList) tempMessage0 += str + ":";
			bw.write(tempMessage0 + "\n");
			bw.flush();
			addServerState(nickname + "���� " + tempMessage0 + "����");

			// �α��� ������ ������ ä�� ����
			while (true) {
				// �Է� ���
				input = br.readLine();
				addServerState("[ " + nickname + "'s Data Received ] " + input);

				// ä�ù� ����
				if (input.startsWith("MakeRoom")) {
					input = input.replaceAll("MakeRoom;", "");
					// �̸� �ߺ�
					if (checkDuplicateRoomName(input)) {
						bw.write("MakeRoomFailed\n");
						bw.flush();
						addServerState(nickname + " : �ߺ��� �̸��� ä�ù� ���� �õ�");
					}
					// ����
					else {
						Server.roomList.add(input);
						bw.write("MakeRoomSuccess\n");
						bw.flush();
						addServerState(nickname + " : " + input + "ä�ù� ����");
						String tempMessage1 = "NowRoomList;";
						for (String str : Server.roomList) tempMessage1 += str + ":";
						sendToAllUser(tempMessage1);
					}
				}
				
				// ä�ù� ����
				else if (input.startsWith("JoinedRoom")) {
					input = input.replaceAll("JoinedRoom;", "");
					// �ش� ������ ä�ù� ���� �����ϰ�
					// �ش� ä�ù��� ��� ������� �޼��� �Ѹ���
					Server.roomMember.remove(bw);
					Server.roomMember.put(bw, input);
					addServerState(nickname + " : " + input + "ä�ù� ����");
					// ��� ���۶����� ��ȸ�ϸ鼭 ä�ù��� input�̶� ������ ���� �޼��� ������
					for (BufferedWriter tBw : Server.roomMember.keySet()) {
						// Server.roomMember.get(tBw)�� ���ϰ��� String�ε�
						// Server.roomMember.get(tBw).equals�� �ȸ���
						if (input.equals(Server.roomMember.get(tBw))) {
							tBw.write("JoinRoom;" + nickname + "\n");
							tBw.flush();
							// ���۶����� -> ���۸��� -> �г���
							BufferedReader tBr = Server.users.get(tBw);
							String tempMessage2 = Server.nickname.get(tBr);
							addServerState(tempMessage2 + "���� " + nickname + "�� " + input + "�� ���������� ����");
						}
					}
				}
				
				// �׳� ä��
				else if (input.startsWith("Chat")) {
					
				}
				
				// �̰� �߸� ���� �߸��Ȱ�
				else {
					System.out.println("������ ���� ����");
				}
			}
		} catch (IOException e) {

		}
	}
	
	
}
