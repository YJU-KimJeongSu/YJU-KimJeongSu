package tester;

import java.awt.CardLayout;

import java.awt.Container;

import java.awt.Graphics;

import java.awt.GridLayout;

import java.awt.Image;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

 

import javax.swing.ImageIcon;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

 

class MyWindow extends JFrame implements ActionListener{

JPanel panel,topPanel;

Cards cards;

JLabel label;

public MyWindow() {

setSize(400,586);

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 

label = new JLabel();

panel = new JPanel(new GridLayout(0,4));

topPanel = new JPanel(new GridLayout(0,1));


//��ư�����ϰ� �гο� �߰�

addButton("�ɽ�����",topPanel);

addButton("�ξ�",panel);

addButton("����",panel);

addButton("����",panel);

addButton("����",panel);

 

 


//�г��� �߰�

add(topPanel,"North");

add(panel,"South");

cards = new Cards();

cards.add(label);

add(cards,"Center");

 

 

setVisible(true);

 

}

 


//��ư���������ÿ� �̺�Ʈ�����ϴ� �޼ҵ�

void addButton(String str,Container target){

JButton button = new JButton(str);

button.addActionListener(this);

target.add(button);

}

 

 

//�̺�Ʈ �޼ҵ�

@Override

public void actionPerformed(ActionEvent e) {

if(e.getActionCommand().equals("�ɽ�����")){

ImageIcon girsDay = new ImageIcon("girsDay.jpg");

label.setIcon(girsDay);

}else if(e.getActionCommand().equals("�ξ�")){

ImageIcon min_a = new ImageIcon("min_a.jpg");

label.setIcon(min_a);

}else if(e.getActionCommand().equals("����")){

ImageIcon heri = new ImageIcon("heri.jpg");

label.setIcon(heri);

}else if(e.getActionCommand().equals("����")){

ImageIcon yura = new ImageIcon("yura.jpg");

label.setIcon(yura);

}else if(e.getActionCommand().equals("����")){

ImageIcon sojin = new ImageIcon("sojin.jpg");

label.setIcon(sojin);

}

 

}

 

 

 

 

 

 

 

class Cards extends JPanel{

CardLayout layout;

public Cards() {

layout = new CardLayout();

setLayout(layout);

}

@Override

protected void paintComponent(Graphics g) {

super.paintComponent(g);

Image img = new ImageIcon("girsDay.jpg").getImage();

g.drawImage(img, 0, 0,  null);

}

 

 

}

}

public class tester {

public static void main(String[] args) {

MyWindow d = new MyWindow();

}

 

}

