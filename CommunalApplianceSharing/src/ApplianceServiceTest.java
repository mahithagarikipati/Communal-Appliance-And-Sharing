import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.PrintWriter;
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
import org.mockito.Mock;
import org.mockito.Mockito;

public class ApplianceServiceTest extends Mockito{
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
public void serviceTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	 when(req.getParameter("userName")).thenReturn("AlexList11");
     when(req.getParameter("appId")).thenReturn("2");
     when(req.getParameter("mode")).thenReturn("GETDATA");

 	JSONObject data = new JSONObject();
 	data.put("firstName", "Alex");
	data.put("lastName", "List");
	data.put("streetAddress", "Baker Streel");
	data.put("phoneNo", "7687121"); 
	data.put("price_per_day", "3.0");
	data.put("available_from_dt", "2020-11-04");
	data.put("available_to_dt", "2020-11-26");
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
     when(res.getWriter()).thenReturn(pw);

     ApplianceService appService =new ApplianceService();
     appService.doPost(req, res);
     assertEquals(	data.toString(),sw.toString());
     data = new JSONObject();
  	data.put("firstName", "Alex");
 	data.put("lastName", "List");
 	data.put("streetAddress", "Baker Streel");

     assertNotSame(data.toString(),sw.toString());

}

@Test
public void addData() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	 when(req.getParameter("userName")).thenReturn("AlexList11");
     when(req.getParameter("appId")).thenReturn("2");
     when(req.getParameter("mode")).thenReturn("ADD");
     when(req.getParameter("borrowerName")).thenReturn("Sherlock221");

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
     when(res.getWriter()).thenReturn(pw);

     ApplianceService appService =new ApplianceService();
     appService.doPost(req, res);
     assertEquals("SUCCESS",sw.toString());

}
@Test
public void nullTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	 when(req.getParameter("userName")).thenReturn("");
     when(req.getParameter("appId")).thenReturn(null);
     when(req.getParameter("mode")).thenReturn("GETDATA");
     JSONObject data = new JSONObject();
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
     when(res.getWriter()).thenReturn(pw);

     ApplianceService appService =new ApplianceService();
     appService.doPost(req, res);
     assertEquals(data.toString(),sw.toString());

}
}
