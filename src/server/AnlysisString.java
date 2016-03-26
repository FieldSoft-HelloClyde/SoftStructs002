package server;

public class AnlysisString {
	String OrginStr;
	String[] StrArr;
	public AnlysisString(String Text){
		this.OrginStr = Text;
		this.StrArr = Text.split("&");
	}
	
	public String GetValue(String ArrtitubeName){
		if (ArrtitubeName.equals("")){
			return null;
		}
		for (String tempStr:this.StrArr){
			if (tempStr.startsWith(ArrtitubeName)){
				return tempStr.split("=")[1];
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		AnlysisString testObj = 
				new AnlysisString("order=login&username=root&password=123456");
		System.out.println(testObj.GetValue(""));
	} 
}
