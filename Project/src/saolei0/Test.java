package saolei0;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class Test extends JFrame implements ActionListener {
    private JPanel panel0 = null, panel2 = null;
    private JButton b1 = null, b2 = null, b3 = null, b4 = null;
    public Test() {
        Container c = this.getContentPane();
        //边框布局
        c.setLayout(new BorderLayout());
        //创建panel
        panel0 = new JPanel();
        panel2 = new JPanel();
        //为2个panel设置底色
        panel0.setBackground(Color.red);
        panel2.setBackground(Color.BLUE);
        //2个panel都是用流水布局
        panel0.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        //创建按钮
        b1 = new JButton("panel2黄色");
        b2 = new JButton("panel2绿色");
        b3 = new JButton("panel0橙色");
        b4 = new JButton("panel0灰色");
        /**
         * 添加按钮事件
         */
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        /**
         * 将按钮添加相应panel上
         */
        panel0.add(b1);
        panel0.add(new JLabel());
        panel0.add(b2);
        panel2.add(b3);
        panel2.add(b4);
        /**
         * 将panel添加到容器
         */
        c.add(panel0, BorderLayout.CENTER);
        c.add(panel2, BorderLayout.EAST);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
         
    }    
    public static void main(String[] args) {
        new Test();    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == b1) {
            panel2.setBackground(Color.yellow);
        } else if (e.getSource() == b2) {
            panel2.setBackground(Color.green);
        } else if (e.getSource() == b3) {
            panel0.setBackground(Color.ORANGE);
        }  else if (e.getSource() == b4) {
            panel0.setBackground(Color.GRAY);
        } 
    }
}