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
	
	// ServerThread�� ���� ����� ������. ��Ƽ���������� ArrayList���� Vector
	// roomList : Vector<String>���� ä�ù� ���
	// roomMember : HashMap<BufferedWriter, String>���� ���۶����͸��� ä�ù� �ο�
	// nickname : HashMap<BufferedReader, String>���� ���۸��� �ϳ����� �г��� �ο�
	// users : HashMap<BufferedWriter, BufferedReader> ���߿� �߰� / ���۶�����-���۸��� ¦��� ����� ã�� ���ϰ� �ϱ� ���ؼ�
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
		// ����������
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
		
		// ���������� ��ü�� ���� �����г�
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBounds(0, 0, 300, 450);
		mainPanel.setLayout(null);
		
		// ��Ʈ �Է¹ް� ���� ���� ��� �г�
		JPanel serverOpenPanel = new JPanel();
		serverOpenPanel.setBounds(0, 0, 388, 50);
		serverOpenPanel.setBackground(Color.LIGHT_GRAY);
		serverOpenPanel.setLayout(null);
		mainPanel.add(serverOpenPanel);
		
		// ��Ʈ ��
		JLabel portLabel = new JLabel("Port");
		portLabel.setBounds(68, 17, 42, 15);
		serverOpenPanel.add(portLabel);
		
		// ��Ʈ �Է� �޴� �ʵ�
		portTextBox = new JTextField();
		portTextBox.setText("5299");
		portTextBox.setBounds(120, 14, 96, 21);
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
		openBtn.setBounds(272, 13, 79, 23);
		serverOpenPanel.add(openBtn);
		
		
		
		// ���� ���� ������ �ϴ� �г�
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 55, 378, 505);
		mainPanel.setBackground(Color.GRAY);
		mainPanel.add(scrollPane);
		
		// ���� ���� ǥ���ϴ� ����Ʈ
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
				// ���� ���;� ���� ����
				Thread.sleep(50); // �� ����. ��Ȥ �����Ÿ� �����ε�?. ���� ������ �����Ÿ� �����
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
