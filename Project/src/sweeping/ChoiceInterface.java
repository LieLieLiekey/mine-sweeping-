package sweeping;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
class ChoiceFrameMouseLisence implements MouseListener{
//	public void mouseClicked(MouseEvent e) {
//		System.out.println("mouse pressed");
//		if(CustMize.custframe.isVisible()==true) {
//			CustMize.close();
//		}
//	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse pressed");
		if(CustMize.custframe.isVisible()==true) {
			CustMize.close();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse pressed");
		if(CustMize.custframe.isVisible()==true) {
			CustMize.close();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse pressed");
		if(CustMize.custframe.isVisible()==true) {
			CustMize.close();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("mouse pressed");
//		if(CustMize.custframe.isVisible()==true) {
//			CustMize.close();
//		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("mouse pressed");
//		if(CustMize.custframe.isVisible()==true) {
//			CustMize.close();
//		}
	}
	
}
public class ChoiceInterface {
	public static JFrame jf;
	static public void main(String[] args) {
//		SwingUtilities.invokeLater(ChoiceInterface::create);
		SwingUtilities.invokeLater(ChoiceInterface::create);
		
	}
	static public JPanel getTopLabel() {
		JPanel panel=new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(0,250));
		
		
		JLabel label=new JLabel();
		label.setIcon(new ImageIcon("images/Title.png"));
		label.setBounds(280,80,608,124);
		
		JButton button=new JButton();
		button.setIcon(new ImageIcon("images/exit.png"));
		button.setBounds(1070, 60, 50, 72);
		button.addActionListener((Object)->{
			ChoiceInterface.close();
		}
		);
		
		panel.add(label);
		panel.add(button);
//		lb.setBackground(Color.green);
		return panel;
	}
	static public JButton getEasyButton() {
		JButton button=getButton("images/easy_0.png", "images/easy_1.png", "images/easy_2.png");
		button.setPreferredSize(new Dimension(380,290));
		button.addActionListener((Object)->{
			GameProperties.row=10;
			GameProperties.col=10;
			GameProperties.bom_cnt=10;
			if(CustMize.custframe.isVisible()==true) {
				CustMize.close();
			}
			ChoiceInterface.hind();
			Game.create();
		});
		return button;
	}
	static public JButton getMidButton() {
		JButton button=getButton("images/mid_0.png", "images/mid_1.png", "images/mid_2.png");
		button.setPreferredSize(new Dimension(380,290));
		button.addActionListener((Object)->{
			GameProperties.row=20;
			GameProperties.col=15;
			GameProperties.bom_cnt=30;
			if(CustMize.custframe.isVisible()==true) {
				CustMize.close();
			}
			ChoiceInterface.hind();
			Game.create();
		});
//		button.setSize(380,290);
		return button;
	}
	static public JButton getHardButton() {
		JButton button=getButton("images/hard_0.png", "images/hard_1.png", "images/hard_2.png");
		button.setPreferredSize(new Dimension(380,290));
		button.addActionListener((Object)->{
			GameProperties.row=16;
			GameProperties.col=30;
			GameProperties.bom_cnt=80;
			if(CustMize.custframe.isVisible()==true) {
				CustMize.close();
			}
			ChoiceInterface.hind();
			Game.create();
		});
//		button.setSize(380,290);
		return button;
	}
	static public JButton getCuteButton() {
		JButton button=getButton("images/cute_0.png", "images/cute_1.png", "images/cute_2.png");
		button.setPreferredSize(new Dimension(380,290));
		button.addActionListener((Object)->{
			System.out.println("Frame::CuteButton");
			if(CustMize.custframe.isVisible()==true)
				CustMize.close();
			else
				CustMize.create();
		});
		return button;
	}
	static public JPanel getCenterPanel() {
		//添加中间的按钮，按钮包含监听器
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,50,60));
//		JButton easyButton=
		panel.add(getEasyButton());
		panel.add(getMidButton());
		panel.add(getHardButton());
		panel.add(getCuteButton());
		return panel;
	}
	static public void create() {
		//初始化界面
		jf=new JFrame();
		jf.setLayout(new BorderLayout());
		jf.setSize(1200,960);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setLocation(343, 36);
//		jf.addMouseListener(new ChoiceFrameMouseLisence());
		//设置没有装饰
		jf.setUndecorated(true);
//		jf.setBackground(new Color(0,255,0));
		//设置透明度
		jf.setOpacity(0.95f);
		jf.add(getTopLabel(),BorderLayout.NORTH);
		jf.add(getCenterPanel(),BorderLayout.CENTER);
		show();
//		jf.dispose();
	}
	static public JButton getButton(String s1,String s2,String s3) {
		//指定button一个默认图片，Press图片，onMouse图片，
		JButton button=new JButton();
		button.setBorderPainted(false);
		button.setBounds(100, 100, 380, 290);
		button.setIcon(new ImageIcon(s1));
		button.setPressedIcon(new ImageIcon(s2));
		button.setRolloverIcon(new ImageIcon(s3));
		button.setBackground(Color.white);
		button.setOpaque(true);
		return button;
	}
	static public void close() {
		jf.removeAll();
		jf.dispose();
		jf.setVisible(false);
	}
	static public void hind() {
		jf.setVisible(false);
	}
	static public void show() {
		jf.setVisible(true);
	}
}
