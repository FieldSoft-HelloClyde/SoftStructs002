package server;

import java.io.*;
import java.net.*;

public class MainServer{
	public static final int PORT = 7788;
	
	public void init(){
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(PORT);
			while (true){
				Socket client = serverSocket.accept();
				//新线程处理这次访问
				System.out.println("接受了一个客户端");
				new HanderThread(client);
			}
		} catch (Exception e){
			e.printStackTrace();
			if (serverSocket != null){
				try {
					serverSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private class HanderThread implements Runnable{
		private Socket socket;
		
		public HanderThread(Socket client){
			this.socket = client;
			new Thread(this).start();
		}
		
		@Override
		public void run() {
			try {
				DataInputStream input = new DataInputStream(this.socket.getInputStream());
				DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
				String clientInputStr = input.readUTF();
				//处理传入的字符串
				System.out.println(this.socket.getInetAddress().getHostAddress() + "客户端内容：" + clientInputStr);
				//举例order=login&username=root&password=123456
				AnlysisString aString = new AnlysisString(clientInputStr);
				//根据指令进行判断
				String orderString = aString.GetValue("order");
				User user = new User(aString.GetValue("username"), aString.GetValue("password"));
				if (orderString.equals("login")){
					if (user.Login()){
						output.writeUTF("登录成功");
					}
					else {
						output.writeUTF("登录失败");
					}
				}
				else if (orderString.equals("register")){
					if (user.Register()){
						output.writeUTF("注册成功");
					}
					else{
						output.writeUTF("注册失败");
					}
				}
				
				//输出
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if (this.socket != null){
					try {
						this.socket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	public static void main(String[] args){
		MainServer server = new MainServer();
		System.out.println("服务器启动...");
		server.init();
	}
}
