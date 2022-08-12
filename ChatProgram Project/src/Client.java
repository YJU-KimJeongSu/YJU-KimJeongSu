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
	HashMap<String, ChatRoomBtn> chatRoom; // 채팅방 이름-버튼으로 짝지어서 넣기
	JTextField sendMessageTxt;
	String nowChatRoom = "";
	String myNickname;
	
	// 독립적인 클래스로 만드니 변수나 메소드 쓰기 힘들어서 그냥 내부클래스로 선언
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
//							modelAddElements(name + "채팅방에 참가하였습니다"); // 생각해보니 이거 서버에서 해야함
						} catch (IOException e1) {}
					}
					else {
						modelAddElements("[System]" + name + "채팅방에 이미 참가중입니다");
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
		// JoinedRoom;방이름
		try {
			if (!nowChatRoom.equals(roomName)) b = true;
		} catch (Exception e) {}
		
		return b;
	}
	
	// 내부 클래스 안에서는 model 접근이 안돼서 만들었음
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
					// 로그인 먼저 해야하니 Client를 실행시켜도 ClientLogin로 연결
//					ClientLogin window = new ClientLogin();
					
					// ClientLogin으로 시작하면 윈도우빌더 고장남
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
	
	// 윈도우 빌더 만들기용. 나중에 삭제
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// 생성시 초기화
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ChatClient");
		
		JPanel MainPanel = new JPanel();
		frame.getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(null);
		
		// 상단바 
		// 채팅방 만들고 채팅방 선택하기
		JPanel chatRoomBarPanel = new JPanel();
		chatRoomBarPanel.setBounds(0, 0, 588, 60);
		MainPanel.add(chatRoomBarPanel);
		chatRoomBarPanel.setLayout(null);
		chatRoomBarPanel.setBackground(new Color(190, 190, 190));;
		
		// 상단바 채팅방 만들기 버튼
		// 누르면 새로운 JFrame 창이 떠서 연결하기
		JButton makeRoomWindowBtn = new JButton();
		makeRoomWindowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 채팅방 만드는 JFream 띄우기. modal은 JDialog만 되는듯
				new MakeRoomModal(frame, new JLabel(), br, bw).setVisible(true);;
			}
		});
		makeRoomWindowBtn.setBounds(5, 5, 50, 50);
		chatRoomBarPanel.add(makeRoomWindowBtn);
		
		// 상단바 채팅방 선택 버튼들
		// 누르면 채팅방 변경
		chatRoom = new HashMap<String, ChatRoomBtn>();
//		chatRoom.put("테스트채팅방", new ChatRoomBtn(chatRoomBarPanel, autoLocationing()));
		
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
						// 클라이언트가 받을 메세지 : 채팅, 방 조인(해당 방에 사람만), 방 생성
						
						// 방 생성 시 버튼이랑 방 연결하는거 신경쓰기
						// NowRoomList;방이름:방이름:방이름:
						if (input.startsWith("NowRoomList;")) {
							input = input.replaceAll("NowRoomList;", "");
							// input = 방이름1:방이름2:...
							String[] str = input.split(":");
							for (int i = 0; i < str.length; i++) {
								if (!chatRoom.containsKey(str[i]) && !str[i].equals("")) {
									chatRoom.put(str[i], new ChatRoomBtn(chatRoomBarPanel, autoLocationing(), str[i]));
								}
							}
						}
						else if (input.startsWith("JoinRoom;")) {
							input = input.replaceAll("JoinRoom;", "");
							// input = JoinRoom;접속자_닉네임
							model.addElement("[System]" + input + "님이 채팅방에 참가하였습니다.");
						}
					} catch (IOException | InterruptedException e) {
						System.err.println("서버와 연결이 종료되었습니다.");
						break;
					}
				}

			}
		}.start();
	}
}
