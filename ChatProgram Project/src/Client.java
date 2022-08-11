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
		// ������ �ʱ�ȭ
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		JButton makeRoom = new JButton();
		makeRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ä�ù� ����� JFream ����
				System.out.println("makeRoom ���콺 �̺�Ʈ �߻�");
			}
		});
		makeRoom.setBounds(5, 5, 50, 50);
		chatRoomBarPanel.add(makeRoom);
		
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
		chattingPanel.setBounds(0, 60, 588, 365);
		MainPanel.add(chattingPanel);
		chattingPanel.setLayout(null);
	}
	
	int autoLocationing() {
		autoLocationX += 55;
		return autoLocationX;
	}
}
