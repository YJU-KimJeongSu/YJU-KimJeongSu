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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Server {

	private JFrame frame;
	private JTextField portTextBox;
	private ServerSocket sSocket;
	private boolean isOpened = false;
	
	// ServerThread랑 같이 사용할 변수들. 멀티스레딩에선 ArrayList말고 Vector
	// roomList : Vector<String>으로 채팅방 기록
	// roomMember : HashMap<BufferedWriter, String>으로 버퍼라이터마다 채팅방 부여
	// nickname : HashMap<BufferedReader, String>으로 버퍼리더 하나마다 닉네임 부여
	// users : HashMap<BufferedWriter, BufferedReader> 나중에 추가 / 버퍼라이터-버퍼리더 짝지어서 사용자 찾기 편하게 하기 위해서
	static public DefaultListModel<String> model;
	static public Vector<String> roomList = new Vector<String>();
	static public HashMap<BufferedWriter, String> roomMember = new HashMap<BufferedWriter, String>();
	static public HashMap<BufferedReader, String> nickname = new HashMap<BufferedReader, String>();
	static public HashMap<BufferedWriter, BufferedReader> users = new HashMap<BufferedWriter, BufferedReader>();
	
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
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					sSocket.close();
				} catch (IOException e1) {}
			}
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					sSocket = new ServerSocket();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.setBounds(0, 0, 400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ChatServer");
		
		// 메인프레임 전체를 덮는 메인패널
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBounds(0, 0, 300, 450);
		mainPanel.setLayout(null);
		
		// 포트 입력받고 열기 위한 상단 패널
		JPanel serverOpenPanel = new JPanel();
		serverOpenPanel.setBounds(0, 0, 388, 50);
		serverOpenPanel.setBackground(Color.LIGHT_GRAY);
		serverOpenPanel.setLayout(null);
		mainPanel.add(serverOpenPanel);
		
		// 포트 라벨
		JLabel portLabel = new JLabel("Port");
		portLabel.setBounds(68, 17, 42, 15);
		serverOpenPanel.add(portLabel);
		
		// 포트 입력 받는 필드
		portTextBox = new JTextField();
		portTextBox.setText("5299");
		portTextBox.setBounds(120, 14, 96, 21);
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
		openBtn.setBounds(272, 13, 79, 23);
		serverOpenPanel.add(openBtn);
		
		
		
		// 서버 상태 보여줄 하단 패널
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 55, 378, 505);
		mainPanel.setBackground(Color.GRAY);
		mainPanel.add(scrollPane);
		
		// 서버 상태 표시하는 리스트
		model = new DefaultListModel<String>();
		JList<String> list = new JList<String>(model);
		model.addElement("test");
		scrollPane.setViewportView(list);
	}
	
	private void openBtnAction() {
		if (isOpened == false) {
			model.addElement("Server Open");
			isOpened = true;
		}
		else model.addElement("Server Is Already Opened");
		
		while (true) {
			try {
				// 연결 들어와야 다음 진행
				Thread.sleep(50); // 렉 방지. 간혹 버벅거림 원인인듯?. 지연 넣으니 버벅거림 사라짐
				sSocket.bind(new InetSocketAddress(Integer.parseInt(portTextBox.getText())));
			} catch (IOException | InterruptedException e) {
				return;
			}
			new Thread() {
				public void run() {					
					while (true) {
						try {
							Socket cSocket = sSocket.accept();
							new ServerThread(cSocket).start();
						} catch (Exception e) {}
					}
				}
			}.start();
		}
	}
}
