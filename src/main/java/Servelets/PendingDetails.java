package Servelets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.IOException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Servlet implementation class PendingDetails
 */
public class PendingDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Database db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingDetails() {
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
		HttpSession session=request.getSession(false); 
		String Institute = (String)session.getAttribute("Institute");
		db = new Database();

		try {
			Connection cn = db.getConnection();
			String selectQuery = "SELECT * FROM Member WHERE institute = ? AND subscription_month = 0";
			PreparedStatement pstmt = cn.prepareStatement(selectQuery);
			pstmt.setString(1, Institute);
			ResultSet resultSet = pstmt.executeQuery();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		}

}
