package Servelets;

import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private Database db;
    public AddMember() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String MemberName = request.getParameter("MemberName");
		String MemberId = request.getParameter("MemberId");
		
		HttpSession session=request.getSession(false); 
		String Institute = (String)session.getAttribute("Institute");
		
		String months = request.getParameter("subscription_month");
		String Mobile = request.getParameter("phoneNumber");
		String date = request.getParameter("joining_date");
		System.out.print(date);
		
		db = new Database();
		
		try {
			Connection conn = db.getConnection();
			 String insertQuery = "INSERT INTO Member (id, name, institute, mobile_number, joining_date,subscription_month,is_sub_active) VALUES (?, ?, ?, ?, ?, ? ,1)";
			 PreparedStatement pstmt = conn.prepareStatement(insertQuery);
			    
			  	pstmt.setString(1, MemberId); // id
	            pstmt.setString(2, MemberName); // name
	            pstmt.setString(3,Institute ); // institute
	            pstmt.setString(4, Mobile); // mobile_number
	            pstmt.setString(5, date); // joining_date
	            pstmt.setString(6, months);
	            
	            int rowsAffected = pstmt.executeUpdate();  
	            if(rowsAffected>0) {
	            	response.sendRedirect("error.jsp?error=Member successfully added&source=manager.jsp&mode=green");
	            }else {
	            	
	            }
			    
		}catch(Exception e) {
			e.printStackTrace();
			}
	
		
		
	}

}
