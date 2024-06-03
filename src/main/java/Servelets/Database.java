package Servelets;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
 * Servlet implementation class Database
 */
public class Database extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected boolean isValidUser = false;
	protected String username;
	protected boolean isRegister = false;
	protected boolean isUserExist = false;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Database() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Connection getConnection() throws SQLException{
    	
    	Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/new_schema", "root", "123456");
    	
    	
    	return cn;
    }
    
	public void login(String name,String password) throws ClassNotFoundException {
			System.out.println(name);
		  Class.forName("com.mysql.cj.jdbc.Driver");
		    try { Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/new_schema", "root", "123456");
		    Statement statement = connection.createStatement();
	        
	        String query = "SELECT password FROM Institute";
	        query = query + " where name='";
	        query = query + name;
	        query = query + "'";
//	        System.out.println(query);
	       

	        ResultSet resultSet = statement.executeQuery(query);

	        resultSet.next();
	        String pw = resultSet.getString(1);
	        if(pw.equals(password)) {
 	        	isValidUser = true;
 	        	
 	        }
	        
	      
	        connection.close();
		         
		        
		        } catch (SQLException e) {
		        	
		        	 System.out.println("eror in login");
		        }
	  
 
	}
	
	public void register(String name,String password,String email) throws ClassNotFoundException {
		  System.out.println(email);
		  Class.forName("com.mysql.cj.jdbc.Driver");
		    try { Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/new_schema", "root", "123456");
		    
		    //For checking available user
		    String Query = "SELECT * FROM Institute WHERE name = (?)";
		    
		    PreparedStatement preparedStatement1 = connection.prepareStatement(Query);
		    preparedStatement1.setString(1, name);
		    ResultSet resultSet = preparedStatement1.executeQuery();
		    
		    if(resultSet.next()) {
		    	isUserExist = true;
		    	return;
		    }
		    
		    String insertQuery = "INSERT INTO Institute (name, password, email) VALUES (?, ?, ?)";
		    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		    
		    preparedStatement.setString(1, name);
           preparedStatement.setString(2, password);
           preparedStatement.setString(3, email);

           int rowsAffected = preparedStatement.executeUpdate();
//	        System.out.print(rowsAffected);
	        
		    if (rowsAffected > 0) {
	        	isRegister = true;
	        } else {
	            System.out.println("Failed to insert user.");
	        }
	        
	      
	        connection.close();
		         
		        
		        } catch (SQLException e) {
		        	
		        	 e.printStackTrace();
		        	
		        }
	  

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
