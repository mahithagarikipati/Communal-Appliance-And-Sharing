
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

public class LoginDAOTest {
		
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
    LoginDAO logindao = new LoginDAO();
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		@Before
		public void setup(){
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschema");
		}
		
		@Test
		public void getValidation() throws ClassNotFoundException, SQLException{

		       JSONObject obj = new JSONObject();
		       obj.put("username", "AlexList11");
		       obj.put("password", "Hellow12");

		    assertEquals("SUCCESS",logindao.addUserRecord(obj));
		     obj = new JSONObject();
		     obj.put("username", "AlexList1");
		     obj.put("password", "Hellow12");
		    assertEquals("INVALID",logindao.addUserRecord(obj));
		    obj = new JSONObject();
		     obj.put("username", "");
		     obj.put("password", "");
		    assertEquals("INVALID",logindao.addUserRecord(obj));
		    
		    obj = new JSONObject();
		    assertEquals("SUCCESS",logindao.addUserRecord(obj));    
		    obj = new JSONObject();
		       obj.put("username", "AlexList11");
		       obj.put("password", "Hellow12");
		    ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschemaaaa");
		    assertEquals("SUCCESS",logindao.addUserRecord(obj)); 
		}
		
		
}
