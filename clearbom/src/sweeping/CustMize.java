package sweeping;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustMize extends JDialog{
	//会通过按钮监听器自动关闭
	ChoiceInterface father;
	 public  JLabel label1;
	 public  JLabel label2;
	 public  JLabel label3;
	 public  JTextField row_Field;
	 public  JTextField col_Field;
	 public  JTextField bom_Field;
	 public JButton play;
	 public JButton exit;
	 public JPanel cust_panel;
	 static int add=10;
	 public CustMize(ChoiceInterface father) {
		 super(father);
		 this.father=father;
		// TODO Auto-generated constructor stub
	}
	 static public void main(String [] args) {
		 
	 }
	 public CustMize(JFrame owner) {
		 super(owner,"CustSize",true);
		// TODO Auto-generated constructor stub
	}
	 void create() {
			label1=new JLabel("高度:");
			label2=new JLabel("宽度:");
			label3=new JLabel("数量:");
			row_Field=new JTextField();
			col_Field=new JTextField();
			bom_Field=new JTextField();
			play=new JButton("Play");
			exit=new JButton("EXIT");
			cust_panel=new JPanel();
			/*设置frame格式*/
			super.setSize(200,260);
			super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			super.setResizable(false);
			super.setLocation(920, 410);
			//设置没有装饰
			super.setUndecorated(true);
			super.setContentPane(cust_panel);
			super.setBackground(Color.white);
			cust_panel.setLayout(null);
			/*设置组件格式*/
			setRowInfo();
			setColInfo();
			setBomInfo();
			setExitButtonInfo();
			setPlayButtonInfo();
			/*添加到frame*/
			cust_panel.add(label1);cust_panel.add(row_Field);
			cust_panel.add(label2);cust_panel.add(col_Field);
			cust_panel.add(label3);cust_panel.add(bom_Field);
			cust_panel.add(play);
			cust_panel.add(exit);
			super.setVisible(true);
		}
	private void  setPlayButtonInfo() {
		play.setBounds(50, 150+add, 100, 50);
		play.addActionListener((Object)->{
			try {
				if(check()) {
					intoGame();
				}
				else {
					JOptionPane.showMessageDialog(this,"Wrong Input");
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(this,"Wrong Input");
				// TODO: handle exception
			}
		});
	}
	private void setExitButtonInfo() {
		exit.setBounds(50, 200+add, 100, 50);
		exit.addActionListener((Object)->{
			close();
		});
	}
	private void setRowInfo() {
		label1.setBounds(0, 0+add, 100, 50);
		row_Field.setBounds(100, 0+add, 100, 50);
	}
	private void setColInfo() {
		label2.setBounds(0, 50+add, 100, 50);
		col_Field.setBounds(100, 50+add, 100, 50);
	}
	private void setBomInfo() {
		label3.setBounds(0, 100+add, 100, 50);
		bom_Field.setBounds(100, 100+add, 100, 50);
	}
	void close() {
		// TODO Auto-generated method stub
		super.removeAll();
		super.dispose();
		super.setVisible(false);
	}
	private  boolean check() throws Exception{
		// TODO Auto-generated method stub
		String col=row_Field.getText(),row=col_Field.getText(),bom=bom_Field.getText();
		int col_cnt=Integer.valueOf(col);
		int row_cnt=Integer.valueOf(row);
		int bom_cnt=Integer.valueOf(bom);
		if(col_cnt*row_cnt<bom_cnt*2)
			return false;
		GameProperties.row=row_cnt;
		GameProperties.col=col_cnt;
		GameProperties.bom_cnt=bom_cnt;
		return true;
	}
	public void intoGame() {
		close();
		father.intoGame();
	}
}