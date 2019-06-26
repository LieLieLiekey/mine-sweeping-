
package saolei0;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

 class Saolei implements MouseListener,ActionListener{
	JPanel top_panel=new JPanel();
	JFrame frame = new JFrame("扫雷");
	int[][] dir= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	@SuppressWarnings("rawtypes")
	JComboBox combobox_list = new JComboBox();//下拉列表
	JButton reset_button = new JButton("重新开始");
	Container container = new Container();
	
	//游戏数据结构
	SaoLeiConstant SaoLeiConstant = new SaoLeiConstant();
	JButton[][] buttons=new JButton[SaoLeiConstant.row][SaoLeiConstant.col];//定义按钮
	int[][] counts=new int[SaoLeiConstant.row][SaoLeiConstant.col] ;//定义整型数组保存按钮下方雷数
	public void init(int row,int col) {
		counts=new int[row][col];
		buttons=new JButton[row][col];
		container.removeAll();
		container.setLayout(new GridLayout(row,col));
		for(int i=0;i<row;++i) {
			for(int j=0;j<col;++j) {
				JButton button = new JButton();
				button.setBackground(Color.white);
				button.setOpaque(true);
				button.addActionListener(this);
				button.addMouseListener((MouseListener) this);
				buttons[i][j] = button;
				container.add(button);
			}
		}
		container.setVisible(false);
		container.setVisible(true);
	}
	//创建构造方法
	public Saolei() {
		//初始化dir
		SaoLeiConstant.is_regame=0;
		//显示窗口
		frame.setSize(600,700);//600*700
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
//		init(game_value.row,game_value.col);
		//添加重置、选择难度按钮
		addtopButton();
		
		//添加雷区按钮
		addButtons();
		
		//埋雷
		addLei();
		
		//添加雷的计数
		calcNeiboLei();
		
		frame.setVisible(true);
	}

	void addtopButton() {
		top_panel.removeAll();
		top_panel.add(reset_button);
		reset_button.setBackground(Color.green);
		reset_button.setOpaque(true);
		reset_button.addActionListener(this);
		//combobox.addItem("选择难度");
		combobox_list.addItem("新手难度");
		combobox_list.addItem("初级难度");
		combobox_list.addItem("中级难度");
		combobox_list.addItem("高级难度");
		combobox_list.addItem("大师难度");
		combobox_list.setBackground(Color.GREEN);
		combobox_list.setOpaque(true);
		combobox_list.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {

				String item = e.getItem().toString(); 
				if(item == "新手难度") {
					SaoLeiConstant.row=20;
					SaoLeiConstant.col=20;
					SaoLeiConstant.leiCount = 20;
					init(SaoLeiConstant.row, SaoLeiConstant.col);
					ResetGame();
				} else if(item == "初级难度") {
					SaoLeiConstant.row=23;
					SaoLeiConstant.col=23;
					SaoLeiConstant.leiCount = 43;
					SaoLeiConstant.is_regame=1;
					init(SaoLeiConstant.row, SaoLeiConstant.col);
//					frame.dispose();
					ResetGame();
//					ResetGame();
				} else if(item == "中级难度"){
					SaoLeiConstant.leiCount = 63;
					ResetGame();
				} else if(item == "高级难度"){
					SaoLeiConstant.leiCount = 99;
					ResetGame();
				} else if(item == "大师难度") {
					SaoLeiConstant.leiCount = 119;
					ResetGame();
				}
				
			}
			
		});
		top_panel.add(combobox_list);
		frame.add(top_panel,BorderLayout.NORTH);
		//p.add(new Label("总雷数:"+constant.leiCount,Label.CENTER));
		//p.add(new Label("总雷数:"+constant.leiCount,Label.RIGHT));
		 
	}
	
	
	/*
	void addnanduButton() {
		nandu.setBackground(Color.green);
		nandu.setOpaque(true);
		nandu.addActionListener(this);
		frame.add(nandu,BorderLayout.WEST);
	}
	
	void addResetButton() {
		reset.setBackground(Color.green);
		reset.setOpaque(true);
		reset.addActionListener(this);
		//reset.addMouseListener(this);
		frame.add(reset,BorderLayout.NORTH);	
	}
	*/
	
	void addLei() {
		Random rand = new Random();
		int randRow,randCol;
		for(int i=0; i<SaoLeiConstant.leiCount; i++) {
			randRow = rand.nextInt(SaoLeiConstant.row);
			randCol = rand.nextInt(SaoLeiConstant.col);
			if(counts[randRow][randCol] == SaoLeiConstant.BOMCODE) {
				i--;
			} else {
			counts[randRow][randCol] = SaoLeiConstant.BOMCODE;
			//buttons[randRow][randCol].setText("X");
			}
		}
	}
	
	void addButtons() {
		frame.add(container,BorderLayout.CENTER);
		container.setLayout(new GridLayout(SaoLeiConstant.row,SaoLeiConstant.col));
		for(int i=0;i<SaoLeiConstant.row;i++) {
			for(int j=0;j<SaoLeiConstant.col;j++) {
				JButton button = new JButton();
				button.setBackground(Color.white);
				button.setOpaque(true);
				button.addActionListener(this);
				button.addMouseListener((MouseListener) this);
				buttons[i][j] = button;
				container.add(button);
			}
		}
	}
	
	void calcNeiboLei() {
		int count;
		for(int i=0;i<SaoLeiConstant.row;i++) {
			for(int j=0;j<SaoLeiConstant.col;j++) {
				count =0;
				if(counts[i][j] == SaoLeiConstant.BOMCODE) continue;
				for(int k=0;k<8;++k) {
					int x=i+dir[k][0];
					int y=j+dir[k][1];
					if(x>=0&&x<SaoLeiConstant.row&&y>=0&&y<SaoLeiConstant.col&&counts[x][y]==SaoLeiConstant.BOMCODE)
						count++;
				}
				counts[i][j] = count;
				buttons[i][j].setMargin(new Insets(0,0,0,0));//让按钮随按钮上的图案变化
//				buttons[i][j].setText(counts[i][j] + "");
			}
		}
	}
	
	@Override
	 public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed!!!!");
	
		JButton button = (JButton)e.getSource();
		if(button.equals(reset_button)) {
			ResetGame();//重新开始游戏
		} else {
			int count = 0;
			for(int i=0;i<SaoLeiConstant.row;i++) {
				for(int j=0;j<SaoLeiConstant.col;j++) {
					if(button.equals(buttons[i][j])) {
						count = counts[i][j];
						if(count == SaoLeiConstant.BOMCODE) {
							loseGame();
						} else {
							openCell(i,j);	
							checkWin();
						}
						return;
						
					}	
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked!!!!");
		JButton button = (JButton)e.getSource();
		if (e.getButton() == MouseEvent.BUTTON3) {//判断鼠标右击动作
				for(int i=0;i<SaoLeiConstant.row;i++) {
					for(int j=0;j<SaoLeiConstant.col;j++) {
						if(button.equals(buttons[i][j])) {
							if((buttons[i][j].isEnabled() == true)) {
								//buttons[i][j].setEnabled(false)
								buttons[i][j].setMargin(new Insets(0,0,0,0));//让按钮随按钮上的图案变化
								if(buttons[i][j].getText()=="？")
									buttons[i][j].setText("");
								else
									buttons[i][j].setText("？");
							} 
							return;
						}
					}
				}	
		}
	}
	
	void ResetGame() {
		for(int i=0;i<SaoLeiConstant.row;i++) {
			for(int j=0;j<SaoLeiConstant.col;j++) {
				buttons[i][j].setText("");
				buttons[i][j].setEnabled(true);
				buttons[i][j].setBackground(Color.white);
				counts[i][j] = 0;		
			}	
		}
//		addButtons();
		addLei();
		calcNeiboLei();	
	}
	
	void checkWin() {
		for(int i=0;i<SaoLeiConstant.row;i++) {
			for(int j=0;j<SaoLeiConstant.col;j++) {
				if(buttons[i][j].isEnabled() == true && counts[i][j] != SaoLeiConstant.BOMCODE ) return;		
			}
		}
		JOptionPane.showMessageDialog(frame,"Yeah,你赢了！");	
	}
	
	//使用递归方法打开格子
	void openCell(int i, int j) {//保证该格子未打开且不是雷
		buttons[i][j].setBackground(Color.yellow);
		buttons[i][j].setOpaque(true);
		buttons[i][j].setEnabled(false);
		buttons[i][j].setText((counts[i][j]==0?" ":String.valueOf(counts[i][j])));
		if(counts[i][j] == 0) {
			for(int k=0;k<8;++k) {
				int x=i+dir[k][0];
				int y=j+dir[k][1];
				if(x>=0&&x<SaoLeiConstant.row&&y>=0&&y<SaoLeiConstant.col&&buttons[x][y].isEnabled() == true)
					openCell(x,y);
			}
		} else {
			buttons[i][j].setMargin(new Insets(0,0,0,0));
//			buttons[i][j].setText(counts[i][j] + "");
		}
	}
	
	void loseGame() {//在事件监听处 点击按钮并且count == constant.BOMCODE 这个区域是个雷
		for(int i=0;i<SaoLeiConstant.row;i++) {
			for(int j=0;j<SaoLeiConstant.col;j++) {
				int count = counts[i][j];
				if(count == SaoLeiConstant.BOMCODE) {
					buttons[i][j].setMargin(new Insets(0,0,0,0));
					buttons[i][j].setText("雷");
					buttons[i][j].setBackground(Color.red);
					buttons[i][j].setEnabled(false);
				} else {
					buttons[i][j].setMargin(new Insets(0,0,0,0));
					if(count==0) buttons[i][j].setText("");
					else buttons[i][j].setText(count + "");
					buttons[i][j].setEnabled(false);
					
				}
			}
		}
		JOptionPane.showMessageDialog(frame,"error,你输了！");
	}
	
//	public static void main(String[] args) {
//		new SaoLei();
//	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
public class Main{
	static public void main(String arg[]) {
		Saolei now=new Saolei();
	}
}