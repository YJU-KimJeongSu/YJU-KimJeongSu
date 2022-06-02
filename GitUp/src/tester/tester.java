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


//버튼생성하고 패널에 추가

addButton("걸스데이",topPanel);

addButton("민아",panel);

addButton("혜리",panel);

addButton("유라",panel);

addButton("소진",panel);

 

 


//패널을 추가

add(topPanel,"North");

add(panel,"South");

cards = new Cards();

cards.add(label);

add(cards,"Center");

 

 

setVisible(true);

 

}

 


//버튼생성과동시에 이벤트실행하는 메소드

void addButton(String str,Container target){

JButton button = new JButton(str);

button.addActionListener(this);

target.add(button);

}

 

 

//이벤트 메소드

@Override

public void actionPerformed(ActionEvent e) {

if(e.getActionCommand().equals("걸스데이")){

ImageIcon girsDay = new ImageIcon("girsDay.jpg");

label.setIcon(girsDay);

}else if(e.getActionCommand().equals("민아")){

ImageIcon min_a = new ImageIcon("min_a.jpg");

label.setIcon(min_a);

}else if(e.getActionCommand().equals("혜리")){

ImageIcon heri = new ImageIcon("heri.jpg");

label.setIcon(heri);

}else if(e.getActionCommand().equals("유라")){

ImageIcon yura = new ImageIcon("yura.jpg");

label.setIcon(yura);

}else if(e.getActionCommand().equals("소진")){

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

