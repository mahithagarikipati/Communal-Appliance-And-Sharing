import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class ApplianceRegistrationServiceTest extends Mockito{
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
	ApplianceRegistrationService appliancedao = new ApplianceRegistrationService();
    ConnectionUtility connect = new ConnectionUtility();
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		@Before
		public void setup(){
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/testschema");
		}
		@Test
		public void serviceTest() throws Exception {
			HttpServletRequest req = mock(HttpServletRequest.class);
			HttpServletResponse res = mock(HttpServletResponse.class);
	

		 	JSONObject data = new JSONObject();
		 	 data.put("username","AlexList11");
		 	 data.put("appliance_name","PressureCooker");
			data.put("appliance_desc","Hawkins Brand New");
			data.put("available_from_dt","2020-11-11T02:50:12.208Z");
			data.put("available_to_dt", "2020-12-12T02:50:12.208Z");
			data.put("price_per_day",10);
			data.put("mode","ADD");
			String s = data.toString();
		    StringWriter sw = new StringWriter();
		    StringReader sr = new StringReader(s);
		    PrintWriter pw = new PrintWriter(sw);
		    BufferedReader bf = new BufferedReader(sr);
		    when(req.getReader()).thenReturn(bf);
		     when(res.getWriter()).thenReturn(pw);

		     ApplianceRegistrationService appService =new ApplianceRegistrationService();
		     appService.doPost(req, res);
		     assertEquals("SUCCESS",sw.toString());

		}

		@Test
		public void nullTest() throws Exception {
			HttpServletRequest req = mock(HttpServletRequest.class);
			HttpServletResponse res = mock(HttpServletResponse.class);
		     JSONObject data = new JSONObject();
		     data.put("username","");
		 	 data.put("appliance_name","PressureCooker");
			data.put("appliance_desc","Hawkins Brand New");
			data.put("available_from_dt","2020-11-11T02:50:12.208Z");
			data.put("available_to_dt", "2020-12-12T02:50:12.208Z");
			data.put("mode","ADD");
			
			String s = data.toString();
		    StringWriter sw = new StringWriter();
		    StringReader sr = new StringReader(s);
		    PrintWriter pw = new PrintWriter(sw);
		    BufferedReader bf = new BufferedReader(sr);
		    when(req.getReader()).thenReturn(bf);
		     when(res.getWriter()).thenReturn(pw);
		     ApplianceRegistrationService appService =new ApplianceRegistrationService();
		     appService.doPost(req, res);
		     assertEquals("",sw.toString());


		}
}
