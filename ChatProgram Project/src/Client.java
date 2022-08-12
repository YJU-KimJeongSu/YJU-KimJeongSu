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
	public Client(Socket cSocket) {
		this.cSocket = cSocket;
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
				new MakeRoomModal(frame, new JLabel(), cSocket).setVisible(true);;
			}
		});
		makeRoomWindowBtn.setBounds(5, 5, 50, 50);
		chatRoomBarPanel.add(makeRoomWindowBtn);
		
		// ��ܹ� ä�ù� ���� ��ư��
		// ������ ä�ù� ����
		Vector<ChatRoomBtn> chatRooms = new Vector<ChatRoomBtn>();
		chatRooms.add(new ChatRoomBtn (chatRoomBarPanel, autoLocationing()));
		chatRooms.add(new ChatRoomBtn (chatRoomBarPanel, autoLocationing()));
		chatRooms.add(new ChatRoomBtn (chatRoomBarPanel, autoLocationing()));
		chatRooms.add(new ChatRoomBtn (chatRoomBarPanel, autoLocationing()));
		
		// ä�ù�
		// ä�� �Է��ϰ�, ä�� ���̰�
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
