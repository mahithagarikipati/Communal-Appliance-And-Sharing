import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import org.json.JSONObject;
import java.sql.Date;

public class ApplianceRegistrationDAO {

	public String addNewAppliance(JSONObject obj) {
		String message = null;
		String userName = null;
		String appName = null;
		String appDesc = null;
		String availFrom = null;
		String availTo = null;	
		double pricePerDay = 0;
		
		try {
			
			Connection con = getConnection();
			userName 	= obj.getString("username");
			appName  	= obj.getString("appliance_name");
			appDesc  	= obj.getString("appliance_desc");
			availFrom   = obj.getString("available_from_dt");
			availTo  	= obj.getString("available_to_dt");
			pricePerDay = obj.getDouble("price_per_day");
			
			PreparedStatement stmt = con.prepareStatement("insert into appliance values (?,?,?,?,?,?,?)");
			stmt.setString(1, null); //Auto increments appliance_id field in MySQL
			stmt.setString(2, userName);
			stmt.setString(3, appName);
			stmt.setString(4, appDesc);
			stmt.setString(5, availFrom);
			stmt.setString(6, availTo);
			stmt.setDouble(7, pricePerDay);
	
			int i = stmt.executeUpdate();
            if (i > 0) {
                System.out.println("Your appliance is successfully registered...");
                message = "SUCCESS";
            }
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
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root", null); 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
		return con;
		
	}
	
}
