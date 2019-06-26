package sweeping;

import javax.swing.*;
import java.awt.*;
public class Test {
	static public void main(String [] arg) {
		SwingUtilities.invokeLater(Test::ceshi);
	}
	static public void ceshi() {
		JFrame jf=new JFrame();
//		jf.setBackground(new Color(r, g, b));
		jf.setLayout(null);
		jf.setUndecorated(true);
//		jf.setBackground(new Color(0,255,0));
		jf.setOpacity(0.95f);
		jf.setSize(1200,960);
		jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jf.setResizable(false);
		
		JButton button=new JButton();
		button.setIcon(new ImageIcon("images/easy_0.png"));
		button.setBounds(100, 100, 380, 290);
		button.setBorderPainted(false);
		button.setRolloverIcon(new ImageIcon("images/easy_2.png"));
		button.setBackground(Color.white);
		button.setOpaque(true);
		button.setPressedIcon(new ImageIcon("images/easy_1.png"));
		jf.add(button);
		jf.setVisible(true);
	}
}
