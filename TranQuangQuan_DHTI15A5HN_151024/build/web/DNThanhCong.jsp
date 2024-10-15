<%-- 
    Document   : DNThanhCong
    Created on : Oct 15, 2024, 9:08:55 AM
    Author     : quant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Chào mừng bạn!</h2>

    <%
       
        String username = (String) session.getAttribute("username");

        if (username != null) {
            out.println("<p>Xin chào, " + username + "!</p>");
        } else {
   
            response.sendRedirect("TranQuangQuan_Login.jsp");
        }
    %>
    </body>
</html>
