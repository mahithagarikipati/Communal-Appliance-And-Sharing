import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.sql.DataSource;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;

@RunWith(MockitoJUnitRunner.class)

public class ApplianceDAOTest {
		
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
	ApplianceDAO appliancedao = new ApplianceDAO();
    ConnectionUtility connect = new ConnectionUtility();
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		@Before
		public void setup(){
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschema");
		}
		
		@Test
		public void getData() throws ClassNotFoundException, SQLException{

			 JSONObject data = new JSONObject();
			 data.put("firstName", "Alex");
			 data.put("lastName", "List");
			 data.put("streetAddress", "Baker Streel");
			 data.put("phoneNo", "7687121"); 
			 data.put("price_per_day", "3.0");
			 data.put("available_from_dt", "2020-11-04");
			 data.put("available_to_dt", "2020-11-26");  


			JSONAssert.assertEquals(data, appliancedao.getData("AlexList11", "2"),true);
			
			JSONAssert.assertNotEquals(data, appliancedao.getData("AlexList111", "3"),true);
			
			JSONAssert.assertNotEquals(data, appliancedao.getData(null,null),true);
			
		}

		@Test
		public void addNotify() throws ClassNotFoundException, SQLException, NumberFormatException,SQLIntegrityConstraintViolationException{
			String message = "SUCCESS";
			assertEquals(message,appliancedao.addData("AlexList11", "2", "TestAClient"));
			assertEquals(message,appliancedao.addData("AlexList11", "3", "TestAClient"));
			assertEquals("FAIL",appliancedao.addData("", null, null));
		}
		
		
}
