import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/ApplianceService")
public class ApplianceService  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		handleRequest(req,res);
	}
	@SuppressWarnings("unused")
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		JSONObject data = new JSONObject();
		String userName = null;
		String appId = null;
		String mode = null;
		String response= null;
		
		try {
			StringBuffer sb = null;
			BufferedReader read = req.getReader();
			String line;
			ApplianceDAO dao = new ApplianceDAO();
	        userName = req.getParameter("userName");
	        appId = req.getParameter("appId");
	        mode ="GETDATA";	 
			data = dao.getData(userName, appId);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8"); 
			PrintWriter out = res.getWriter();
			out.print(data);
			res.setStatus(200);		
		}
		catch(Exception e) {
			System.out.println("Exception in Appliance Details Service!!! "+e);
		}
	}

	
}
