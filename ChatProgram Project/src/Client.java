import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client {

	private JFrame frame;
	int autoLocationX = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		JButton makeRoom = new JButton();
		makeRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 채팅방 만드는 JFream 띄우기
				System.out.println("makeRoom 마우스 이벤트 발생");
			}
		});
		makeRoom.setBounds(5, 5, 50, 50);
		chatRoomBarPanel.add(makeRoom);
		
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
		chattingPanel.setBounds(0, 60, 588, 365);
		MainPanel.add(chattingPanel);
		chattingPanel.setLayout(null);
	}
	
	int autoLocationing() {
		autoLocationX += 55;
		return autoLocationX;
	}
}
