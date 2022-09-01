import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class Client {
	private JFrame frame;
	Socket cSocket;
	int autoLocationX = -45;
	int chatRoomCount = 0;
	BufferedReader br;
	BufferedWriter bw;
	DefaultListModel<String> model;
	HashMap<String, ChatRoomBtn> chatRoom; // 채팅방 이름-버튼으로 짝지어서 넣기
	JTextField sendMessageTxt;
	String nowChatRoom = "";
	String myNickname;
	JLabel chatRoomLabel;
	
	// 독립적인 클래스로 만드니 변수나 메소드 쓰기 힘들어서 그냥 내부클래스로 선언
	class ChatRoomBtn extends JButton {
		String name;
		public ChatRoomBtn(JPanel panel, int x, String name) {
//			super(name, new ImageIcon("img/chatRoomBtn.jpg"));
			panel.add(this);
			setBounds(x, 5, 100, 50);
			setHorizontalTextPosition(CENTER);
			setVerticalTextPosition(CENTER); // 이거 두개 없으면 글자가 우측으로 밀림
			addMouseListener();
			setText(name);
			setBorderPainted(false);
			setContentAreaFilled(false);
			setIcon(new ImageIcon("img/chatRoomBtn.jpg"));
//			setSelectedIcon(new ImageIcon("img/chatRoomBtnFocus.jpg")); 마우스 올렸을 떄 아닌듯
			this.name = name;
			setPressedIcon(new ImageIcon("img/chatRoomBtnClick.jpg"));
			addMouseListener(new MouseAdapter() {
				// 마우스 올렸을 때 아이콘 변경해주는 메소드가 없어서 마우스 이벤트로 적용
				@Override
				public void mouseEntered(MouseEvent e) {
					setIcon(new ImageIcon("img/chatRoomBtnFocus.jpg"));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					setIcon(new ImageIcon("img/chatRoomBtn.jpg"));
				}
			});
		}

		

		public synchronized void addMouseListener() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (canRoomJoin(getText()))  {
						try {
							bw.write("JOINROOM;" + name + "\n");
							bw.flush();
							nowChatRoom = name;
							chatRoomLabel.setText(nowChatRoom);
//							modelAddElements(name + "채팅방에 참가하였습니다"); // 생각해보니 이거 서버에서 해야함
						} catch (IOException e1) {}
					}
					else {
						modelAddElements("[System]이미 참가중인 채팅방입니다");
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
					ClientLogin window = new ClientLogin();
					
					// ClientLogin으로 시작하면 윈도우빌더 고장남
//					Client window = new Client();
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
		JButton makeRoomWindowBtn = new JButton(new ImageIcon("img/addChatRoomBtn.jpg"));
		makeRoomWindowBtn.setPressedIcon(new ImageIcon("img/addChatRoomBtnClick.jpg"));
		makeRoomWindowBtn.setBorderPainted(false);
		makeRoomWindowBtn.setContentAreaFilled(false);
		makeRoomWindowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 채팅방 만드는 JFream 띄우기. modal은 JDialog만 되는듯
				if (chatRoomCount >= 5) JOptionPane.showMessageDialog(null, "더이상 생성할 수 없습니다\n채팅방 수가 최대입니다");
				else new MakeRoomModal(frame, new JLabel(), br, bw).setVisible(true);;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				makeRoomWindowBtn.setIcon(new ImageIcon("img/addChatRoomBtnFocus.jpg"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				makeRoomWindowBtn.setIcon(new ImageIcon("img/addChatRoomBtn.jpg"));
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
		sendMessageTxt.setBounds(184, 370, 312, 35);
		MainPanel.add(sendMessageTxt);
		sendMessageTxt.setColumns(10);
		
		JButton sendBtn = new JButton(new ImageIcon("img/sendBtn.jpg"));
		sendBtn.setPressedIcon(new ImageIcon("img/sendBtnClick.jpg"));
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CHAT;내용
				try {
					// 공백은 안보내지게
					// 현재 접속중인 채팅방이 없으면 안내메세지
					if (nowChatRoom.equals("") ) {
						model.addElement("[System]현재 접속중인 채팅방이 없습니다");
						sendMessageTxt.setText("");
					}
					else if (!sendMessageTxt.getText().equals("")) {
						bw.write("CHAT;" + sendMessageTxt.getText() + "\n");
						bw.flush();
						sendMessageTxt.setText("");
					}
				} catch (IOException e1) {}
			}
			
		});
		sendBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sendBtn.setIcon(new ImageIcon("img/sendBtnFocus.jpg"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sendBtn.setIcon(new ImageIcon("img/sendBtn.jpg"));
			}
		});
		sendBtn.setBounds(506, 370, 72, 35);
		MainPanel.add(sendBtn);
		
		JLabel lblNewLabel = new JLabel("ID : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 363, 76, 22);
		MainPanel.add(lblNewLabel);
		
		JLabel nicknameLabel = new JLabel("");
		nicknameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nicknameLabel.setBounds(92, 363, 90, 22);
		nicknameLabel.setText(myNickname);
		MainPanel.add(nicknameLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uD604\uC7AC \uCC44\uD305\uBC29 :");
		lblNewLabel_1.setBounds(10, 383, 82, 22);
		MainPanel.add(lblNewLabel_1);
		
		chatRoomLabel = new JLabel((String) null);
		chatRoomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chatRoomLabel.setBounds(92, 387, 90, 22);
		MainPanel.add(chatRoomLabel);
		
		
		// 서버에서 받은 메세지 처리하는 스레드
		new Thread() {
			public void run() {
				String input = null;
				while (true) {
					try {
						Thread.sleep(10);
						input = br.readLine();
						// 클라이언트가 받을 메세지 : 채팅, 방 조인(해당 방에 사람만), 방 생성 -> 현재 모든 방 정보 받는걸로 대체
						
						// 방 생성 시 버튼이랑 방 연결하는거 신경쓰기
						// NowRoomList;방이름:방이름:방이름:
						if (input.startsWith("NowRoomList;")) {
							input = input.replaceAll("NowRoomList;", "");
							// input = 방이름1:방이름2:...
							String[] str = input.split(":");
							for (int i = 0; i < str.length; i++) {
								if (!chatRoom.containsKey(str[i]) && !str[i].equals("")) {
									chatRoom.put(str[i], new ChatRoomBtn(chatRoomBarPanel, autoLocationing(), str[i]));
									chatRoomCount++;
									System.out.println(chatRoomCount);
								}
							}
						}
						else if (input.startsWith("JoinedRoom;")) {
							input = input.replaceAll("JoinedRoom;", "");
							// input = JoinedRoom;접속자_닉네임
							model.addElement("[System]" + input + "님이 채팅방에 참가하였습니다.");
						}
						else if (input.startsWith("Chat;")) {
							// 메시지 내용은 ;이 들어갈수도 있으니, Chat;Chat;Chat;asdasd 같은 메세지가 올 수도 있음
							input = input.substring(5, input.length());
							model.addElement(input);
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
