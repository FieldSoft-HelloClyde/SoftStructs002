package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientUpload {
	String Text;
	public ClientUpload(String Text){
		this.Text = Text;
	}
	
	public String ShowResult(){
		System.out.println("�ͻ���׼����������");
		Socket socket = null;
		try {
			socket = new Socket(ServerConfig.IP_ADDR,ServerConfig.PORT);
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
			System.out.println("�ͻ��˷������ݣ�" + this.Text);
			outputStream.writeUTF(this.Text);
			String retString = inputStream.readUTF();
			System.out.println("���������ؽ����" + retString);
			inputStream.close();
			outputStream.close();
			return retString;
		} catch (Exception e) {
			if (socket != null){
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return "������δ����ֵ";
	}
}
