import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame {
	// DB ���� �ʵ庯����
	public static Connection makeConnection() {
		String url = "jdbc:mariadb://localhost:3306/star_db";
		Connection con = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "password");
		} catch (ClassNotFoundException e) {
			System.err.println("����̹��� ã�� �� �����ϴ�");
		} catch (SQLException e) {
			System.err.println("���ῡ �����߽��ϴ�");
		}
		
		return con;
	}
	Connection con = makeConnection();
	Statement stmt = con.createStatement();
	
	// GUI�ȿ��� ���������� ���� ��ҵ� 
	Font font = new Font("D2Cording", Font.BOLD, 20);
	JPanel panel = new JPanel() {
		ImageIcon i = new ImageIcon("img/Login.png");	
		public void paintComponent(Graphics g) {
			g.drawImage(i.getImage(), 0, 0, 450, 675, null);
		}
	};
	
	// �α���â ���� ���
	JLabel login_idLabel = new JLabel("ID");
	JLabel login_passwdLabel = new JLabel("Password");
	JTextField login_id = new JTextField(10);
	JPasswordField login_passwd = new JPasswordField(15);
	JButton login_login = new JButton("Login");
	JButton login_register = new JButton("Register");
	JLabel login_message = new JLabel();
	
	// ȸ������â ���� ���
	JLabel reg_idLabel = new JLabel("ID");
	JLabel reg_passwdLabel = new JLabel("��й�ȣ");
	JLabel reg_passwdCheckLabel = new JLabel("��й�ȣ Ȯ��");
	JTextField reg_id = new JTextField(10);
	JPasswordField reg_passwd = new JPasswordField(15);
	JPasswordField reg_passwdCheck = new JPasswordField(15);
	JButton reg_register = new JButton("ȸ������");
	JButton reg_back = new JButton("�ڷ�");
	JLabel reg_message = new JLabel("��й�ȣ�� ���� �ٸ��ϴ�");
	JButton reg_deleteAccount = new JButton("ȸ��Ż��");
	
	// ���������� ����/����
	public Login() throws SQLException {		
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(200, 0, 450, 675);
		setVisible(true);
		
		panel.setSize(450, 675);
		panel.setLayout(null);
		add(panel);
		
		makeLoginComponents();
		makeRegisterComponents(); // ������ �ǰ�, �������� �ʰ�
	}
	
	private void makeLoginComponents() {
		login_idLabel.setBounds(150, 205, 100, 20);
		login_idLabel.setFont(font);
		login_idLabel.setForeground(Color.white);
		login_id.setBounds(200, 205, 100, 20);
		
		login_passwdLabel.setBounds(75, 245, 100, 20);
		login_passwdLabel.setFont(font);
		login_passwdLabel.setForeground(Color.white);
		login_passwd.setBounds(200, 245, 100, 20);
		
		login_login.setBounds(175, 325, 100, 25);
		login_register.setBounds(175, 365, 100, 25);

		login_message.setBounds(0, 415, 450, 25);
		login_message.setFont(font);
		login_message.setForeground(Color.red);
		login_message.setHorizontalAlignment(JLabel.CENTER); // �߾�����
		
		panel.add(login_idLabel);
		panel.add(login_id);
		panel.add(login_passwdLabel);
		panel.add(login_passwd);
		panel.add(login_login);
		panel.add(login_register);
		panel.add(login_message);
		
		// Ŭ���ϸ� login �޼ҵ� �ȿ��� ���� ó��
		login_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            try {
					login(con, stmt, login_id.getText(), login_passwd.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        }
		});
		
		// Ŭ���ϸ� register â���� ȭ�� ����
		login_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login_idLabel.setVisible(false);
				login_id.setVisible(false);
				login_passwdLabel.setVisible(false);
				login_passwd.setVisible(false);
				login_login.setVisible(false);
				login_register.setVisible(false);
				login_message.setVisible(false);
				login_message.setText("");
				
				reg_idLabel.setVisible(true);
				reg_id.setVisible(true);
				reg_passwdLabel.setVisible(true);
				reg_passwd.setVisible(true);
				reg_passwdCheckLabel.setVisible(true);
				reg_passwdCheck.setVisible(true);
				reg_register.setVisible(true);
				reg_back.setVisible(true);
				reg_deleteAccount.setVisible(true);
			}
		});
	}	
	private void  makeRegisterComponents() {
		reg_idLabel.setBounds(150, 205, 100, 20);
		reg_idLabel.setFont(font);
		reg_idLabel.setForeground(Color.white);
		reg_id.setBounds(200, 205, 100, 20);
		
		reg_passwdLabel.setBounds(0, 245, 170, 20);
		reg_passwdLabel.setFont(font);
		reg_passwdLabel.setForeground(Color.white);
		reg_passwdLabel.setHorizontalAlignment(JLabel.RIGHT);
		reg_passwd.setBounds(200, 245, 100, 20);
		
		reg_passwdCheckLabel.setBounds(0, 285, 170, 20);
		reg_passwdCheckLabel.setFont(font);
		reg_passwdCheckLabel.setForeground(Color.white);
		reg_passwdCheckLabel.setHorizontalAlignment(JLabel.RIGHT);
		reg_passwdCheck.setBounds(200, 285, 100, 20);
		
		reg_register.setBounds(175, 335, 100, 25);
		reg_back.setBounds(175, 375, 100, 25);
		reg_deleteAccount.setBounds(175, 455, 100, 25);
		reg_message.setBounds(0, 415, 450, 25);
		reg_message.setHorizontalAlignment(JLabel.CENTER);
		reg_message.setFont(font);
		reg_message.setForeground(Color.red);
		
		
		// Register ��ư ������ ���̵��� �ϱ� ���ؼ� ���� �Ⱥ��̰�
		// ��ư ������ �����ǰ� ����� back�ߴٰ� �ٽ� Register ������ �۵� �ȵ�
		reg_idLabel.setVisible(false);
		reg_id.setVisible(false);
		reg_passwdLabel.setVisible(false);
		reg_passwd.setVisible(false);
		reg_passwdCheckLabel.setVisible(false);
		reg_passwdCheck.setVisible(false);
		reg_register.setVisible(false);
		reg_back.setVisible(false);
		reg_message.setVisible(false);
		reg_deleteAccount.setVisible(false);
		
		panel.add(reg_idLabel);
		panel.add(reg_id);
		panel.add(reg_passwdLabel);
		panel.add(reg_passwd);
		panel.add(reg_passwdCheckLabel);
		panel.add(reg_passwdCheck);
		panel.add(reg_register);
		panel.add(reg_back);
		panel.add(reg_message);
		panel.add(reg_deleteAccount);
		
		// ���� �� �߻� ������ ������ 1.���̵� �ߺ�   2.��й�ȣ�� ��й�ȣ Ȯ�� �ٸ�
		reg_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((reg_passwd.getText().equals(reg_passwdCheck.getText()))) {
					try {
						// register �޼ҵ� �ȿ� ���� ���� ��� ����
						register(con, stmt, reg_id.getText(), reg_passwd.getText());
					} catch (SQLException e1) {
						// 1�� ���� ó��
						login_message.setText("�ߺ��� ID�Դϴ�.");
					}
					reg_idLabel.setVisible(false);
					reg_id.setVisible(false);
					reg_passwdLabel.setVisible(false);
					reg_passwd.setVisible(false);
					reg_passwdCheckLabel.setVisible(false);
					reg_passwdCheck.setVisible(false);
					reg_register.setVisible(false);
					reg_back.setVisible(false);
					reg_message.setVisible(false);
					reg_deleteAccount.setVisible(false);
					
					login_idLabel.setVisible(true);
					login_id.setVisible(true);
					login_passwdLabel.setVisible(true);
					login_passwd.setVisible(true);
					login_login.setVisible(true);
					login_register.setVisible(true);
					login_message.setVisible(true);
				}
				else { 
					// 2�� ���� ó��
					reg_message.setVisible(true);
				}
	        }
		});
		
		// �׳� ���� ȭ������ ���ư���
		reg_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reg_idLabel.setVisible(false);
				reg_id.setVisible(false);
				reg_passwdLabel.setVisible(false);
				reg_passwd.setVisible(false);
				reg_passwdCheckLabel.setVisible(false);
				reg_passwdCheck.setVisible(false);
				reg_register.setVisible(false);
				reg_back.setVisible(false);
				reg_message.setVisible(false);
				reg_deleteAccount.setVisible(false);
				
				login_idLabel.setVisible(true);
				login_id.setVisible(true);
				login_passwdLabel.setVisible(true);
				login_passwd.setVisible(true);
				login_login.setVisible(true);
				login_register.setVisible(true);
				login_message.setVisible(true);
	        }
		});
		
		// ���� ���� �� �߻� ������ ������ 1.���� ���̵� ����   2.��й�ȣ�� ��й�ȣ Ȯ�� �ٸ�
		reg_deleteAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((reg_passwd.getText().equals(reg_passwdCheck.getText()))) {
					try {
						deleteAccount(con, stmt, reg_id.getText(), reg_passwd.getText());
						
						reg_idLabel.setVisible(false);
						reg_id.setVisible(false);
						reg_passwdLabel.setVisible(false);
						reg_passwd.setVisible(false);
						reg_passwdCheckLabel.setVisible(false);
						reg_passwdCheck.setVisible(false);
						reg_register.setVisible(false);
						reg_back.setVisible(false);
						reg_message.setVisible(false);
						reg_deleteAccount.setVisible(false);

						login_idLabel.setVisible(true);
						login_id.setVisible(true);
						login_passwdLabel.setVisible(true);
						login_passwd.setVisible(true);
						login_login.setVisible(true);
						login_register.setVisible(true);
						login_message.setVisible(true);
					} catch (SQLException e1) {
						// 1�� ���� ó��... �ϰ� �;����� ���� ���̵� �����Ѵٰ� ������ �߻����� �ʾƼ� �۵� �ȵ�
						// �׷��� deleteAccount �޼ҵ忡�� 1�� ���� ó��
						// reg_message.setText("�������� �ʴ� ID�Դϴ�.");
					}
				}
				else { 
					// 2�� ���� ó��
					reg_message.setVisible(true);
				}
	        }
		});
	}
	// �α��� �� �߻� ������ ������ 1.��й�ȣ Ʋ��   2.���� ���̵�� �α���
	private void login(Connection con, Statement stmt, String id, String password) throws SQLException {
		String s = "select password from star_login where id = '" + id + "'";
		ResultSet rs = stmt.executeQuery(s);
		
		if (rs.next()) {
			if (rs.getString("password").equals(password)) {
				setVisible(false);
				StarUI User = new StarUI(0, "User");
					// 1�� ���� ó��
			} else { login_message.setText("�߸��� ��й�ȣ�Դϴ�."); }
		}
		else {
			// 2�� ���� ó��
			login_message.setText("�������� �ʴ� ID�Դϴ�.");
		}
	}
	
	private void register(Connection con, Statement stmt, String id, String password) throws SQLException {
		String s = "insert into star_login (id, password) values ('" + id + "', '" + password + "');";
		stmt.executeUpdate(s);
	}
	
	private void deleteAccount(Connection con, Statement stmt, String id, String password) throws SQLException {
		String s = "select password from star_login where id = '" + id + "'";
		ResultSet rs = stmt.executeQuery(s);
		
		// ���� 1�� ���� ó��
		if (!(rs.next())) { login_message.setText("�������� �ʴ� ID�Դϴ�."); }
		
		String s1 = "delete from star_login where id = '" + id + "';";
		stmt.executeUpdate(s1);
	}
}
