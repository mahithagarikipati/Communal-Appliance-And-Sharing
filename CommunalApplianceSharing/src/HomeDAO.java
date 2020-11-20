import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomeDAO {	
	
	public JSONArray getData(String userName) {

		int count = 0;
		JSONArray arr = new JSONArray();
		try {
			ConnectionUtility ConnectionUtility = new ConnectionUtility();
			Connection con = ConnectionUtility.getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs1=stmt.executeQuery("select count(*) from user a , appliance b where a.zipcode = (select zipcode from user where username = '"+userName+"') and a.username = b.username and available_to_dt > sysdate() and a.username not in ('"+userName+"')");  
			if(rs1.next()){
				count = rs1.getInt(1);
				}
			ResultSet rs=stmt.executeQuery("select a.username,a.first_name,a.last_name, b.appliance_id,b.appliance_desc,b.appliance_name from user a , appliance b where a.zipcode = (select zipcode from user where username = '"+userName+"') and a.username = b.username and available_to_dt > sysdate() and a.username not in ('"+userName+"')");  
	            for (int i = 0; i < count; i++) {
	    		if(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("userName", rs.getString(1));
				obj.put("firstName", rs.getString(2));
				obj.put("lastName", rs.getString(3));
				obj.put("appliance_id", rs.getInt(4));
				obj.put("appliance_desc", rs.getString(5));
				obj.put("appliance_name", rs.getString(6));
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
