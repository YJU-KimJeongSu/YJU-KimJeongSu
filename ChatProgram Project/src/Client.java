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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Client {
	Socket cSocket;
	private JFrame frame;
	int autoLocationX = 5;
	BufferedReader br;
	BufferedWriter bw;

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
	public Client(Socket cSocket) {
		this.cSocket = cSocket;
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
				new MakeRoomModal(frame, new JLabel(), cSocket).setVisible(true);;
			}
		});
		makeRoomWindowBtn.setBounds(5, 5, 50, 50);
		chatRoomBarPanel.add(makeRoomWindowBtn);
		
		// 상단바 채팅방 선택 버튼들
		// 누르면 채팅방 변경
		Vector<ChatRoomBtn> chatRooms = new Vector<ChatRoomBtn>();
		chatRooms.add(new ChatRoomBtn (chatRoomBarPanel, autoLocationing()));
		chatRooms.add(new ChatRoomBtn (chatRoomBarPanel, autoLocationing()));
		chatRooms.add(new ChatRoomBtn (chatRoomBarPanel, autoLocationing()));
		chatRooms.add(new ChatRoomBtn (chatRoomBarPanel, autoLocationing()));
		
		// 채팅방
		// 채팅 입력하고, 채팅 보이게
		JPanel chattingPanel = new JPanel();
		chattingPanel.setBounds(0, 60, 588, 355);
		MainPanel.add(chattingPanel);
		chattingPanel.setLayout(null);
		
		
		
	}
	
	int autoLocationing() {
		autoLocationX += 55;
		return autoLocationX;
	}
}
