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

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

public class ProfileServiceTest extends Mockito {
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
public void serviceTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	 when(req.getParameter("userName")).thenReturn("AlexList11");
 	JSONObject data = new JSONObject();
 	data.put("zipcode", 123123);
 	data.put("firstName", "Alex");
	data.put("lastName", "List");
	data.put("phone_no", "76871211");
	data.put("password", "Hellow12");
	data.put("address", "Baker Streel");
	data.put("userName", "AlexList11");
	data.put("email", "ghhj@gjh.com");
    StringWriter sw = new StringWriter();
    String s = new String();
    StringReader sr = new StringReader(s);
    PrintWriter pw = new PrintWriter(sw);
    BufferedReader bf = new BufferedReader(sr);
     when(res.getWriter()).thenReturn(pw);
     when(req.getReader()).thenReturn(bf);
     ProfileService profileService =new ProfileService();
     profileService.doPost(req, res);
     assertEquals(	data.toString(),sw.toString());
   

}
@Test
public void nullTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	 when(req.getParameter("userName")).thenReturn("");
 	JSONObject data = new JSONObject();
 	data.put("zipcode", 0);
 	data.put("userName","");
    StringWriter sw = new StringWriter();
    String s = new String();
    StringReader sr = new StringReader(s);
    PrintWriter pw = new PrintWriter(sw);
    BufferedReader bf = new BufferedReader(sr);
     when(res.getWriter()).thenReturn(pw);
     when(req.getReader()).thenReturn(bf);
     ProfileService profileService =new ProfileService();
     profileService.doPost(req, res);
     assertEquals(	data.toString(),sw.toString());
   

}
@Test
public void updateTest() throws Exception {
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	 when(req.getParameter("userName")).thenReturn("");
		JSONObject data = new JSONObject();
	 	data.put("zipcode", 123123);
	 	data.put("firstname", "Alex");
		data.put("lastname", "List");
		data.put("phone", "76871211");
		data.put("psw", "Hellow12");
		data.put("address", "Baker Streel");
		data.put("username", "AlexList11");
		data.put("email", "ghhj@gjh.com");
		data.put("mode", "UPDATE");
    StringWriter sw = new StringWriter();
    
    String s =data.toString();
    StringReader sr = new StringReader(s);
    PrintWriter pw = new PrintWriter(sw);
    BufferedReader bf = new BufferedReader(sr);
     when(res.getWriter()).thenReturn(pw);
     when(req.getReader()).thenReturn(bf);
     ProfileService profileService =new ProfileService();
     profileService.doPost(req, res);
     assertEquals("SUCCESS",sw.toString());
   

}

@Test
public void updateTestNull(){
	HttpServletRequest req = mock(HttpServletRequest.class);
	HttpServletResponse res = mock(HttpServletResponse.class);
	
	 when(req.getParameter("userName")).thenReturn("");
		JSONObject data = new JSONObject();
	 	data.put("zipcode", 123123);
	 	data.put("firstname", "Alex");
		data.put("lastname", "List");
		data.put("phone", "76871211");
		data.put("psw", "Hellow12");
		data.put("address", "Baker Streel");
		data.put("username", "AlexList11");
		data.put("email", "ghhj@gjh.com");
    StringWriter sw = new StringWriter();
    
    String s =data.toString();


     ProfileService profileService =new ProfileService();
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
		profileService.doPost(req, res);
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
    	  data = new JSONObject();
    	  data.put("mode", "UPDATE");
    	 s = data.toString();
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
 		profileService.doPost(req, res);
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
