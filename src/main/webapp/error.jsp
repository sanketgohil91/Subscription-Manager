<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
    body {
        background-color: #333;
    }

    .error {
    	width:100vh;
        
    }
    
    .h1{
    	margin : 50vh auto;
        font-size: 50px;
        font-weight: 800;
    }
</style>
<script>

    // Function to delay redirect
    function delayRedirect(url, delay) {
        setTimeout(function () {
            window.location.href = url;
        }, delay);
    }
    
</script>
</head>
<body>
<div class="error">
<%
    String mode = request.getParameter("mode");
    String error = request.getParameter("error");
%>
<h1 style="color: <%=mode%> "><%=request.getParameter("error")%></h1>

</div>
<%
    System.out.print("in error");
    String source = request.getParameter("source");
    // Set delay in milliseconds (1000 ms = 1 second)
    int delayMilliseconds = 2000; // Change this value to adjust the delay
    // Call JavaScript function to delay redirect
    %><script>delayRedirect('<%= source %>', <%= delayMilliseconds %>);</script><%
%>

</body>
</html>
