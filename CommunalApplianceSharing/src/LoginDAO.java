import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import org.json.JSONObject;

public class LoginDAO {
	public String addUserRecord(JSONObject obj) {
		String message="SUCCESS";
		String password = null;	
		String userName = null;
try {
	int count	   = 0;
	ConnectionUtility ConnectionUtility = new ConnectionUtility();
	Connection con = ConnectionUtility.getConnection();
			userName  = obj.getString("username");
			password  = obj.getString("password");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select count(*) from user where username ='"+userName+"'and password='"+password+"'");  
			if(rs.next()) {
				count = rs.getInt(1);
				if(count != 1) {
				message="INVALID";
				}
			}
			con.close();
			
     }
    catch(Exception e) {
          e.printStackTrace();}
     return message;
     }
}
