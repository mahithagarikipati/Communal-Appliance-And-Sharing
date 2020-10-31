import java.io.BufferedReader;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/RegistrationService")
 
public class RegistrationService extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		handleRequest(req,res);
	}
	@SuppressWarnings("unused")
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		JSONObject obj = new JSONObject();
		String userName = null;
		String mode = null;
		String response= null;
		try {
			StringBuffer sb = null;
			BufferedReader read = req.getReader();
			String line;
			RegistrationDAO dao = new RegistrationDAO();
	        while ((line = read.readLine()) != null) {
	        	sb = new StringBuffer();
	            sb.append(line).append('\n');
	        }
	        if(sb!=null && !(sb.equals(""))) {
			obj = new JSONObject(sb.toString());
			mode = obj.getString("mode");
	        }
	       else{
	        	 userName = req.getParameter("userName");
	        	 mode ="USERVALIDCHECK";	 
	        }
			if(mode.equalsIgnoreCase("ADD")) {
				response = dao.addUserRecord(obj);
				res.setContentType("text/plain"); 
				res.setCharacterEncoding("UTF-8"); 
				res.getWriter().write(response); 
				res.setStatus(200);
			}
			if(mode.equalsIgnoreCase("USERVALIDCHECK")) {
				response = dao.userValidation(userName);
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8"); 
				res.getWriter().write(response);
				res.setStatus(200);
				
				
			}
			  

			
		}
		catch(Exception e) {
			System.out.println("Exceprion in Registration Service!!! "+e);
		}
	}

	
}
