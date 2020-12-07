import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			 data.put("firstName", "Adrianna");
			 data.put("lastName", "Mckeown");
			 data.put("streetAddress", "5003 Cambridge Oaks Dr");
			 data.put("phoneNo", "704-989-1797"); 
			 data.put("price_per_day", "8.0");
			 data.put("available_from_dt", "2020-12-01");
			 data.put("available_to_dt", "2020-12-05");  


			JSONAssert.assertEquals(data, appliancedao.getData("amckeow3", "2"),true);
			
			JSONAssert.assertNotEquals(data, appliancedao.getData("amck3", "3"),true);
			
		}
		
		
}
