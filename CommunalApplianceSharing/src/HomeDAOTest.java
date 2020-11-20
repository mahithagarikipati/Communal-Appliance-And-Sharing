
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

public class HomeDAOTest {
		
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
    HomeDAO homedao = new HomeDAO();
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		@Before
		public void setup(){
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschema");
		}
		
		@Test
		public void getData() throws ClassNotFoundException, SQLException{

		       JSONArray arr = new JSONArray();

			JSONAssert.assertEquals(arr, homedao.getData("AlexList11"),true);
			
			 JSONObject obj = new JSONObject();
			 JSONObject obj1 = new JSONObject();
			 JSONObject obj2 = new JSONObject(); 
		       obj.put("appliance_id", 1); 
		       obj.put("firstName", "Alex");
		       obj.put("lastName", "List");
		       obj.put("appliance_name", "Lawn mover");
		       obj.put("appliance_desc", "New onw");
		       obj.put("userName", "AlexList11"); 
		       arr.put(obj);
		       obj1.put("appliance_id", 2);
		       obj1.put("firstName", "Alex");
		       obj1.put("lastName", "List");
		       obj1.put("appliance_name", "Printer");
		       obj1.put("appliance_desc", "Epson 2020 latest and brand new");
		       obj1.put("userName", "AlexList11");  
		       arr.put(obj1);
		       obj2.put("appliance_id", 3);
		       obj2.put("firstName", "Alex");
		       obj2.put("lastName", "List");
		       obj2.put("appliance_name", "Pressure washer");
		       obj2.put("appliance_desc", "IFB Brand - 10 lit capacity");
		       obj2.put("userName", "AlexList11");  
		       arr.put(obj2);
		       JSONAssert.assertEquals(arr, homedao.getData("Sherlock221"),true);  
		       
		       arr = new JSONArray();
		       JSONAssert.assertNotEquals(arr, homedao.getData("Sherlock221"),true);   
			
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschemaaaa");
		       arr = new JSONArray();
				JSONAssert.assertEquals(arr, homedao.getData(null),true); 
				
				arr = new JSONArray();
			       JSONAssert.assertEquals(arr, homedao.getData(""),true);   
			
			 
		}
		
		
}
