<%-- 
    Document   : TranQuangQuan_HienThi
    Created on : Oct 15, 2024, 10:18:44 AM
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
        <h2>Thông Tin Bạn Vừa Nhập</h2>
<h2>Trần Quang Quân - DHTI15A5HN - 21103100304</h2>
    <p>Bạn tên là: <%= session.getAttribute("name") %></p>
    <p>Năm nay bạn: <%= session.getAttribute("age") %> tuổi</p>

    <a href="TranQuangQuan_NhapThongTin.jsp">Nhập lại thông tin</a>
    </body>
</html>
