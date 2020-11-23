
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;

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

public class RegistrationDAOTest {
		
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
    RegistrationDAO registrationdao = new RegistrationDAO();
    ConnectionUtility connect = new ConnectionUtility();
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		@Before
		public void setup(){
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschema");
		}
		
		
		@Test
		public void getValidation() throws ClassNotFoundException, SQLException{

		    assertEquals("INVALID",registrationdao.userValidation("Sherlock221"));
		    assertEquals("SUCCESS",registrationdao.userValidation("Sherlock22"));
		    assertEquals("SUCCESS",registrationdao.userValidation(""));
			 assertEquals("SUCCESS",registrationdao.userValidation(null));    
		    ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschemaaaa");
		    assertEquals("SUCCESS",registrationdao.userValidation("Sherlock221")); 
		}
		
		@Test
		public void getData() throws ClassNotFoundException, SQLException{

			 JSONObject obj = new JSONObject();
		       obj.put("zipcode", 123123);
		       obj.put("firstname", "TestA");
		       obj.put("lastname", "Client");
		       obj.put("phone", "7687121");
		       obj.put("psw", "Hellow12");
		       obj.put("address", "Arizona");
		       obj.put("username", "TestAClient4");  
		       obj.put("email", "ghhj@gjh.com");

			JSONAssert.assertEquals("SUCCESS", registrationdao.addUserRecord(obj),true);
			
			
			ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschemaaaa");
			obj = new JSONObject();
		       obj.put("zipcode", 123123);
		       obj.put("firstname", "TestA");
		       obj.put("lastname", "Client");
		       obj.put("phone", "7687121");
		       obj.put("psw", "Hellow12");
		       obj.put("address", "Arizona");
		       obj.put("username", "TestAClient");  
			JSONAssert.assertEquals(null,registrationdao.addUserRecord(obj),true);
			 
		}
		
		
}
