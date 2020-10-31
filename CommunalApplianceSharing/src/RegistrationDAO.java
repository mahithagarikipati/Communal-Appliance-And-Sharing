import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import org.json.JSONObject;

public class RegistrationDAO {

	public String addUserRecord(JSONObject obj) {
		String message = null;
		String firstName = null;
		String lastName = null;
		String address = null;
		String phone = null;
		String password = null;	
		String userName = null;
		int zipCode =0;
		String email = null;
		int user_id = 0;
		try {
			
			Connection con = getConnection();
			firstName = obj.getString("firstname");
			lastName  = obj.getString("lastname");
			userName  = obj.getString("username");
			phone     = obj.getString("phone");
			password  = obj.getString("psw");
			address   = obj.getString("address");
			zipCode	  = obj.getInt("zipcode");
			email     = obj.getString("email");
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select ifnull(max(user_id),0) +1 from user");  
			if(rs.next()){
				user_id = rs.getInt(1);
				}
			PreparedStatement stmt=con.prepareStatement("insert into user values (?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1,user_id);
			stmt.setString(2, userName);
			stmt.setString(3, password);
			stmt.setString(4, firstName);
			stmt.setString(5, lastName);
			stmt.setString(6, email);
			stmt.setString(7, phone);
			stmt.setString(8, address);
			stmt.setInt(9,zipCode);
	
			stmt.executeUpdate();
			System.out.println("Insert success");
			message = "SUCCESS";
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 return message;
	}
	
	public String userValidation(String userName) {
		String message = "SUCCESS";
		int count	   = 0;
		try {
			Connection con = getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select count(*) from user where username ='"+userName+"'");  
			if(rs.next()) {
				count = rs.getInt(1);
				if(count > 0) {
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
