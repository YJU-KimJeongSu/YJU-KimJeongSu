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
	
	// ServerThread�� ���� ����� ������. ��Ƽ���������� ArrayList���� Vector
	// roomList : Vector<String>���� ä�ù� ���
	// roomMember : HashMap<BufferedWriter, String>���� ���۶����͸��� ä�ù� �ο�
	// nickname : HashMap<BufferedReader, String>���� ���۸��� �ϳ����� �г��� �ο�
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
		// ����������
		frame = new JFrame();
		frame.setBounds(100, 100, 310, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ���������� ��ü�� ���� �����г�
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBounds(0, 0, 300, 450);
		mainPanel.setLayout(null);
		
		// ��Ʈ �Է¹ް� ���� ���� ��� �г�
		JPanel serverOpenPanel = new JPanel();
		serverOpenPanel.setBounds(0, 0, 300, 50);
		serverOpenPanel.setBackground(Color.LIGHT_GRAY);
		serverOpenPanel.setLayout(null);
		mainPanel.add(serverOpenPanel);
		
		// ��Ʈ ��
		JLabel portLabel = new JLabel("Port");
		portLabel.setBounds(40, 17, 42, 15);
		serverOpenPanel.add(portLabel);
		
		// ��Ʈ �Է� �޴� �ʵ�
		portTextBox = new JTextField();
		portTextBox.setBounds(82, 14, 96, 21);
		serverOpenPanel.add(portTextBox);
		portTextBox.setColumns(10);
		
		// ���� ���� ��ư 
		JButton openBtn = new JButton("Open");
		openBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openBtnAction();
			}
		});
		openBtn.setBounds(211, 13, 79, 23);
		serverOpenPanel.add(openBtn);
		
		
		
		// ���� ���� ������ �ϴ� �г�
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 55, 288, 353);
		mainPanel.setBackground(Color.GRAY);
		mainPanel.add(scrollPane);
		
		// ���� ���� ǥ���ϴ� ����Ʈ
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
				// ���� ���;� ���� ����
				sSocket.bind(new InetSocketAddress(Integer.parseInt(portTextBox.getText())));
				cSocket = sSocket.accept();
			} catch (IOException e) {
				model.addElement("���� ���ῡ �����߽��ϴ�");
				break;
			}
			
			new ServerThread(cSocket).start();
		}
		
		
	}
}
