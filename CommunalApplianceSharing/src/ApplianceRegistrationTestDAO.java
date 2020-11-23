
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

public class ApplianceRegistrationTestDAO {
		
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
    ApplianceRegistrationDAO applianceRegistration= new ApplianceRegistrationDAO();
    ConnectionUtility connect = new ConnectionUtility();
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		@Before
		public void setup(){
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschema");
		}
		
		
		
		@Test
		public void getData() throws ClassNotFoundException, SQLException,java.sql.SQLIntegrityConstraintViolationException{

			 JSONObject obj = new JSONObject();
		       obj.put("username", "TestAClient");
		       obj.put("appliance_name", "Printer epson");
		       obj.put("appliance_desc", "BrandNew");
		       obj.put("available_from_dt", "2020-11-11T02:50:12.208Z");
		       obj.put("available_to_dt", "2020-12-12T02:50:12.208Z");
		       obj.put("price_per_day", 11);

			JSONAssert.assertEquals("SUCCESS", applianceRegistration.addNewAppliance(obj),true);
			
			  obj = new JSONObject();
		       obj.put("username", "TestAClientAAA");
		       obj.put("appliance_name", "Printer");
		       obj.put("appliance_desc", "BrandNew");
		       obj.put("available_from_dt", "2020-11-11T02:50:12.208Z");
		       obj.put("available_to_dt", "2020-12-12T02:50:12.208Z");
		       obj.put("price_per_day", 11);
		       JSONAssert.assertEquals(null, applianceRegistration.addNewAppliance(obj),true);
		       
		       obj = new JSONObject();
		       obj.put("username", "TestAClientAAA");
		       obj.put("appliance_name", "Printer");
		       obj.put("appliance_desc", "BrandNew");
		       obj.put("available_from_dt", "2020-11-11T02:50:12.208Z");
		       obj.put("available_to_dt", "2020-12-12T02:50:12.208Z");
		       JSONAssert.assertEquals(null, applianceRegistration.addNewAppliance(obj),true);
				
			
			ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschemaaaa");
			 obj = new JSONObject();
		       obj.put("username", "TestAClientA");
		       obj.put("appliance_name", "XBOX");
		       obj.put("appliance_desc", "BrandNew");
		       obj.put("available_from_dt", "2020-11-11T02:50:12.208Z");
		       obj.put("available_to_dt", "2020-12-12T02:50:12.208Z");
		       obj.put("price_per_day", 11);

			JSONAssert.assertEquals(null, applianceRegistration.addNewAppliance(obj),true);
			
		}
		
		
}
