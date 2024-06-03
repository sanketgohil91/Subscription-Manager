package Servelets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Servlet implementation class DeleteMember
 */
public class DeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private Database db;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberIdToDelete = request.getParameter("studentToDelete");
		db = new Database();
		
		try {
			Connection conn = db.getConnection();
			
	        String deleteQuery = "DELETE FROM Member WHERE id = ?";
	        PreparedStatement pstmt = conn.prepareStatement(deleteQuery);

	            // Set the parameter
	            pstmt.setString(1, memberIdToDelete);

	            // Execute the delete statement
	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Member with ID " + memberIdToDelete + " deleted successfully.");
	                response.sendRedirect("error.jsp?error=Member Removed Successfully </br> redirecting to main Page..."+"&source=manager.jsp&mode=green");
	            } else {
	                System.out.println("No member found with ID " + memberIdToDelete + ".");
	                response.sendRedirect("error.jsp?error=Member don'r Removed </br> redirecting to main Page..."+"&source=manager.jsp&mode=red");
	            }
	            
			
		}catch(Exception e) {
			e.printStackTrace();
			}
	}

}
