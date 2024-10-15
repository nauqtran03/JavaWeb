<%-- 
    Document   : TranQuangQuan_21103100304
    Created on : Oct 8, 2024, 9:31:00 AM
    Author     : quant
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ví Dụ Session</title>
    </head>
    <body>
        <form action="" method="post">
            <h1>Trần Quang Quân _ DHTI15A5HN _ 21103100115</h1>
            <label>Nhập tên: </label>
            <input type="text" id="name" name="name">
            
            <input type="submit" value="Hiển thị">
        </form>

        <%
            String name = request.getParameter("name");
            HttpSession ssTen = request.getSession();
            if (name != null && !name.trim().isEmpty()) {
                ssTen.setAttribute("username", name);
            }
        %>
        <p>Tên của bạn: <%= (ssTen.getAttribute("username"))%></p>
        
    </body>
</html>
