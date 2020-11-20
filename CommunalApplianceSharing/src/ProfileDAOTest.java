
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

public class ProfileDAOTest {
		
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
    ProfileDAO profiledao = new ProfileDAO();
    ConnectionUtility connect = new ConnectionUtility();
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		@Before
		public void setup(){
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschema");
		}
		
		@Test
		public void updateData() throws SQLException,org.json.JSONException{
		       JSONObject obj = new JSONObject();
		       obj.put("zipcode", "123123");
		       obj.put("mode", "UPDATE");
		       obj.put("firstname", "Alex");
		       obj.put("address", "Baker Streel");
		       obj.put("phone", "7687121");
		       obj.put("psw", "Hellow12");
		       obj.put("pswr", "Hellow12");
		       obj.put("email", "ghhj@gjh.com");
		       obj.put("lastname", "List");
		       obj.put("username", "AlexList11");   

		    assertEquals("SUCCESS", profiledao.updateUserRecord(obj));
		    //expected username parameter to retrieve the data
		    obj = new JSONObject();
		       obj.put("zipcode", "123123");
		       obj.put("mode", "UPDATE");
		       obj.put("firstname", "Alex");
		       obj.put("address", "Baker Streel");
		       obj.put("phone", "7687121");
		       obj.put("psw", "Hellow12");
		       obj.put("pswr", "Hellow12");
		       obj.put("email", "ghhj@gjh.com");
		       obj.put("lastname", "List"); 
		       assertEquals(null, profiledao.updateUserRecord(obj));

		}
		
		@Test
		public void getData() throws ClassNotFoundException, SQLException{

			 JSONObject obj = new JSONObject();
		       obj.put("zipcode", 123123);
		       obj.put("firstName", "Alex");
		       obj.put("lastName", "List");
		       obj.put("phone_no", "7687121");
		       obj.put("password", "Hellow12");
		       obj.put("address", "Baker Streel");
		       obj.put("userName", "AlexList11");  
		       obj.put("email", "ghhj@gjh.com");

			JSONAssert.assertEquals(obj, profiledao.getData("AlexList11"),true);
			
			JSONAssert.assertNotEquals(obj,profiledao.getData("AlexList1"),true);
			
			obj = new JSONObject();
			obj.put("zipcode", 0);
			obj.put("userName", "AlexLi");    
			
			JSONAssert.assertEquals(obj,profiledao.getData("AlexLi"),true);
			
			obj = new JSONObject();    
			obj.put("zipcode", 0);
			JSONAssert.assertEquals(obj,profiledao.getData(null),true);
			
			ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschemaaaa");
			
			thrown.expect(NullPointerException.class);
			JSONAssert.assertEquals(obj,profiledao.getData(null),true);
			 
		}
		
		
}
