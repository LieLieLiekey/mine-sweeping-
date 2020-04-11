package sweeping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class ChoiceInterface extends JFrame {
	JPanel topPanel;
	JPanel centerPanel;
	 static public void main(String[] args) {
//		SwingUtilities.invokeLater(ChoiceInterface::create);
		 ChoiceInterface ceshi=new ChoiceInterface();
		SwingUtilities.invokeLater(ceshi::create);
		
	}
	 public void setTopPanel() {
		 topPanel.setLayout(null);
		 topPanel.setPreferredSize(new Dimension(0,250));
		
		//添加一个label主题
		JLabel label=new JLabel();
		label.setIcon(new ImageIcon("images/Title.png"));
		label.setBounds(280,80,608,124);
		//添加一个退出Button
		JButton button=new JButton();
		button.setIcon(new ImageIcon("images/exit.png"));
		button.setBounds(1070, 60, 50, 72);
		button.addActionListener((Object)->{
			close();
		}
		);
		
		topPanel.add(label);
		topPanel.add(button);
//		lb.setBackground(Color.green);
	}
	 public JButton getEasyButton() {
		JButton button=getButton("images/easy_0.png", "images/easy_1.png", "images/easy_2.png");
		button.setPreferredSize(new Dimension(380,290));
		button.addActionListener((Object)->{
			GameProperties.row=10;
			GameProperties.col=10;
			GameProperties.bom_cnt=10;
			intoGame();
		});
		return button;
	}
	 public JButton getMidButton() {
		JButton button=getButton("images/mid_0.png", "images/mid_1.png", "images/mid_2.png");
		button.setPreferredSize(new Dimension(380,290));
		button.addActionListener((Object)->{
			GameProperties.row=20;
			GameProperties.col=15;
			GameProperties.bom_cnt=30;
			intoGame();
		});
//		button.setSize(380,290);
		return button;
	}
	 public JButton getHardButton() {
		JButton button=getButton("images/hard_0.png", "images/hard_1.png", "images/hard_2.png");
		button.setPreferredSize(new Dimension(380,290));
		button.addActionListener((Object)->{
			GameProperties.row=16;
			GameProperties.col=30;
			GameProperties.bom_cnt=80;
			intoGame();
		});
//		button.setSize(380,290);
		return button;
	}
	 public JButton getCuteButton() {
		JButton button=getButton("images/cute_0.png", "images/cute_1.png", "images/cute_2.png");
		button.setPreferredSize(new Dimension(380,290));
		button.addActionListener((Object)->{
			System.out.println("Frame::CuteButton");
			CustMize ceshiCustMize=new CustMize(this);
			ceshiCustMize.create();
		});
		return button;
	}
	 public void setCenterPanel() {
		//添加中间的按钮，按钮包含监听器
		 centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,60));
//		JButton easyButton=
		 centerPanel.add(getEasyButton());
		 centerPanel.add(getMidButton());
		 centerPanel.add(getHardButton());
		 centerPanel.add(getCuteButton());
	}
	 public void create() {
		//初始化界面
		topPanel=new JPanel();
		centerPanel=new JPanel();
		super.setLayout(new BorderLayout());
		super.setSize(1200,960);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocation(343, 36);
//		jf.addMouseListener(new ChoiceFrameMouseLisence());
		//设置没有装饰
		super.setUndecorated(true);
//		jf.setBackground(new Color(0,255,0));
		//设置透明度
		super.setOpacity(0.95f);
		setTopPanel();
		setCenterPanel();
		super.add(topPanel,BorderLayout.NORTH);
		super.add(centerPanel,BorderLayout.CENTER);
		System.out.println("oh right");
		showInterface();
		System.out.println("oh right");
//		jf.dispose();
	}
	 public JButton getButton(String s1,String s2,String s3) {
		//指定button一个默认图片，Press图片，onMouse图片
		JButton button=new JButton();
		//不显示边框
		button.setBorderPainted(false);
//		button.setBounds(100, 100, 380, 290);
		button.setIcon(new ImageIcon(s1));
		//按下
		button.setPressedIcon(new ImageIcon(s2));
		//鼠标移动到按钮上
		button.setRolloverIcon(new ImageIcon(s3));
		button.setBackground(Color.white);
		button.setOpaque(false);
		return button;
	}
	 public void close() {
		super.removeAll();
		super.dispose();
		super.setVisible(false);
	}
	 public void hindInterface() {
		super.setVisible(false);
	}
	 public void showInterface() {
		super.setVisible(true);
	}
	 public void intoGame() {
			hindInterface();
			GameBegin gameBegin=new GameBegin(this);
			gameBegin.create();
	 }
}
