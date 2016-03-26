package database;


public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataBaseInit DBInit = new DataBaseInit("test");
		DataBaseOperate DBOperate = new DataBaseOperate(DBInit.GetConnection());
		DBOperate.ExecuteSQL("create table UserInfo(UserName varchar(20),UserPassword varchar(20))");
		//DBOperate.ExecuteSQL("insert into UserInfo values ('root','123456');");
		ResultSetShow RSShow = new ResultSetShow(DBOperate.ExecuteQuerySQL("select * from UserInfo"));
		RSShow.Show(2);
	}

}
