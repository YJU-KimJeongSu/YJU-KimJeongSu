import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Server {

	private JFrame frame;
	private JTextField portTextBox;
	private ServerSocket sSocket;
	private Socket cSocket;
	
	// ServerThread랑 같이 사용할 변수들. 멀티스레딩에선 ArrayList말고 Vector
	// roomList : Vector<String>으로 채팅방 기록
	// roomMember : HashMap<BufferedWriter, String>으로 버퍼라이터마다 채팅방 부여
	// nickname : HashMap<BufferedReader, String>으로 버퍼리더 하나마다 닉네임 부여
	static public DefaultListModel<String> model;
	static public Vector<String> roomList;
	static public HashMap<BufferedWriter, String> roomMember;
	static public HashMap<BufferedReader, String> nickname;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
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
	public Server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// 메인프레임
		frame = new JFrame();
		frame.setBounds(100, 100, 310, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 메인프레임 전체를 덮는 메인패널
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBounds(0, 0, 300, 450);
		mainPanel.setLayout(null);
		
		// 포트 입력받고 열기 위한 상단 패널
		JPanel serverOpenPanel = new JPanel();
		serverOpenPanel.setBounds(0, 0, 300, 50);
		serverOpenPanel.setBackground(Color.LIGHT_GRAY);
		serverOpenPanel.setLayout(null);
		mainPanel.add(serverOpenPanel);
		
		// 포트 라벨
		JLabel portLabel = new JLabel("Port");
		portLabel.setBounds(40, 17, 42, 15);
		serverOpenPanel.add(portLabel);
		
		// 포트 입력 받는 필드
		portTextBox = new JTextField();
		portTextBox.setBounds(82, 14, 96, 21);
		serverOpenPanel.add(portTextBox);
		portTextBox.setColumns(10);
		
		// 서버 열기 버튼 
		JButton openBtn = new JButton("Open");
		openBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openBtnAction();
			}
		});
		openBtn.setBounds(211, 13, 79, 23);
		serverOpenPanel.add(openBtn);
		
		
		
		// 서버 상태 보여줄 하단 패널
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 55, 288, 353);
		mainPanel.setBackground(Color.GRAY);
		mainPanel.add(scrollPane);
		
		// 서버 상태 표시하는 리스트
		model = new DefaultListModel<String>();
		JList<String> list = new JList<String>(model);
		model.addElement("test");
		model.addElement("test");
		model.addElement("test");
		model.addElement("test");
		scrollPane.setViewportView(list);
	}
	
	private void openBtnAction() {
		while (true) {
			try {
				sSocket = new ServerSocket();
				// 연결 들어와야 다음 진행
				sSocket.bind(new InetSocketAddress(Integer.parseInt(portTextBox.getText())));
				cSocket = sSocket.accept();
			} catch (IOException e) {
				model.addElement("소켓 연결에 실패했습니다");
				break;
			}
			
			new ServerThread(cSocket).start();
		}
		
		
	}
}
