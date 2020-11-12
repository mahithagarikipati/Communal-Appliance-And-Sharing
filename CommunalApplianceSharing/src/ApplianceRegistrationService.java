import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/ApplianceRegistrationService")
public class ApplianceRegistrationService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp);
	}
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject obj = new JSONObject();
		String mode = null;
		String response= null;
		try {
			StringBuffer sb = null;
			BufferedReader read = req.getReader();
			String line;
			ApplianceRegistrationDAO dao = new ApplianceRegistrationDAO();
			 while ((line = read.readLine()) != null) {
		        	sb = new StringBuffer();
		            sb.append(line).append('\n');
		        }
			 if(sb!=null && !(sb.equals(""))) {
					obj = new JSONObject(sb.toString());
					mode = obj.getString("mode");
			        }
			 if(mode.equalsIgnoreCase("ADD")) {
				 response = dao.addNewAppliance(obj);
				 resp.setContentType("text/plain"); 
				 resp.setCharacterEncoding("UTF-8"); 
				 resp.getWriter().write(response); 
				 resp.setStatus(200);
			 }
		} catch(Exception e) {
			System.out.println("Exception in Appliance Registration Service!!! "+e);
		}
	}
}
