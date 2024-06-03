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

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Peding Details</title>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }
    th {
        background-color: #f2f2f2;
    }
</style>
<%      
    Database db = new Database();
    session=request.getSession(false); 
    String Institute = (String)session.getAttribute("Institute");
    db = new Database();

    try {
        Connection cn = db.getConnection();
        
        String selectAllQuery = "SELECT * FROM Member WHERE institute = ?";
        PreparedStatement selectAllStmt = cn.prepareStatement(selectAllQuery);
        selectAllStmt.setString(1, Institute);
        ResultSet allResultSet = selectAllStmt.executeQuery();
        
        LocalDate today = LocalDate.now().minusDays(1);
        
        while(allResultSet.next()) {
            Date joiningDate = allResultSet.getDate("joining_date");
            LocalDate joiningLocalDate = joiningDate.toLocalDate();
            
            int subscriptionMonth = allResultSet.getInt("subscription_month");
            int isSubActive = (joiningLocalDate.plusMonths(subscriptionMonth).isAfter(today)) ? 1 : 0;
            
            String updateQuery = "UPDATE Member SET is_sub_active = ? WHERE id = ?";
            PreparedStatement updateStmt = cn.prepareStatement(updateQuery);
            updateStmt.setInt(1, isSubActive);
            updateStmt.setInt(2, allResultSet.getInt("id"));
            updateStmt.executeUpdate();
        }
        
        String selectQuery = "SELECT id, name, mobile_number AS Phone, joining_date AS JoiningDate, subscription_month AS TotalMonthOfSubscription FROM Member WHERE institute = ? AND is_sub_active = 0";
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
