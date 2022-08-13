import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ClientLogin {
	private Socket cSocket;
	private JFrame frame;
	private JTextField IPTxt;
	private JTextField portTxt;
	private JTextField nicknameTxt;
	private JLabel portLabel;
	private JLabel IPLabel;
	private JLabel nicknameLabel;
	private JButton connectBtn;
	private JButton startBtn;
	private BufferedWriter bw;
	private BufferedReader br;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin window = new ClientLogin();
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
	public ClientLogin() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 300, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("로그인");

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 288, 185);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		connectBtn = new JButton("Connect");
		connectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 연결 실패 후 다시 연결할려면 인스턴스 새로 만들어야함
				cSocket = null;
				cSocket = new Socket();
				try {
					// connectBtn 버튼 누르면 서버 접속
					cSocket.connect(new InetSocketAddress(IPTxt.getText(), Integer.parseInt(portTxt.getText())));
					screenChange(); // 로그인 화면으로 전환
					bw = new BufferedWriter(new OutputStreamWriter(cSocket.getOutputStream())); // 서버로부터 입력받을 준비
				} catch (NumberFormatException | IOException e1) {
					JOptionPane.showMessageDialog(null, "서버 연결 실패");
				}	

			}
		});
		connectBtn.setBounds(66, 132, 145, 23);
		panel.add(connectBtn);

		portLabel = new JLabel("Port");
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		portLabel.setBounds(10, 79, 79, 30);
		panel.add(portLabel);

		IPLabel = new JLabel("IP");
		IPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		IPLabel.setBounds(10, 32, 79, 30);
		panel.add(IPLabel);

		IPTxt = new JTextField();
		IPTxt.setText("127.0.0.1");
		IPTxt.setBounds(122, 37, 132, 21);
		panel.add(IPTxt);
		IPTxt.setColumns(10);

		portTxt = new JTextField();
		portTxt.setText("5299");
		portTxt.setColumns(10);
		portTxt.setBounds(122, 84, 132, 21);
		panel.add(portTxt);
		
		
		
		nicknameLabel = new JLabel("ID");
		nicknameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nicknameLabel.setBounds(10, 57, 79, 30);
		nicknameLabel.setVisible(false);
		panel.add(nicknameLabel);
		
		nicknameTxt = new JTextField();
		nicknameTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (nicknameTxt.getText().equals("ID (닉네임) 입력")) nicknameTxt.setText("");
			}
		});
		nicknameTxt.setText("ID (\uB2C9\uB124\uC784) \uC785\uB825");
		nicknameTxt.setColumns(10);
		nicknameTxt.setBounds(122, 62, 132, 21);
		nicknameTxt.setVisible(false);
		panel.add(nicknameTxt);

		startBtn = new JButton("Start");
		startBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					// Start 버튼 누르면 현재 아이디 정보 서버로 보내기
					if (nicknameTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "공백은 입력할 수 없습니다");
					}
					else if (nicknameTxt.getText().contains(":")) {
						JOptionPane.showMessageDialog(null, ":은 입력할 수 없습니다");
					}
					else if (nicknameTxt.getText().equals("ID (닉네임) 입력")) {
						nicknameTxt.setText("");
						nicknameTxt.grabFocus();
					}
					else {
						bw.write("LOGIN;");
						bw.write(nicknameTxt.getText());
						bw.write("\n");
						bw.flush();
						String nickname = nicknameTxt.getText();

						// 서버로부터 아이디 중복인지 신호 받아서 성공/실패 확인
						if (isLoginSuccess()) {
							new Client(cSocket, nickname);
							frame.dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "로그인 실패\nerror : 중복된 닉네임");
						}
					}
					
				} catch (IOException e1) {}
			}
		});
		startBtn.setBounds(66, 115, 145, 23);
		startBtn.setVisible(false);
		panel.add(startBtn);
	}
	
	private void screenChange() {
		portLabel.setVisible(false);
		portTxt.setVisible(false);
		IPLabel.setVisible(false);
		IPTxt.setVisible(false);
		connectBtn.setVisible(false);
		
		nicknameLabel.setVisible(true);
		nicknameTxt.setVisible(true);
		startBtn.setVisible(true);
	}
	
	private boolean isLoginSuccess() {
		boolean b = true;
		try {
			br = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			String t = br.readLine();
			if (t.equals("LoginFailed")) b = false;
		}
		catch (IOException e) {}
		
		return b;
	}
}
