import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ChatRoomBtn extends JButton {
	public ChatRoomBtn(JPanel panel, int x, String name) {
		panel.add(this);
		setBounds(x, 5, 100, 50);
		addMouseListener();
		setText(name);
	}
	
	public synchronized void addMouseListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ä�ù� ��ȯ �̺�Ʈ �ֱ�
				System.out.println("ChatRoomBtn ���콺 �̺�Ʈ �߻�");
			}
		});
	}
}
