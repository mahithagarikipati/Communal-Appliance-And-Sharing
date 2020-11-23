
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
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/mydb");
		}
		
		@Test
		public void getData() throws ClassNotFoundException, SQLException{

		       JSONArray arr = new JSONArray();

			
			
			 JSONObject obj = new JSONObject();
			 JSONObject obj1 = new JSONObject();
			 JSONObject obj2 = new JSONObject(); 
		       obj.put("name", "Alex"); 
		       obj.put("item", "cooker");
		       obj.put("contact_number", 980765);
		       obj.put("zipcode", 28262);
		       
		       arr.put(obj);
		       obj.put("name", "Shane"); 
		       obj.put("item", "cooker");
		       obj.put("contact_number", 984565);
		       obj.put("zipcode", 28272); 
		       arr.put(obj1);
		       obj.put("name", "White"); 
		       obj.put("item", "telivision");
		       obj.put("contact_number", 9987465);
		       obj.put("zipcode", 28572);
		       arr.put(obj2);
		         
		       
		       arr = new JSONArray();
		          
			
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/mydb");
		       arr = new JSONArray();
				JSONAssert.assertEquals(arr, Notificationdao.getData(null),true); 
				
				arr = new JSONArray();
			       JSONAssert.assertEquals(arr, Notificationdao.getData(""),true);   
			
			 
		}
		
		
}