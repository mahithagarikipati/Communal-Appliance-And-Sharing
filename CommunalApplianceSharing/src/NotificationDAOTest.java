
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;

@RunWith(MockitoJUnitRunner.class)

public class NotificationDAOTest {
		
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
    NotificationDAO Notificationdao = new NotificationDAO();
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		@Before
		public void setup(){
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschema");
		}
		
		@Test
		public void getData() throws ClassNotFoundException, SQLException{

		       JSONArray arr = new JSONArray();

			
			
			 JSONObject obj = new JSONObject();
			 JSONObject obj1 = new JSONObject();
			 JSONObject obj2 = new JSONObject(); 
		       obj.put("borrower_username", "Damon"); 
		       obj.put("appliance_name", "normal cooker");
		       obj.put("phone_no", 987698321);
		       obj.put("zipcode", 2985);
		       
		       arr.put(obj);
		       obj.put("borrower_username", "stefan"); 
		       obj.put("appliance_name", "pressure cooker");
		       obj.put("phone_no", 987654321);
		       obj.put("zipcode", 2345);
		       
		         
		       
		       arr = new JSONArray();
		          
			
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschema");
		       arr = new JSONArray();
				JSONAssert.assertEquals(arr, Notificationdao.getData(null),true); 
				
				arr = new JSONArray();
			       JSONAssert.assertEquals(arr, Notificationdao.getData(""),true);   
			
			 
		}
		
		
}