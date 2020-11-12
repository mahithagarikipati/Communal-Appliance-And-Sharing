import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/ProfileService")
public class ProfileService extends HttpServlet{
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
		JSONObject data = new JSONObject();
		try {
			StringBuffer sb = null;
			BufferedReader read = req.getReader();
			String line;
			ProfileDAO dao = new ProfileDAO();
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
	        	 mode ="GETDATA";	 
	        }
			if(mode.equalsIgnoreCase("UPDATE")) {
				response = dao.updateUserRecord(obj);
				res.setContentType("text/plain"); 
				res.setCharacterEncoding("UTF-8"); 
				res.getWriter().write(response); 
				res.setStatus(200);
			}
			if(mode.equalsIgnoreCase("GETDATA")) {
				data = dao.getData(userName);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8"); 
				PrintWriter out = res.getWriter();
				out.print(data);
				res.setStatus(200);
				
				
			}
			  

			
		}
		catch(Exception e) {
			System.out.println("Exceprion in Profile Service!!! "+e);
		}
	}

	
}
