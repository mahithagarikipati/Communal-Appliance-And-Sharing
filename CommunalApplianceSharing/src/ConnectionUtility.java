import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	public static String driver ="jdbc:mysql://localhost:3306/world";
	String user ="mahitha";
	String password ="Test123";
	public static String getDriver() {
		return driver;
	}
	public static void setDriver(String driver) {
		ConnectionUtility.driver = driver;
	}
	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(driver,user,password); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
		return con;
		
	}
	public Connection getNewDriver(String driver) throws SQLException {
		Connection con = null;
		driver = getDriver();

		setDriver(driver);
		return con;
	}
	
}
