package Servelets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpdateSub
 */
public class UpdateSub extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Database db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSub() {
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
		
		String studentId = request.getParameter("studentId");
		System.out.println(studentId);
		int newSubscription = Integer.parseInt(request.getParameter("newSubscription"));
		
		db = new Database();
		
		try {
			Connection conn = db.getConnection();
			
			String Query = "SELECT subscription_month from Member where id=(?)";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1, studentId);
			ResultSet resultset = pstmt.executeQuery();
			resultset.next();
			int currentSubscriptionMonth = resultset.getInt("subscription_month");
			newSubscription += currentSubscriptionMonth;
			
			 String updateQuery = "UPDATE Member SET subscription_month = ? WHERE id = ?";
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
             updateStmt.setInt(1, newSubscription);
             updateStmt.setString(2, studentId);
             
             int rowsAffected = updateStmt.executeUpdate(); // Execute the update query

             if (rowsAffected > 0) {
            	 response.sendRedirect("error.jsp?error=Subscription extended successfully</br> redirecting to main page..."+"&source=manager.jsp&mode=green");
             } else {
            	 response.sendRedirect("error.jsp?error=Member with this ID not exist </br> redirecting to main page..."+"&source=manager.jsp&mode=red");
             }
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
