import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/HomeService")
public class HomeService  extends HttpServlet{
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
		JSONArray data = new JSONArray();
		try {
			StringBuffer sb = null;
			BufferedReader read = req.getReader();
			String line;
			HomeDAO dao = new HomeDAO();
	        userName = req.getParameter("userName");
	        mode ="GETDATA";	 
			data = dao.getData(userName);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8"); 
			PrintWriter out = res.getWriter();
			out.print(data);
			res.setStatus(200);
					
		}
		catch(Exception e) {
			System.out.println("Exceprion in Home Service!!! "+e);
		}
	}

	
}
