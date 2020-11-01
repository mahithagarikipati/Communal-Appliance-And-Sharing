import java.io.BufferedReader;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		handleRequest(req,res);
	}
	@SuppressWarnings("unused")
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		JSONObject obj = new JSONObject();
		String userName = null;
		String response= null;
		try {
			StringBuffer sb = null;
			BufferedReader read = req.getReader();
			String line;
			LoginDAO dao = new LoginDAO();
	        while ((line = read.readLine()) != null) {
	        	sb = new StringBuffer();
	            sb.append(line).append('\n');
	        }
	        if(sb!=null && !(sb.equals(""))) {
			obj = new JSONObject(sb.toString());
			
	        }
	       else{
	        	 userName = req.getParameter("userName");
	        		 
	        }
	        response = dao.addUserRecord(obj);
			res.setContentType("text/plain"); 
			res.setCharacterEncoding("UTF-8"); 
			res.getWriter().write(response); 
			res.setStatus(200);
		}
	        catch(Exception e) {
				System.out.println("Exceprion in Login Service!!! "+e);
	        }
		}

}
