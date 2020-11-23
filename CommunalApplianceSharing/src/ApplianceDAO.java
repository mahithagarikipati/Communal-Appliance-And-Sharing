import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import org.json.JSONObject;

public class ApplianceDAO {	
String className ="com.mysql.cj.jdbc.Driver";

	public JSONObject getData(String userName, String appId) throws SQLSyntaxErrorException{
		JSONObject data = new JSONObject();
		String firstName = null;
		String lastName = null;
		String phoneNo = null;
		String streetAddress = null;
		String price_per_day = null;
		String available_from_dt = null;
		String available_to_dt = null;
		
		try {
			ConnectionUtility ConnectionUtility = new ConnectionUtility();
			Connection con = ConnectionUtility.getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT a.first_name, a.last_name, a.street_address, a.phone_no, b.price_per_day, b.available_from_dt, b.available_to_dt FROM user a, appliance b WHERE a.username = '"+userName+"' and b.appliance_id = '"+appId+"' and a.username = b.username");
			if(rs.next()) {
				firstName = rs.getString(1);
				lastName = rs.getString(2);
				streetAddress = rs.getString(3);
				phoneNo = rs.getString(4);
				price_per_day = rs.getString(5);
				available_from_dt = rs.getString(6);
				available_to_dt = rs.getString(7);
				}
			
				data.put("firstName", firstName);
				data.put("lastName", lastName);
				data.put("streetAddress", streetAddress);
				data.put("phoneNo", phoneNo); 
				data.put("price_per_day", price_per_day);
				data.put("available_from_dt", available_from_dt);
				data.put("available_to_dt", available_to_dt);

			con.close();  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	 

}
