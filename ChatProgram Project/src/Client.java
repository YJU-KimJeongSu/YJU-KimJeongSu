import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class Client {
	private JFrame frame;
	Socket cSocket;
	int autoLocationX = -45;
	BufferedReader br;
	BufferedWriter bw;
	DefaultListModel<String> model;
	HashMap<String, ChatRoomBtn> chatRoom; // ä�ù� �̸�-��ư���� ¦��� �ֱ�
	JTextField sendMessageTxt;
	String nowChatRoom = "";
	String myNickname;
	
	// �������� Ŭ������ ����� ������ �޼ҵ� ���� ���� �׳� ����Ŭ������ ����
	class ChatRoomBtn extends JButton {
		String name;
		public ChatRoomBtn(JPanel panel, int x, String name) {
			panel.add(this);
			setBounds(x, 5, 100, 50);
			addMouseListener();
			setText(name);
			this.name = name;
		}

		public synchronized void addMouseListener() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (canRoomJoin(getText()))  {
						try {
							bw.write("JoinedRoom;" + name + "\n");
							bw.flush();
							nowChatRoom = name;
//							modelAddElements(name + "ä�ù濡 �����Ͽ����ϴ�"); // �����غ��� �̰� �������� �ؾ���
						} catch (IOException e1) {}
					}
					else {
						modelAddElements("[System]" + name + "ä�ù濡 �̹� �������Դϴ�");
					}
				}
			});
		}
	}
	
	int autoLocationing() {
		autoLocationX += 105;
		return autoLocationX;
	}
	
	boolean canRoomJoin(String roomName) {
		boolean b = false;
		// JoinedRoom;���̸�
		try {
			if (!nowChatRoom.equals(roomName)) b = true;
		} catch (Exception e) {}
		
		return b;
	}
	
	// ���� Ŭ���� �ȿ����� model ������ �ȵż� �������
	void modelAddElements(String str) {
		model.addElement(str);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					// �α��� ���� �ؾ��ϴ� Client�� ������ѵ� ClientLogin�� ����
//					ClientLogin window = new ClientLogin();
					
					// ClientLogin���� �����ϸ� ��������� ���峲
					Client window = new Client();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client(Socket cSocket, String nickname) {
		this.cSocket = cSocket;
		this.myNickname = nickname;
		try {
			br = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(cSocket.getOutputStream()));
		} catch (IOException e) {}
		initialize();
	}
	
	// ������ ���� ������. ���߿� ����
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// ������ �ʱ�ȭ
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ChatClient");
		
		JPanel MainPanel = new JPanel();
		frame.getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(null);
		
		// ��ܹ� 
		// ä�ù� ����� ä�ù� �����ϱ�
		JPanel chatRoomBarPanel = new JPanel();
		chatRoomBarPanel.setBounds(0, 0, 588, 60);
		MainPanel.add(chatRoomBarPanel);
		chatRoomBarPanel.setLayout(null);
		chatRoomBarPanel.setBackground(new Color(190, 190, 190));;
		
		// ��ܹ� ä�ù� ����� ��ư
		// ������ ���ο� JFrame â�� ���� �����ϱ�
		JButton makeRoomWindowBtn = new JButton();
		makeRoomWindowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ä�ù� ����� JFream ����. modal�� JDialog�� �Ǵµ�
				new MakeRoomModal(frame, new JLabel(), br, bw).setVisible(true);;
			}
		});
		makeRoomWindowBtn.setBounds(5, 5, 50, 50);
		chatRoomBarPanel.add(makeRoomWindowBtn);
		
		// ��ܹ� ä�ù� ���� ��ư��
		// ������ ä�ù� ����
		chatRoom = new HashMap<String, ChatRoomBtn>();
//		chatRoom.put("�׽�Ʈä�ù�", new ChatRoomBtn(chatRoomBarPanel, autoLocationing()));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 60, 588, 300);
		MainPanel.add(scrollPane);
		
		model = new DefaultListModel<String>();
		JList<String> chattingList = new JList<String>(model);
		scrollPane.setViewportView(chattingList);
		
		sendMessageTxt = new JTextField();
		sendMessageTxt.setBounds(152, 370, 344, 35);
		MainPanel.add(sendMessageTxt);
		sendMessageTxt.setColumns(10);
		
		JButton sendBtn = new JButton("\uC804\uC1A1");
		sendBtn.setBounds(506, 370, 72, 35);
		MainPanel.add(sendBtn);
		
		JLabel lblNewLabel = new JLabel("ID : ");
		lblNewLabel.setBounds(10, 380, 30, 15);
		MainPanel.add(lblNewLabel);
		
		JLabel nicknameLabel = new JLabel("");
		nicknameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nicknameLabel.setBounds(36, 376, 106, 22);
		nicknameLabel.setText(myNickname);
		MainPanel.add(nicknameLabel);
		
		new Thread() {
			public void run() {
				String input = null;
				while (true) {
					try {
						Thread.sleep(10);
						input = br.readLine();
						// Ŭ���̾�Ʈ�� ���� �޼��� : ä��, �� ����(�ش� �濡 �����), �� ����
						
						// �� ���� �� ��ư�̶� �� �����ϴ°� �Ű澲��
						// NowRoomList;���̸�:���̸�:���̸�:
						if (input.startsWith("NowRoomList;")) {
							input = input.replaceAll("NowRoomList;", "");
							// input = ���̸�1:���̸�2:...
							String[] str = input.split(":");
							for (int i = 0; i < str.length; i++) {
								if (!chatRoom.containsKey(str[i]) && !str[i].equals("")) {
									chatRoom.put(str[i], new ChatRoomBtn(chatRoomBarPanel, autoLocationing(), str[i]));
								}
							}
						}
						else if (input.startsWith("JoinRoom;")) {
							input = input.replaceAll("JoinRoom;", "");
							// input = JoinRoom;������_�г���
							model.addElement("[System]" + input + "���� ä�ù濡 �����Ͽ����ϴ�.");
						}
					} catch (IOException | InterruptedException e) {
						System.err.println("������ ������ ����Ǿ����ϴ�.");
						break;
					}
				}

			}
		}.start();
	}
}
