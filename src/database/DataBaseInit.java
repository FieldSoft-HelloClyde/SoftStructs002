package database;


import java.sql.*;

public class DataBaseInit {
	String DriverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	String DataBaseName;
	String ConnectURL;
	
	public DataBaseInit(String DataBaseName){
		this.DataBaseName = DataBaseName;
		this.ConnectURL = "jdbc:odbc:" + this.DataBaseName;
		try {
			Class.forName(this.DriverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection GetConnection(){
		Connection ResultConn = null;
		try {
			ResultConn = DriverManager.getConnection(this.ConnectURL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ResultConn;
	}
}
