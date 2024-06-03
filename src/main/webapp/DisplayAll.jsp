<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Servelets.Database" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Of member</title>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
        border-radius  : 10px;
    }
    th, td {
    	
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }
    th {
        background-color: #7C8F96;
    }
</style>

<%      
    Database db = new Database();
    session=request.getSession(false); 
    String Institute = (String)session.getAttribute("Institute");
    String id = request.getParameter("studentId");
    db = new Database();

    try {
        Connection cn = db.getConnection();
        String selectQuery = "SELECT id, name, mobile_number AS Phone, joining_date AS JoiningDate, subscription_month AS TotalMonthOfSubscription FROM Member WHERE institute = ?";
        PreparedStatement pstmt = cn.prepareStatement(selectQuery);
        pstmt.setString(1, Institute);
 
        ResultSet resultSet = pstmt.executeQuery();
        
        %>
        </head>
        <body>
            <table>
                <tr>
                    <% for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) { %>
                    <th><%= resultSet.getMetaData().getColumnName(i) %></th>
                    <% } %>
                </tr>
                <% while(resultSet.next()) { %>
                <tr>
                    <% for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) { %>
                    <td><%= resultSet.getString(i) %></td>
                    <% } %>
                </tr>
                <% } %>
            </table>
        <%
            } catch(Exception e) {
                e.printStackTrace();
            }
        %>
        </body>
        </html>
