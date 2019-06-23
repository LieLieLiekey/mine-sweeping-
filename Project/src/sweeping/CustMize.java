package sweeping;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class CustMize{
	static public JLabel label1;
	static public  JLabel label2;
	static public  JLabel label3;
	static public  JTextField row_Field;
	static public  JTextField col_Field;
	static public  JTextField bom_Field;
	static public JButton play;
	static public JFrame custframe=new JFrame();
	static public JButton exit;
	static public void main(String [] args) {
		SwingUtilities.invokeLater(CustMize::create);
	}
	static public void create() {
		label1=new JLabel("高度:");
		label2=new JLabel("宽度:");
		label3=new JLabel("数量:");
		row_Field=new JTextField();
		col_Field=new JTextField();
		bom_Field=new JTextField();
		play=new JButton("Play");
		exit=new JButton("EXIT");
		custframe=new JFrame();
		/*设置frame格式*/
		custframe.setSize(200,260);
		custframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		custframe.setResizable(false);
		custframe.setLocation(920, 410);
		//设置没有装饰
		custframe.setUndecorated(true);
		custframe.setLayout(null);
		/*设置组件格式*/
		int add=10;
		label1.setBounds(0, 0+add, 100, 50);
		row_Field.setBounds(100, 0+add, 100, 50);
		label2.setBounds(0, 50+add, 100, 50);
		col_Field.setBounds(100, 50+add, 100, 50);
		label3.setBounds(0, 100+add, 100, 50);
		bom_Field.setBounds(100, 100+add, 100, 50);
		/*添加到frame*/
		custframe.add(label1);custframe.add(row_Field);
		custframe.add(label2);custframe.add(col_Field);
		custframe.add(label3);custframe.add(bom_Field);
		
		play.setBounds(50, 150+add, 100, 50);
		play.addActionListener((Object)->{
			try {
				if(check()) {
					close();
					ChoiceInterface.hind();
					Game.create();
				}
				else {
					JOptionPane.showMessageDialog(custframe,"Wrong Input");
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(custframe,"Wrong Input");
				// TODO: handle exception
			}
		});
		custframe.add(play);
		
		exit.setBounds(50, 200+add, 100, 50);
		exit.addActionListener((Object)->{
			close();
		});
		custframe.add(exit);
		custframe.setBackground(Color.white);
		custframe.setVisible(true);
	}
	static public void close() {
		custframe.removeAll();
		custframe.dispose();
		custframe.setVisible(false);
	}

	private static boolean check() throws Exception{
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
}