import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import org.json.JSONObject;

public class ProfileDAO {

	public String updateUserRecord(JSONObject obj) {
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

			PreparedStatement stmt=con.prepareStatement("update user set password ='"+password+"', email = '"+email+"', phone_no ='"+phone+"', street_address='"+address+"',zipcode ='"+zipCode+"' where username ='"+userName+"'");
	
			stmt.executeUpdate();
			System.out.println("Update success");
			message = "SUCCESS";
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 return message;
	}
	
	public JSONObject getData(String userName) {
		String message = "SUCCESS";
		int count	   = 0;
		String firstName=null;
		String lastName = null;
		String password = null;
		String email = null;
		String phone_no = null;
		String address = null;
		int zipcode = 0;
		JSONObject obj = new JSONObject();
		try {
			Connection con = getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select first_name, last_name, password,email, phone_no, street_address,zipcode from user where username ='"+userName+"'");  
			if(rs.next()) {
				firstName = rs.getString(1);
				lastName = rs.getString(2);
				password = rs.getString(3);
				email = rs.getString(4);
				phone_no = rs.getString(5);
				address = rs.getString(6);
				zipcode = rs.getInt(7);
				}
			obj.put("firstName", firstName);
			obj.put("lastName", lastName);
			obj.put("password", password);
			obj.put("userName", userName);
			obj.put("email", email);
			obj.put("phone_no", phone_no);
			obj.put("address", address);
			obj.put("zipcode", zipcode);
			con.close();  
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 return obj;
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
