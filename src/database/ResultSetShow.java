package database;


import java.sql.*;

public class ResultSetShow {
	ResultSet mSet;
	
	public ResultSetShow(ResultSet SetSrc){
		this.mSet = SetSrc;
	}
	
	public void Show(int Length){
			try {
				while (this.mSet.next()){
					for (int i = 0;i < Length;i ++){
						System.out.print(this.mSet.getString(i + 1) + "\t");
					}
					System.out.println();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public boolean IsEmpty(){
		try {
			return !this.mSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public int GetSize(){
		try {
			int Size = 0;
			this.mSet.first();
			while (this.mSet.next()) {
				Size ++;
			}
			return Size;
		} catch (Exception e) {
			return 0;
		}
	}
}
