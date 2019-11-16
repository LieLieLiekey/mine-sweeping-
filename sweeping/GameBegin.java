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
public class GameBegin extends JFrame{
	public ChoiceInterface father;
	public static int[][] dir= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	public GridButton [][] grid_buttons;
	public JButton back_button;
	public GameBegin(ChoiceInterface father) {
		super();
		this.father=father;
		// TODO Auto-generated constructor stub
	}
	class BomButtonMouseLisence extends MouseAdapter{//设置Bom的鼠标监听适配器类
		public void mouseClicked(MouseEvent e) {
				System.out.println("mouseClicked!!!!");
				GridButton gridButton= (GridButton)e.getSource();
				if(gridButton.isEnabled()==false)
					return ;
				if(e.getButton()==MouseEvent.BUTTON1) 
				{//鼠标左键单击
					if(gridButton.isbom) 
						loseGame();
					else 
						openCell(gridButton.x,gridButton.y);	
				}
				else if(e.getButton()==MouseEvent.BUTTON3) 
				{//鼠标右键单击
					if(gridButton.have_flag==true)
					{
						gridButton.setInitButton();
						gridButton.have_flag=false;
					}
					else
					{
						gridButton.setFlagButton();
						gridButton.have_flag=true;
						checkWin();
					}
				}
				return ;
		}
	}
	class GridButton extends JButton{
		int x,y;
		int count;
		boolean have_flag,isbom;
		public GridButton(int x,int y) {
			super();
			have_flag=false;
			isbom=false;
			count=0;
			this.x=x;
			this.y=y;
			// TODO Auto-generated constructor stub
		}
		public void setFlagButton() {
			setButtonImage(GameProperties.Bom_len, "images/flag1.png", "images/flag1.png", "images/flag1.png");
			super.setBackground(Color.white);
			super.setOpaque(false);
		}
		public void setButtonImage(int d,String s1,String s2,String s3) {
			//指定button一个默认图片，Press图片，onMouse图片，
//			button.setBorderPainted(false);
			
			super.setIcon(getSpcImageIcon(s1,d));
			super.setPressedIcon(getSpcImageIcon(s2,d));
			super.setRolloverIcon(getSpcImageIcon(s3,d));
//			button.setOpaque(false);
		}
		public ImageIcon getSpcImageIcon(String filename,int d){
				ImageIcon icon=new ImageIcon(filename);
				Image img=icon.getImage();
				img=img.getScaledInstance(d, d, Image.SCALE_DEFAULT);
				icon.setImage(img);
				return icon;
		}
		public  void setButtonPro(int d) {
				/*设置图片，以及按钮的监听器*/
				setButtonImage(d, "images/icon_0.png", "images/icon_1.png", "images/icon_2.png");
				super.setBorderPainted(false);
				super.setOpaque(false);
				super.addMouseListener(new BomButtonMouseLisence());
		}
		public  void setInitButton() {
			// TODO Auto-generated method stub
			setButtonImage(GameProperties.Bom_len, "images/icon_0.png", "images/icon_1.png", "images/icon_2.png");
		}
		public  void setOpenIco() {
			// TODO Auto-generated method stub
			if(isbom) {
				super.setDisabledIcon(getSpcImageIcon("images/bom.png",GameProperties.Bom_len));
				return;
			}
			switch(count)//自己打开的背景是白色的
			{
			case 0:
				super.setDisabledIcon(getSpcImageIcon("images/num_0.png",GameProperties.Bom_len));
				break;
			case 1:
				super.setDisabledIcon(getSpcImageIcon("images/num_1.png",GameProperties.Bom_len));
//				setButtonImage(button, GameProperties.Bom_len, "images/num7.png", "images/num7.png", "images/num7.png");
				break;
			case 2:
				super.setDisabledIcon(getSpcImageIcon("images/num_2.png",GameProperties.Bom_len));
//				setButtonImage(button, GameProperties.Bom_len, "images/num2.png", "images/num2.png", "images/num2.png");
				break;
			case 3:
				super.setDisabledIcon(getSpcImageIcon("images/num_3.png",GameProperties.Bom_len));
//				setButtonImage(button, GameProperties.Bom_len, "images/num3.png", "images/num3.png", "images/num3.png");
				break;
			case 4:
				super.setDisabledIcon(getSpcImageIcon("images/num_4.png",GameProperties.Bom_len));
				break;
			case 5:
				super.setDisabledIcon(getSpcImageIcon("images/num_5.png",GameProperties.Bom_len));
				break;
			case 6:
				super.setDisabledIcon(getSpcImageIcon("images/num_6.png",GameProperties.Bom_len));
				break;
			case 7:
				super.setDisabledIcon(getSpcImageIcon("images/num_7.png",GameProperties.Bom_len));
				break;
			case 8:
				super.setDisabledIcon(getSpcImageIcon("images/num_8.png",GameProperties.Bom_len));
				break;
			}
		}
	}
	static public void main(String [] args) {
//		SwingUtilities.invokeLater(Game::ceshi);
	}
	static public void ceshi() {
//		ChoiceInterface.create();
//		ceshiGameBegin game_begin=new ceshiGameBegin();
//		game_begin.create();
	}
	public  void create() {
		/*根据游戏属性重新创建一个游戏，与初始的内存，指针，都无关*/
		super.setLayout(null);
		super.setSize(1200,960);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocation(343, 36); 
		//设置没有装饰
		super.setUndecorated(true);
//		jf.setBackground(new Color(0,255,0));
		//设置透明度
		
		super.setOpacity(0.95f);
		super.add(getPanel(GameProperties.row, GameProperties.col));
		super.add(getBackButton());  
		
		addBoms();
		calcNeiboBom();
		
		super.setVisible(true);
	}
	public  void checkWin() {
		// TODO Auto-generated method stub
		boolean flag=true;
		for(int i=0;i<GameProperties.row&&flag;i++) {
			for(int j=0;j<GameProperties.col&&flag;j++) {
				if(grid_buttons[i][j].isEnabled() == true)
				{
					if(grid_buttons[i][j].have_flag&&grid_buttons[i][j].isbom) continue;
					else flag=false;
				}		
			}
		}
		if(flag==true) {
			JOptionPane.showMessageDialog(this,"Yeah,你赢了！");
			exitGame();
		}
	}
	public  JPanel getPanel(int row,int col) {
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
		grid_buttons=new GridButton[GameProperties.row][GameProperties.col];
		for(int i=0;i<GameProperties.row;++i) {
			for(int j=0;j<GameProperties.col;++j) {
				grid_buttons[i][j]=new GridButton(i,j);
				grid_buttons[i][j].setButtonPro((int)r);//设置按钮的属性
				panel.add(grid_buttons[i][j]);
			}
		} 
		return panel;
	}
	public void addBoms() {
		Random rand = new Random();
		int randRow,randCol;
		for(int i=0; i<GameProperties.bom_cnt; i++) {
			randRow = rand.nextInt(GameProperties.row);
			randCol = rand.nextInt(GameProperties.col);
			if(grid_buttons[randRow][randCol].isbom) i--;
			else grid_buttons[randRow][randCol].isbom=true;
		}
	}
    public void  calcNeiboBom() {
			int count;
			for(int i=0;i<GameProperties.row;i++) {
				for(int j=0;j<GameProperties.col;j++) {
					count =0;
					if(grid_buttons[i][j].isbom) continue;
					for(int k=0;k<8;++k) {
						int x=i+dir[k][0];
						int y=j+dir[k][1];
						if(x>=0&&x<GameProperties.row&&y>=0&&y<GameProperties.col&&grid_buttons[x][y].isbom)
							count++;
					}
					grid_buttons[i][j].count = count;
				}
			}
	}
    public  void loseGame() {
		// TODO Auto-generated method stub
		for(int i=0;i<GameProperties.row;i++) 
		{
			for(int j=0;j<GameProperties.col;j++) 
			{
				if(grid_buttons[i][j].isEnabled()==false) continue;
				//被标记不管
//				if(have_flag[i][j]==true) continue;
				//未被标记 显示图片
				grid_buttons[i][j].setOpenIco();
				grid_buttons[i][j].setEnabled(false);
			}
		}
		JOptionPane.showMessageDialog(this,"error,你输了！");
		exitGame();
	}
	public  void close() {
		super.removeAll();
		super.dispose();
		super.setVisible(false);
	}
	public void openCell(int i, int j) {//保证该格子未打开且不是BOM
		// TODO Auto-generated method stub
		/*打开该格子 */
		grid_buttons[i][j].setEnabled(false);
		grid_buttons[i][j].setOpenIco();
		if(grid_buttons[i][j].count == 0) {
			for(int k=0;k<8;++k) {
				int x=i+dir[k][0];
				int y=j+dir[k][1];
				if(x>=0&&x<GameProperties.row&&y>=0&&y<GameProperties.col&&grid_buttons[x][y].isEnabled() == true&& (!grid_buttons[x][y].isbom))
					openCell(x,y);
			}
		}
	}
	public  JButton getBackButton() {
		JButton button=new JButton();
		button.setBounds(1110,900,50,50);
		button.setIcon(getSpcImageIcon("images/back.png", 50));
		button.addActionListener((Object)->{
			exitGame();
		});
		return button;
	}
	public ImageIcon getSpcImageIcon(String filename,int d)
	{
		ImageIcon icon=new ImageIcon(filename);
		Image img=icon.getImage();
		img=img.getScaledInstance(d, d, Image.SCALE_DEFAULT);
		icon.setImage(img);
		return icon;
	}
	public void exitGame() {
		close();
		father.showInterface();
	}
}