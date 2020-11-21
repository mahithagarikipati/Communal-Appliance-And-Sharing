import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/NotificationService")
public class NotificationService  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		handleRequest(req,res);
	}
	@SuppressWarnings("unused")
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		JSONObject obj = new JSONObject();
		String username = null;
		String mode = null;
		String response= null;
		JSONArray data = new JSONArray();
		try {
			StringBuffer sb = null;
			BufferedReader read = req.getReader();
			String line;
			NotificationDAO dao = new NotificationDAO();
	        username = req.getParameter("username");
	        mode ="GETDATA";	 
			data = dao.getData(username);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8"); 
			PrintWriter out = res.getWriter();
			out.print(data);
			res.setStatus(200);
					
		}
		catch(Exception e) {
			System.out.println("Exceprion in Notification Service!!! "+e);
		}
	}

	
}
