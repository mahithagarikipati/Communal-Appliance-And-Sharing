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

public class RegistrationServiceTest extends Mockito {
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
		       ConnectionUtility.setDriver("jdbc:mysql://localhost:3306/projectdb"); 
		}
		
@Test
public void serviceTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	when(req.getParameter("userName")).thenReturn("");
	JSONObject data = new JSONObject();
	 data.put("zipcode", 123123);
     data.put("firstname", "TestA");
     data.put("address", "Arizona");
     data.put("phone", "7687121");
     data.put("psw", "Hellow12");
     data.put("email", "ghhj@gjh.com");
     data.put("lastname", "Client");
     data.put("username", "TestAClient4");  
    StringWriter sw = new StringWriter();
    String s = new String();
    StringReader sr = new StringReader(s);
    PrintWriter pw = new PrintWriter(sw);
    BufferedReader bf = new BufferedReader(sr);
     when(res.getWriter()).thenReturn(pw);
     when(req.getReader()).thenReturn(bf);
     RegistrationService registrationService =new RegistrationService();
     registrationService.doPost(req, res);
     assertNotSame(	data.toString(),sw.toString());
   

}
@Test
public void nullTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	 when(req.getParameter("userName")).thenReturn("");
 	JSONObject obj = new JSONObject();
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
     when(res.getWriter()).thenReturn(pw);
     RegistrationService registrationService =new RegistrationService();
     registrationService.doPost(req, res);
    
     assertEquals("",sw.toString());

}
@Test
public void addTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	 when(req.getParameter("userName")).thenReturn("TestAClient4");
	 when(req.getParameter("mode")).thenReturn("ADD");
	 JSONObject data = new JSONObject();
	 data.put("zipcode", 123123);
     data.put("firstname", "TestA");
     data.put("address", "Arizona");
     data.put("phone", "7687121");
     data.put("psw", "Hellow12");
     data.put("email", "ghhj@gjh.com");
     data.put("lastname", "Client");
     data.put("username", "TestAClient4");  
     data.put("mode", "ADD");
    StringWriter sw = new StringWriter();
    
    String s = data.toString();
    StringReader sr = new StringReader(s);
    PrintWriter pw = new PrintWriter(sw);
    BufferedReader bf = new BufferedReader(sr);
     when(res.getWriter()).thenReturn(pw);
     when(req.getReader()).thenReturn(bf);
     RegistrationService registrationService = new RegistrationService();
     registrationService.doPost(req, res);
     assertEquals("SUCCESS",sw.toString());
     
     
   

}

@Test
public void addTestNull(){
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	
	 when(req.getParameter("userName")).thenReturn("");
		JSONObject obj = new JSONObject();
		 obj.put("zipcode", 123123);
	     obj.put("firstname", "TestA");
	     obj.put("lastname", "Client");
	     obj.put("phone", "7687121");
	     obj.put("psw", "Hellow12");
	     obj.put("address", "Arizona");
	     obj.put("username", "TestAClient4");  
	     obj.put("email", "ghhj@gjh.com");
    StringWriter sw = new StringWriter();
    
    String s =obj.toString();


     RegistrationService registrationService = new RegistrationService();
     try {
    	    StringReader sr = new StringReader(s);
    	    PrintWriter pw = new PrintWriter(sw);
    	    BufferedReader bf = new BufferedReader(sr);
    	     try {
    			when(res.getWriter()).thenReturn(pw);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	     try {
    			when(req.getReader()).thenReturn(bf);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		registrationService.doPost(req, res);
	     assertEquals("",sw.toString());
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     catch (org.json.JSONException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 		assert(true); 
 	}
     try {
    	  obj = new JSONObject();
    	  obj.put("mode", "ADD");
    	 s = obj.toString();
    	    StringReader sr = new StringReader(s);
    	 PrintWriter pw = new PrintWriter(sw);
 	    BufferedReader bf = new BufferedReader(sr);
 	     try {
 			when(res.getWriter()).thenReturn(pw);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	     try {
 			when(req.getReader()).thenReturn(bf);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		registrationService.doPost(req, res);
 	     assertEquals("",sw.toString());
 	}
     catch (ServletException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 	}
      catch (org.json.JSONException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		assert(true);
  	}

}

}
