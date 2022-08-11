import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ChatRoomBtn extends JButton {
	public ChatRoomBtn(JPanel panel, int x) {
		panel.add(this);
		setBounds(x, 5, 50, 50);
		addMouseListener();
	}
	
	public synchronized void addMouseListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 채팅방 전환 이벤트 넣기
				System.out.println("ChatRoomBtn 마우스 이벤트 발생");
			}
		});
	}
}
