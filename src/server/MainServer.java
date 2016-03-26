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
				//���̴߳�����η���
				System.out.println("������һ���ͻ���");
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
				//��������ַ���
				System.out.println(this.socket.getInetAddress().getHostAddress() + "�ͻ������ݣ�" + clientInputStr);
				//����order=login&username=root&password=123456
				AnlysisString aString = new AnlysisString(clientInputStr);
				//����ָ������ж�
				String orderString = aString.GetValue("order");
				User user = new User(aString.GetValue("username"), aString.GetValue("password"));
				if (orderString.equals("login")){
					if (user.Login()){
						output.writeUTF("��¼�ɹ�");
					}
					else {
						output.writeUTF("��¼ʧ��");
					}
				}
				else if (orderString.equals("register")){
					if (user.Register()){
						output.writeUTF("ע��ɹ�");
					}
					else{
						output.writeUTF("ע��ʧ��");
					}
				}
				
				//���
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
		System.out.println("����������...");
		server.init();
	}
}
