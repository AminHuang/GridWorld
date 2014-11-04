/**
 * 
 * @author amin
 * @version 1.0
 * @function an easy calculator
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/*GUI界面*/
public class EasyCalculator extends JFrame {

	private static final long serialVersionUID = 1L;
	//Button name
	private JButton jb1,jb2,jb3,add,sub,mul,div,ok;
	// Text name
	private JTextField num1, num2;
	
	/*主函数*/
   public static void main(String[] args) {
	   EasyCalculator cal = new EasyCalculator();
	   
	   /* 使可见
	    * 为了不出现不使用cal的情况
	    * 再这里调用函数
	    * 实际上可以在构造函数里设定
	    */
	   cal.setVisible(true);
   }
   
   public EasyCalculator() {
	   // 网格布局
	   this.setLayout(new GridLayout(2,5));
	   
	   //新建按钮
	   num1 = new JTextField("       12");
       jb1 = new JButton("");
       num2 = new JTextField("        2");
       jb2 = new JButton("=");
       jb3 = new JButton("");
       add = new JButton("+");
       sub = new JButton("-");
       mul = new JButton("*");
       div = new JButton("/");
       ok = new JButton("OK");
       
       this.add(num1); 
       this.add(jb1);
       this.add(num2);
       this.add(jb2);
       this.add(jb3);
       this.add(add);
       this.add(sub);
       this.add(mul);
       this.add(div);
       this.add(ok);
       
       // 以下按钮只能看
       num1.setEnabled(false);
       num2.setEnabled(false);
       jb1.setEnabled(false);
       jb2.setEnabled(false);
       jb3.setEnabled(false);
       
       // 事件监听
       ActionListener command = new CommandAction();
       add.addActionListener(command);
       sub.addActionListener(command);
       mul.addActionListener(command);
       div.addActionListener(command);
       
       ActionListener calculate = new CalculateAction();
       ok.addActionListener(calculate);
       
       
       this.setTitle("Easy Calculator");
		
       this.setSize(400, 200);
       
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
       
       // 不能更改窗口大小
       this.setResizable(false);
   }
   
   /* 私有类
    * 事件监听
    * 在按下运算键之后更改jb1中的字符
    */
   private class CommandAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		jb1.setText(command);
	}
   }
   
   /* 私有类
    * 根据jb1的运算符进行四则运算
    * 结果转换给String对jb3进行更改
    */
   private class CalculateAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String command = jb1.getActionCommand();
		double result = 0;
		String n1 = num1.getText().trim();
		String n2 = num2.getText().trim();
		double num1 = Double.parseDouble(n1);
		double num2 = Double.parseDouble(n2);
		
		// 这里是比较值是否相等，不要使用“==”
		if(command.equals("+")) {
			result = num1 + num2;
		} else if (command.equals("-")) {
			result = num1 - num2;
		} else if (command.equals("*") ) {
			result = num1 * num2;
		} else if (command.equals("/")) {
			result = num1 / num2; 
		}
		
		jb3.setText("" + result);
	}
	   
   }
   
   
}
