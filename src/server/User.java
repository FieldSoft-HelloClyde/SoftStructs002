package server;

import database.*;

public class User {
	String UserName;
	String Password;
	
	public User(String userNameString,String passwordString){
		this.UserName = userNameString;
		this.Password = passwordString;
	}
	
	public boolean Login(){
		DataBaseInit DBInit = new DataBaseInit("test");
		DataBaseOperate DBOperate = new DataBaseOperate(DBInit.GetConnection());
		ResultSetShow RSShow = new ResultSetShow(DBOperate.ExecuteQuerySQL("select * from UserInfo where UserName='" + this.UserName + "' and UserPassword='" + this.Password + "'"));
		if (RSShow.IsEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean Register(){
		DataBaseInit DBInit = new DataBaseInit("test");
		DataBaseOperate DBOperate = new DataBaseOperate(DBInit.GetConnection());
		DBOperate.ExecuteSQL("insert into UserInfo values ('" + this.UserName + "','" + this.Password + "')");
		return true;
	}
}
