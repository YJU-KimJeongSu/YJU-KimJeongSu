import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame {
	// DB 관련
	public static Connection makeConnection() {
		String url = "jdbc:mariadb://localhost:3306/star_db";
		Connection con = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "password");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버를 찾을 수 없습니다");
		} catch (SQLException e) {
			System.err.println("연결에 실패했습니다");
		}
		
		return con;
	}
	Connection con = makeConnection();
	Statement stmt = con.createStatement();
	
	// GUI안에서 공통적으로 쓰일 요소들 
	Font font = new Font("D2Cording", Font.BOLD, 20);
	JPanel panel = new JPanel() {
		ImageIcon i = new ImageIcon("img/Login.png");	
		public void paintComponent(Graphics g) {
			g.drawImage(i.getImage(), 0, 0, 450, 675, null);
		}
	};
	
	// 로그인창 관련 요소
	JLabel login_idLabel = new JLabel("ID");
	JLabel login_passwdLabel = new JLabel("Password");
	JTextField login_id = new JTextField(10);
	JPasswordField login_passwd = new JPasswordField(15);
	JButton login_login = new JButton("Login");
	JButton login_register = new JButton("Register");
	JButton login_alter = new JButton("Alter");
	JLabel login_message = new JLabel();
	
	// 회원가입창 관련 요소
	JLabel reg_idLabel = new JLabel("ID");
	JLabel reg_passwdLabel = new JLabel("비밀번호");
	JLabel reg_passwdCheckLabel = new JLabel("비밀번호 확인");
	JTextField reg_id = new JTextField(10);
	JPasswordField reg_passwd = new JPasswordField(15);
	JPasswordField reg_passwdCheck = new JPasswordField(15);
	JButton reg_register = new JButton("회원가입");
	JButton reg_back = new JButton("뒤로");
	JLabel reg_message = new JLabel("비밀번호가 서로 다릅니다");
	JButton reg_deleteAccount = new JButton("회원탈퇴");
	
	// 회원정보 수정창 관련 요소
	JLabel alt_idLabel = new JLabel("ID");
	JLabel alt_passwdLabel = new JLabel("비밀번호");
	JLabel alt_newPasswdLabel = new JLabel("바꿀 비밀번호");
	JLabel alt_newPasswdCheckLabel = new JLabel("비밀번호 확인");
	JTextField alt_id = new JTextField(10);
	JPasswordField alt_passwd = new JPasswordField(15);
	JPasswordField alt_newPasswd = new JPasswordField(15);
	JPasswordField alt_newPasswdCheck = new JPasswordField(15);
	JButton alt_register = new JButton("변경");
	JButton alt_back = new JButton("뒤로");
	JLabel alt_message = new JLabel("비밀번호가 서로 다릅니다");
	
	// 메인프레임 생성/설정
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
		makeAlterComponents(); // 생성만 되게, 보이지는 않게
		makeRegisterComponents(); // 생성만 되게, 보이지는 않게
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
		login_alter.setBounds(175, 405, 100, 25);

		login_message.setBounds(0, 445, 450, 25);
		login_message.setFont(font);
		login_message.setForeground(Color.red);
		login_message.setHorizontalAlignment(JLabel.CENTER); // 중앙정렬
		
		panel.add(login_idLabel);
		panel.add(login_id);
		panel.add(login_passwdLabel);
		panel.add(login_passwd);
		panel.add(login_login);
		panel.add(login_register);
		panel.add(login_message);
		panel.add(login_alter);
		
		// 클릭하면 login 메소드 안에서 전부 처리
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
		
		// 클릭하면 register 창으로 화면 변경
		login_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hideLoginPage();
				showRegPage();
			}
		});
		
		// 클릭하면 Alter창으로 화면 변경
		login_alter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				hideLoginPage();
				showAltPage();
			}
		});
	}	
	private void makeRegisterComponents() {
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
		
		
		// Register 버튼 눌러야 보이도록 하기 위해서 전부 안보이게
		// 버튼 눌러야 생성되게 만드니 back했다가 다시 Register 누르면 작동 안됨
		hideRegPage();
		
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
		
		// 가입 시 발생 가능한 오류는 1.아이디 중복   2.비밀번호랑 비밀번호 확인 다름
		reg_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((reg_passwd.getText().equals(reg_passwdCheck.getText()))) {
					try {
						register(con, stmt, reg_id.getText(), reg_passwd.getText());
//						login_message.setText("회원가입이 완료되었습니다.");
					} catch (SQLException e1) {
						// 1번 오류 처리
						login_message.setText("중복된 ID입니다.");
					}
					hideRegPage();
					showLoginPage();
				}
				else { 
					// 2번 오류 처리
					reg_message.setVisible(true);
				}
	        }
		});
		
		// 그냥 원래 화면으로 돌아가기
		reg_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hideRegPage();
				showLoginPage();
	        }
		});
		
		// 계정 삭제 시 발생 가능한 오류는 1.없는 아이디 삭제   2.비밀번호랑 비밀번호 확인 다름   3.비밀번호 틀림
		reg_deleteAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((reg_passwd.getText().equals(reg_passwdCheck.getText()))) {
					try {
						deleteAccount(con, stmt, reg_id.getText(), reg_passwd.getText());
						
						hideRegPage();
						showLoginPage();
					} catch (SQLException e1) {
						// 1번 오류 처리... 하고 싶었지만 없는 아이디 삭제한다고 에러가 발생하진 않아서 작동 안됨
						// 그래서 deleteAccount 메소드에서 1번 오류 처리
						// reg_message.setText("존재하지 않는 ID입니다.");
					}
				}
				else { 
					// 2번 오류 처리
					reg_message.setVisible(true);
				}
	        }
		});
	}
	private void makeAlterComponents() {
		alt_idLabel.setBounds(150, 205, 100, 20);
		alt_idLabel.setFont(font);
		alt_idLabel.setForeground(Color.white);
		alt_id.setBounds(200, 205, 100, 20);
		
		alt_passwdLabel.setBounds(0, 245, 170, 20);
		alt_passwdLabel.setFont(font);
		alt_passwdLabel.setForeground(Color.white);
		alt_passwdLabel.setHorizontalAlignment(JLabel.RIGHT);
		alt_passwd.setBounds(200, 245, 100, 20);
		
		alt_newPasswdLabel.setBounds(0, 285, 170, 20);
		alt_newPasswdLabel.setFont(font);
		alt_newPasswdLabel.setForeground(Color.white);
		alt_newPasswdLabel.setHorizontalAlignment(JLabel.RIGHT);
		alt_newPasswd.setBounds(200, 285, 100, 20);
		
		alt_newPasswdCheckLabel.setBounds(0, 325, 170, 20);
		alt_newPasswdCheckLabel.setFont(font);
		alt_newPasswdCheckLabel.setForeground(Color.white);
		alt_newPasswdCheckLabel.setHorizontalAlignment(JLabel.RIGHT);
		alt_newPasswdCheck.setBounds(200, 325, 100, 20);
		
		alt_register.setBounds(175, 375, 100, 25);
		alt_back.setBounds(175, 415, 100, 25);
		alt_message.setBounds(0, 455, 450, 25);
		alt_message.setHorizontalAlignment(JLabel.CENTER);
		alt_message.setFont(font);
		alt_message.setForeground(Color.red);
		alt_message.setVisible(false);
		
		// Alter 버튼 눌러야 보이도록 하기 위해서 전부 안보이게
		// 버튼 눌러야 생성되게 만드니 back했다가 다시 Alter 누르면 작동 안됨
		hideAltPage();
		
		panel.add(alt_idLabel);
		panel.add(alt_id);
		panel.add(alt_passwdLabel);
		panel.add(alt_passwd);
		panel.add(alt_newPasswdLabel);
		panel.add(alt_newPasswd);
		panel.add(alt_newPasswdCheckLabel);
		panel.add(alt_newPasswdCheck);
		panel.add(alt_register);
		panel.add(alt_back);
		panel.add(alt_message);
		
		// 정보 수정 시 발생 가능한 오류는 1.비밀번호 틀림   2.없는 아이디로 로그인   3.비밀번호 확인 틀림
		alt_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (alt_newPasswd.getText().equals(alt_newPasswdCheck.getText())) {
					try {
						alterAccount(con, stmt, alt_id.getText(), alt_passwd.getText(), alt_newPasswd.getText());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					// 3번 오류 처리
					alt_message.setText("잘못된 비밀번호입니다 (비밀번호 확인)");
					alt_message.setVisible(true);
				}
				
			}
		});
		
		alt_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hideAltPage();
				showLoginPage();
			}
		});
	}
	// 로그인 시 발생 가능한 오류는 1.비밀번호 틀림   2.없는 아이디로 로그인
	private void login(Connection con, Statement stmt, String id, String password) throws SQLException {
		String s = "select password from star_login where id = '" + id + "'";
		ResultSet rs = stmt.executeQuery(s);
		
		if (rs.next()) {
			if (rs.getString("password").equals(password)) {
				setVisible(false);
				StarUI User = new StarUI(0, "User");
					// 1번 오류 처리
			} else { login_message.setText("잘못된 비밀번호입니다."); }
		}
		else {
			// 2번 오류 처리
			login_message.setText("존재하지 않는 ID입니다.");
		}
	}	
	private void register(Connection con, Statement stmt, String id, String password) throws SQLException {
		if (id.equals("") || password.equals("")) {
			login_message.setText("빈 값은 입력할 수 없습니다");
		}
		else {
			String s = "insert into star_login (id, password) values ('" + id + "', '" + password + "');";
			stmt.executeUpdate(s);
			login_message.setText("회원가입이 완료되었습니다.");
		}
		
	}	
	private void deleteAccount(Connection con, Statement stmt, String id, String password) throws SQLException {
		String s = "select password from star_login where id = '" + id + "'";
		ResultSet rs = stmt.executeQuery(s);

		if (!(rs.next())) { login_message.setText("존재하지 않는 ID입니다."); }
		else if (rs.getString("password").equals(password)) {
			String s1 = "delete from star_login where id = '" + id + "';";
			stmt.executeUpdate(s1);
			login_message.setText("회원탈퇴가 완료되었습니다.");
		}
		else {
			login_message.setText("잘못된 비밀번호입니다.");
		}
		
	}	
	private void alterAccount(Connection con, Statement stmt, String id, String password, String newPassword) throws SQLException {
		String s = "select password from star_login where id = '" + id + "'";
		ResultSet rs = stmt.executeQuery(s);
		
		if (rs.next()) {
			if (rs.getString("password").equals(password)) {
				String s1 = "update star_login set password = '" + newPassword + "' where id = '" + id + "';";
				stmt.executeUpdate(s1);
				hideAltPage();
				showLoginPage();
				login_message.setText("비밀번호 변경이 완료되었습니다");
			} else {
				// 1번 오류 처리
				alt_message.setText("잘못된 비밀번호입니다. (로그인 실패)");
				alt_message.setVisible(true);
			}
		} else {
			// 2번 오류 처리
			alt_message.setText("존재하지 않는 ID입니다.");
			alt_message.setVisible(true);
		}
	}
	private void hideLoginPage() {
		login_idLabel.setVisible(false);
		login_id.setVisible(false);
		login_passwdLabel.setVisible(false);
		login_passwd.setVisible(false);
		login_login.setVisible(false);
		login_register.setVisible(false);
		login_message.setVisible(false);
		login_message.setText("");
		login_alter.setVisible(false);
	}	
	private void showLoginPage() {
		login_idLabel.setVisible(true);
		login_id.setVisible(true);
		login_passwdLabel.setVisible(true);
		login_passwd.setVisible(true);
		login_login.setVisible(true);
		login_register.setVisible(true);
		login_message.setVisible(true);
		login_alter.setVisible(true);
	}
	private void hideRegPage() {
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
	}	
	private void showRegPage() {
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
	private void hideAltPage() {
		alt_idLabel.setVisible(false);
		alt_id.setVisible(false);
		alt_passwdLabel.setVisible(false);
		alt_passwd.setVisible(false);
		alt_newPasswdLabel.setVisible(false);
		alt_newPasswd.setVisible(false);
		alt_newPasswdCheckLabel.setVisible(false);
		alt_newPasswdCheck.setVisible(false);
		alt_register.setVisible(false);
		alt_back.setVisible(false);
		alt_message.setVisible(false);
	}
	private void showAltPage() {
		alt_idLabel.setVisible(true);
		alt_id.setVisible(true);
		alt_passwdLabel.setVisible(true);
		alt_passwd.setVisible(true);
		alt_newPasswdLabel.setVisible(true);
		alt_newPasswd.setVisible(true);
		alt_newPasswdCheckLabel.setVisible(true);
		alt_newPasswdCheck.setVisible(true);
		alt_register.setVisible(true);
		alt_back.setVisible(true);
	}
}
