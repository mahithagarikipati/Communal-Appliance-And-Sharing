import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
			ConnectionUtility ConnectionUtility = new ConnectionUtility();
			Connection con = ConnectionUtility.getConnection();
			userName 	= obj.getString("username");
			appName  	= obj.getString("appliance_name");
			appDesc  	= obj.getString("appliance_desc");
			availFrom   = obj.getString("available_from_dt");
			availTo  	= obj.getString("available_to_dt");
			pricePerDay = obj.getDouble("price_per_day");
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
			LocalDate availFromdate = LocalDate.parse(availFrom, inputFormatter);
			LocalDate availTodate = LocalDate.parse(availTo, inputFormatter);
			
			PreparedStatement stmt = con.prepareStatement("insert into appliance values (?,?,?,?,?,?,?)");
			stmt.setString(1, null); //Auto increments appliance_id field in MySQL
			stmt.setString(2, userName);
			stmt.setString(3, appName);
			stmt.setString(4, appDesc);
			stmt.setDate(5, Date.valueOf(availFromdate));
			stmt.setDate(6, Date.valueOf(availTodate));
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
	 

	
}
