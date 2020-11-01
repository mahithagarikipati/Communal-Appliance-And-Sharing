package com.jwt.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;  
import java.util.Date;  

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class ApplianceRegistrationServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
			
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String userID = request.getParameter("user_id");
        String appName = request.getParameter("appliance_name");
        String appDesc = request.getParameter("appliance_desc");
        String availFrom = request.getParameter("available_from_dt");
        String availTo = request.getParameter("available_to_dt");
        String pricePerDay = request.getParameter("price_per_day");
		
        Connection con = null;
        PreparedStatement ps = null;
        
       
        
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://projectdb-do-user-8049100-0.b.db.ondigitalocean.com:25060/projectDB", "doadmin", "nvxvc24irofl66ym"); 
 
            ps = con.prepareStatement("insert into appliance values(?,?,?,?,?,?,?)");
            ps.setString(1, null);
            ps.setString(2, userID);
            ps.setString(3, appName);
            ps.setString(4, appDesc);
            ps.setString(5, availFrom);
            ps.setString(6, availTo);
            ps.setString(7, pricePerDay);
            
            int i = ps.executeUpdate();
            if (i > 0)
                out.print("Your appliance is successfully registered...");
 
        } catch (Exception e2) {
            System.out.println(e2);
        }
        out.close();
		}
}