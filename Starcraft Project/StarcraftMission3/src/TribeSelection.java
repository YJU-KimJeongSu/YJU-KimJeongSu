import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.border.*;

public class TribeSelection extends JFrame {
	JPanel panel;
	static StarUI User;
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		User = new StarUI(0, "User");
//		User.setBounds(0, 58, 640, 650);
//		User.setVisible(true);
	}
}