import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class test extends JFrame {

   public test() {
      
      setLayout(null);
      
      Button btn = new Button("로  그  인");
      btn.setBounds(100, 100, 100, 30);
      
      Button btn2 = new Button("회 원 가 입");
      btn2.setBounds(100, 150, 100, 30);
      
      btn.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
            //new window2();
         }
      });
      btn2.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
            //new window2();
         }
      });
      add(btn);
      add(btn2);
      
      setBounds(100, 100, 300, 300);
      setVisible(true);
      setBackground(Color.red);
   }
   public static void main(String[] args) {
      new test();
   }
}