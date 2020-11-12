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
			Connection con = getConnection();
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
public String userValidation(String userName, String password) {
	String message = "SUCCESS";
	int count	   = 0;	
			try {
				Connection con = getConnection();
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
				e.printStackTrace();
			}
			return message; 
		}
		 
		public Connection getConnection() throws SQLException {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/world","mahitha","Test123"); 


			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}  
			return con;
			
		
		
	
	}

}
