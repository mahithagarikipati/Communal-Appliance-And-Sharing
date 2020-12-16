import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
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

public class LoginServiceTest extends Mockito {
	@Mock
	private DataSource ds;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement ps;
	@Mock
	private ResultSet rs;
	LoginDAO logindao = new LoginDAO();
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
	JSONObject obj = new JSONObject();
    obj.put("password", "Hellow12");
    assertEquals("SUCCESS",logindao.addUserRecord(obj));
	
 }

@Test
public void nullTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	 when(req.getParameter("userName")).thenReturn("");
     when(req.getParameter("mode")).thenReturn("GETDATA");
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
     when(res.getWriter()).thenReturn(pw);

     LoginService loginService =new LoginService();
     loginService.doPost(req, res);
    
     assertEquals("SUCCESS", logindao.addUserRecord(null));
     
}
}
