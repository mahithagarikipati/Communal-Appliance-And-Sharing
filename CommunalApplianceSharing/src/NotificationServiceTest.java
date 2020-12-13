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

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

public class NotificationServiceTest extends Mockito{
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
	NotificationDAO notificationdao = new NotificationDAO();
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
	 when(req.getParameter("userName")).thenReturn("Damon");
     when(req.getParameter("appId")).thenReturn("2");
     when(req.getParameter("mode")).thenReturn("GETDATA");
     JSONArray arr = new JSONArray();

 	JSONObject data = new JSONObject();
 	data.put("borrower_username", "Damon"); 
    data.put("appliance_name", "normal cooker");
    data.put("phone_no", 987698321);
    data.put("zipcode", 2985);
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
     when(res.getWriter()).thenReturn(pw);

     NotificationService notificationService =new NotificationService();
     notificationService.doPost(req, res);
     arr=new JSONArray();
     
     JSONAssert.assertEquals(arr, notificationdao.getData(null),true);
     

     data = new JSONObject();
     data.put("borrower_username", "Damon"); 
     data.put("appliance_name", "normal cooker");
     data.put("phone_no", 987698321);
     data.put("zipcode", 2985);
     arr=new JSONArray();
     assertNotSame(data.toString(),sw.toString());

}

@Test
public void addData() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	JSONArray arr = new JSONArray();
	 when(req.getParameter("userName")).thenReturn("Damon");
     when(req.getParameter("appId")).thenReturn("2");
     when(req.getParameter("mode")).thenReturn("ADD");
    // when(req.getParameter("borrowerName")).thenReturn("Sherlock221");

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
     when(res.getWriter()).thenReturn(pw);

     NotificationService notificationService =new NotificationService();
     notificationService.doPost(req, res);
     //assertEquals("SUCCESS",sw.toString());
     arr=new JSONArray();
     
     JSONAssert.assertEquals(arr, notificationdao.getData(null),true);

}
@Test
public void nullTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	JSONArray arr = new JSONArray();
	 when(req.getParameter("userName")).thenReturn("");
     when(req.getParameter("appId")).thenReturn(null);
     when(req.getParameter("mode")).thenReturn("GETDATA");
     JSONObject data = new JSONObject();
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
     when(res.getWriter()).thenReturn(pw);

     NotificationService notificationService =new NotificationService();
     notificationService.doPost(req, res);
    
     arr=new JSONArray();
     
     JSONAssert.assertEquals(arr, notificationdao.getData(null),true);
     
}
}
