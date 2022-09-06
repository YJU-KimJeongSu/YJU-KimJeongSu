import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MakeRoomModal extends JDialog {
	BufferedReader br;
	BufferedWriter bw;

	public MakeRoomModal(Window parent, JLabel label, BufferedReader br, BufferedWriter bw) {
		super(parent, "Modal Practice", ModalityType.APPLICATION_MODAL);

		this.br = br;
		this.bw = bw;

		setSize(300, 200);
		getContentPane().setLayout(null);
		JLabel makeRoomLabel = new JLabel("\uCC44\uD305\uBC29 \uC774\uB984");
		makeRoomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		makeRoomLabel.setFont(new Font("����", Font.PLAIN, 18));
		makeRoomLabel.setBounds(42,10,200,40);
		JTextField roomNameTxt = new JTextField(10);
		roomNameTxt.setBounds(42,60,200, 30);

		JButton makeBtn = new JButton("\uCC44\uD305\uBC29 \uC0DD\uC131");
		makeBtn.setBounds(84,114, 116, 30);
		makeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String roomName = roomNameTxt.getText();
				if (roomName.equals(""))  JOptionPane.showMessageDialog(null, "������ �Է��� �� �����ϴ�");
				else if (roomName.contains(":"))  JOptionPane.showMessageDialog(null, ":�� �Է��� �� �����ϴ�");
				else {
					try {
						bw.write("MakeRoom;" + roomName + "\n");
						bw.flush();
						// ���� ���⼭ readLine�ߴٰ� ������
						// �Ѱ��� ��ǲ��Ʈ������ ���������� ���ÿ� �޾Ƽ� �׷���
						// client���� �޾Ƽ� ó������
						dispose();
					} catch (IOException e1) {}
				}
			}

		});

		getContentPane().add(roomNameTxt);
		getContentPane().add(makeBtn);
		getContentPane().add(makeRoomLabel);
	}
}