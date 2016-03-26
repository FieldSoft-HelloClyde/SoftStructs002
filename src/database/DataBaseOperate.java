package database;


import java.sql.*;

public class DataBaseOperate {
	Connection conn;
	
	public DataBaseOperate(Connection ConnSrc){
		this.conn = ConnSrc;
	}
	
	public boolean ExecuteSQL(String SQLStatement){
		try {
			PreparedStatement TempStatement = this.conn.prepareStatement(SQLStatement);
			return TempStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet ExecuteQuerySQL(String SQLStatement){
		try {
			PreparedStatement TempStatement = this.conn.prepareStatement(SQLStatement);
			return TempStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected void finalize(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
