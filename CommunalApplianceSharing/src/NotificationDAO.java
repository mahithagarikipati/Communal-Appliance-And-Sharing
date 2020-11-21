import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class NotificationDAO {	
	
	public JSONArray getData(String userName) throws SQLSyntaxErrorException{

		int count = 0;
		JSONArray arr = new JSONArray();
		try {
			ConnectionUtility ConnectionUtility = new ConnectionUtility();
			Connection con = ConnectionUtility.getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs1=stmt.executeQuery("select * from notifications");  
			if(rs1.next()){
				count = rs1.getInt(1);
				}
			ResultSet rs=stmt.executeQuery("select * from notifications");  
	            for (int i = 0; i < count; i++) {
	    		if(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("name", rs.getString(1));
				obj.put("item", rs.getString(2));
				obj.put("contact_number", rs.getString(3));
				obj.put("zipcode", rs.getInt(4));
				arr.put(obj);
				} 
			}

			con.close();  
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 return arr;
	}
	 

}