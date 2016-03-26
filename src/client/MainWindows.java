package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindows extends JFrame{
	
	JButton LoginBtn;
	JButton RegisterBtn;
	JButton ExitBtn;
	JPanel mJPanel;
	
	JTextField UserNameTxt;
	JPasswordField PasswordTxt;
	JPasswordField PasswordTxtAgain;
	
	JLabel LabUserName;
	JLabel LabPassword;
	JLabel LabPasswordAgain;
	
	public MainWindows(){
		this.mJPanel = new JPanel(null);
		//this.mJPanel.setSize(480,272);
		
		//UserName
		this.LabUserName = new JLabel("用户名：");
		this.LabUserName.setBounds(70, 20, 70, 30);
		this.mJPanel.add(this.LabUserName);
		
		this.UserNameTxt = new JTextField();
		this.UserNameTxt.setBounds(150, 20, 200, 30);
		this.mJPanel.add(this.UserNameTxt);
		
		//Password
		this.LabPassword = new JLabel("密码：");
		this.LabPassword.setBounds(70, 70, 70, 30);
		this.mJPanel.add(this.LabPassword);
		
		this.PasswordTxt = new JPasswordField();
		this.PasswordTxt.setBounds(150, 70, 200, 30);
		this.mJPanel.add(this.PasswordTxt);
		
		//PasswordAgain
		this.LabPasswordAgain = new JLabel("密码确认：");
		this.LabPasswordAgain.setBounds(70, 120, 70, 30);
		this.mJPanel.add(this.LabPasswordAgain);
		
		this.PasswordTxtAgain = new JPasswordField();
		this.PasswordTxtAgain.setBounds(150, 120, 200, 30);
		this.mJPanel.add(this.PasswordTxtAgain);
		
		this.LabPasswordAgain.setEnabled(false);
		this.PasswordTxtAgain.setEnabled(false);
		
		//Button
		this.LoginBtn = new JButton("登陆");
		this.LoginBtn.setBounds(60, 170, 100, 30);
		this.mJPanel.add(this.LoginBtn);
		this.LoginBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (UserNameTxt.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "用户名不能为空！");
					return;
				}
				if (PasswordTxt.getPassword().length < 6){
					JOptionPane.showMessageDialog(null, "密码长度不够，最少需要6位。");
					return;
				}
				String outString = "order=login&username=" + UserNameTxt.getText().trim() + "&password=" + String.valueOf(PasswordTxt.getPassword());
				ClientUpload mLogin = new ClientUpload(outString);
				JOptionPane.showMessageDialog(null, mLogin.ShowResult());
			}
		});
		
		this.RegisterBtn = new JButton("注册");
		this.RegisterBtn.setBounds(170, 170, 100, 30);
		this.mJPanel.add(this.RegisterBtn);
		this.RegisterBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!LabPasswordAgain.isEnabled()){
					LabPasswordAgain.setEnabled(true);
					PasswordTxtAgain.setEnabled(true);
				}
				else{
					if (UserNameTxt.getText().trim().equals("")){
						JOptionPane.showMessageDialog(null, "用户名不能为空！");
						return;
					}
					if (PasswordTxt.getPassword().length < 6){
						JOptionPane.showMessageDialog(null, "密码长度不够，最少需要6位。");
						return;
					}
					if (!String.valueOf(PasswordTxt.getPassword()).equals(String.valueOf(PasswordTxtAgain.getPassword()))){
						JOptionPane.showMessageDialog(null, "两次密码不一致。");
						return;
					}
					String outString = "order=register&username=" + UserNameTxt.getText().trim() + "&password=" + String.valueOf(PasswordTxt.getPassword());
					ClientUpload mLogin = new ClientUpload(outString);
					JOptionPane.showMessageDialog(null, mLogin.ShowResult());
				}
			}
		});
		
		this.ExitBtn = new JButton("退出");
		this.ExitBtn.setBounds(280, 170, 100, 30);
		this.mJPanel.add(this.ExitBtn);
		this.ExitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		this.setTitle("软件体系结构 客户端");
		this.add(this.mJPanel);
		this.setSize(480,272);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		MainWindows App = new MainWindows();
		App.setVisible(true);
	}
	
	
}
