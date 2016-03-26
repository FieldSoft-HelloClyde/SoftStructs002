package database;


import java.sql.*;

public class DataBaseTest {
	public static void main(String[] args){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection Conn = DriverManager.getConnection("jdbc:odbc:test");
			PreparedStatement PStatement = Conn.prepareStatement("create table UserInfo(UserName varchar (10),KeyWords varchar (10));");
			PStatement.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("test ok");
	}
}
