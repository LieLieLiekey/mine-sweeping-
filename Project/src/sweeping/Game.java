package sweeping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;

public class Game {
	public static JFrame jf;
	public static JButton[][] buttons ;
	public static int[][] dir= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	public static int[][] counts;
	public static boolean[][] have_flag;
	BomButtonMouseLisence bomButtonMouseLisence=new BomButtonMouseLisence();
	public class BomButtonMouseLisence extends MouseAdapter{//设置Bom的鼠标监听
		public void mouseClicked(MouseEvent e) {
				System.out.println("mouseClicked!!!!");
				JButton button = (JButton)e.getSource();
				if(button.isEnabled()==false)
					return ;
				
				for(int i=0;i<GameProperties.row;i++) 
				{
					for(int j=0;j<GameProperties.col;j++) 
					{
						if(button.equals(buttons[i][j])) 
						{
							if(e.getButton()==MouseEvent.BUTTON1) 
							{//鼠标左键单击
								if(Game.counts[i][j] == GameProperties.BOMCODE) 
									loseGame();
								else 
									openCell(i,j);	
							}
							else if(e.getButton()==MouseEvent.BUTTON3) 
							{//鼠标右键单击
								if(have_flag[i][j]==true)
								{
									setInitButton(buttons[i][j]);
									have_flag[i][j]=false;
								}
								else
								{
									setFlagButton(buttons[i][j]);
									have_flag[i][j]=true;
									checkWin();
								}
							}
							return ;
						}
						
					}
				}
		}
	}
	static public void main(String [] args) {
		SwingUtilities.invokeLater(Game::ceshi);
	}
	public void setFlagButton(JButton button) {
		// TODO Auto-generated method stub
		setButtonImage(button, GameProperties.Bom_len, "images/flag1.png", "images/flag1.png", "images/flag1.png");
		button.setBackground(Color.white);
		button.setOpaque(false);
		
	}
	public static void setInitButton(JButton button) {
		// TODO Auto-generated method stub
		setButtonImage(button, GameProperties.Bom_len, "images/icon_0.png", "images/icon_1.png", "images/icon_2.png");
	}
	public static void checkWin() {
		// TODO Auto-generated method stub
		boolean flag=true;
		for(int i=0;i<GameProperties.row&&flag;i++) {
			for(int j=0;j<GameProperties.col&&flag;j++) {
				if(buttons[i][j].isEnabled() == true)
				{
					if(have_flag[i][j]==true&&counts[i][j]==GameProperties.BOMCODE) continue;
					else flag=false;
				}		
			}
		}
		if(flag==true)
			JOptionPane.showMessageDialog(jf,"Yeah,你赢了！");	
	}
	public static void openCell(int i, int j) {//保证该格子未打开且不是BOM
		// TODO Auto-generated method stub
		/*打开该格子 */
		buttons[i][j].setEnabled(false);
		setOpenIco(buttons[i][j],counts[i][j]);
		if(counts[i][j] == 0) {
			for(int k=0;k<8;++k) {
				int x=i+dir[k][0];
				int y=j+dir[k][1];
				if(x>=0&&x<GameProperties.row&&y>=0&&y<GameProperties.col&&buttons[x][y].isEnabled() == true&&counts[i][j]!=GameProperties.BOMCODE)
					openCell(x,y);
			}
		}
	}
	public static void setOpenIco(JButton button, int cnt) {
		// TODO Auto-generated method stub
		switch(cnt)//自己打开的背景是白色的
		{
		case 0:
			button.setDisabledIcon(getSpcImageIcon("images/num_0.png",GameProperties.Bom_len));
			break;
		case 1:
			button.setDisabledIcon(getSpcImageIcon("images/num_1.png",GameProperties.Bom_len));
//			setButtonImage(button, GameProperties.Bom_len, "images/num7.png", "images/num7.png", "images/num7.png");
			break;
		case 2:
			button.setDisabledIcon(getSpcImageIcon("images/num_2.png",GameProperties.Bom_len));
//			setButtonImage(button, GameProperties.Bom_len, "images/num2.png", "images/num2.png", "images/num2.png");
			break;
		case 3:
			button.setDisabledIcon(getSpcImageIcon("images/num_3.png",GameProperties.Bom_len));
//			setButtonImage(button, GameProperties.Bom_len, "images/num3.png", "images/num3.png", "images/num3.png");
			break;
		case 4:
			button.setDisabledIcon(getSpcImageIcon("images/num_4.png",GameProperties.Bom_len));
			break;
		case 5:
			button.setDisabledIcon(getSpcImageIcon("images/num_5.png",GameProperties.Bom_len));
			break;
		case 6:
			button.setDisabledIcon(getSpcImageIcon("images/num_6.png",GameProperties.Bom_len));
			break;
		case 7:
			button.setDisabledIcon(getSpcImageIcon("images/num_7.png",GameProperties.Bom_len));
			break;
		case 8:
			button.setDisabledIcon(getSpcImageIcon("images/num_8.png",GameProperties.Bom_len));
			break;
		case 10:
			button.setDisabledIcon(getSpcImageIcon("images/bom.png",GameProperties.Bom_len));
			break;
		}
	}
	public static void loseGame() {
		// TODO Auto-generated method stub
		for(int i=0;i<GameProperties.row;i++) 
		{
			for(int j=0;j<GameProperties.col;j++) 
			{
				if(buttons[i][j].isEnabled()==false) continue;
				//被标记不管
//				if(have_flag[i][j]==true) continue;
				//未被标记 显示图片
				setOpenIco(buttons[i][j],counts[i][j]);
				buttons[i][j].setEnabled(false);
			}
		}
		JOptionPane.showMessageDialog(jf,"error,你输了！");
		close();
		ChoiceInterface.show();
//		JDialog dialog=new JDialog(jf,"You Losed");
//		dialog.setLayout(new FlowLayout(FlowLayout.CENTER,100,50));
//		dialog.setSize(1200,200);
//		dialog.setModal(true);
//		dialog.setLocation(343, 450);
//		dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
//		dialog.setVisible(true);
//		JButton  button=new JButton();
//		button.setText("enter");
//		button.addActionListener((Object)->{
//			dialog.dispose();
//			dialog.setVisible(false);
//			Game.close();
//			ChoiceInterface.show();
//			
//		}
//		);
//		dialog.setResizable(false);
		//设置没有装饰
//		jf.setBackground(new Color(0,255,0));
		//设置透明度
		
	}
	static public void ceshi() {
		ChoiceInterface.create();
		create();
	}
	static public ImageIcon getSpcImageIcon(String filename,int d)
	{
		ImageIcon icon=new ImageIcon(filename);
		Image img=icon.getImage();
		img=img.getScaledInstance(d, d, Image.SCALE_DEFAULT);
		icon.setImage(img);
		return icon;
	}
	static public JButton setButtonImage(JButton button,int d,String s1,String s2,String s3) {
		//指定button一个默认图片，Press图片，onMouse图片，
//		button.setBorderPainted(false);
		
		button.setIcon(getSpcImageIcon(s1,d));
		button.setPressedIcon(getSpcImageIcon(s2,d));
		button.setRolloverIcon(getSpcImageIcon(s3,d));
//		button.setOpaque(false);
		return button;
	}
	static public void addBom() {
		Random rand = new Random();
		int randRow,randCol;
		for(int i=0; i<GameProperties.bom_cnt; i++) {
			randRow = rand.nextInt(GameProperties.row);
			randCol = rand.nextInt(GameProperties.col);
			if(counts[randRow][randCol] == GameProperties.BOMCODE) 
			{
				i--;
			} else 
			{
			counts[randRow][randCol] = GameProperties.BOMCODE;
			}
		}
	}
	static public void  calcNeiboBom() {
		int count;
		for(int i=0;i<GameProperties.row;i++) {
			for(int j=0;j<GameProperties.col;j++) {
				count =0;
				if(counts[i][j] == GameProperties.BOMCODE) continue;
				for(int k=0;k<8;++k) {
					int x=i+dir[k][0];
					int y=j+dir[k][1];
					if(x>=0&&x<GameProperties.row&&y>=0&&y<GameProperties.col&&counts[x][y]==GameProperties.BOMCODE)
						count++;
				}
				counts[i][j] = count;
			}
		}
	}
	public static void setButtonPro(JButton button,int d) {
		/*设置图片，以及按钮的监听器*/
		setButtonImage(button, d, "images/icon_0.png", "images/icon_1.png", "images/icon_2.png");
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.addMouseListener((new Game()).bomButtonMouseLisence);
	}
//	public static JPanel getCenterPanel() {
//		buttons=new JButton[GameProperties.row][GameProperties.col];
//		counts= new int[GameProperties.row][GameProperties.col];
//		JPanel panel=new JPanel();
//		panel.setLayout(new GridLayout(GameProperties.row,GameProperties.col));
//		for(int i=0;i<GameProperties.row;++i) {
//			for(int j=0;j<GameProperties.col;++j) {
//				buttons[i][j]=new JButton();
//				counts[i][j]=0;
//				setButtonPro(buttons[i][j]);
//				panel.add(buttons[i][j]);
//			}
//		}
//		/*设置中间Panel的属性*/
////		panel.setPreferredSize(new Dimension(0,250));
//		return panel;
//	}
//	public static JPanel getDownPanel() {
//		JPanel panel=new JPanel();
////		panel
//		panel.setPreferredSize(new Dimension(0,110));
//		return panel;
//	}
	public static JButton getBackButton() {
		JButton button=new JButton();
		button.setBounds(1110,900,50,50);
		button.addActionListener((Object)->{
		Game.close();
		ChoiceInterface.show();
		});
		button.setIcon(getSpcImageIcon("images/back.png", 50));
		
		
		return button;
	}
	public static JPanel getPanel(int row,int col) {
		/*根据所需的row和col 计算出panel， panel包含Bounds，是一个gridlayout，放对应的按钮*/
		double r=Math.min(GameProperties.game_width/(double)col,GameProperties.game_higth/(double)row);
		double width=col*r,higth=row*r;
		double begin_x=(GameProperties.game_width-width)/2.0f;
		double begin_y=(GameProperties.game_higth-higth)/2.0f;
		JPanel panel=new JPanel();
		System.out.println("x:"+begin_x+" y:"+begin_y+" r:"+r);
		panel.setBounds(60+(int)begin_x,100+(int)begin_y,(int)width,(int)higth);
		panel.setLayout(new GridLayout(row,col));
		GameProperties.Bom_len=(int)r;
		buttons=new JButton[GameProperties.row][GameProperties.col];
		counts= new int[GameProperties.row][GameProperties.col];
		have_flag=new boolean[GameProperties.row][GameProperties.col];
		
		for(int i=0;i<GameProperties.row;++i) {
			for(int j=0;j<GameProperties.col;++j) {
				
				buttons[i][j]=new JButton();
				counts[i][j]=0;
				have_flag[i][j]=false;
				setButtonPro(buttons[i][j],(int)r);//设置按钮的属性
				panel.add(buttons[i][j]);
			}
		} 
		return panel;
	}
	public static void create() {
		/*根据游戏属性重新创建一个游戏，与初始的内存，指针，都无关*/
		jf=new JFrame();
		jf.setLayout(null);
		jf.setSize(1200,960);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setLocation(343, 36); 
		//设置没有装饰
		jf.setUndecorated(true);
//		jf.setBackground(new Color(0,255,0));
		//设置透明度
		
		jf.setOpacity(0.95f);
		jf.add(getPanel(GameProperties.row, GameProperties.col));
		jf.add(getBackButton());  
		
		addBom();
		calcNeiboBom();
		
		jf.setVisible(true);
		
	}
	public static void close() {
		jf.removeAll();
		jf.dispose();
		jf.setVisible(false);
	}
}
