<%-- 
    Document   : TranQuangQuan_TrangNhap
    Created on : Oct 8, 2024, 10:15:10 AM
    Author     : quant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang Nhập Thông Tin</title>
    </head>
    <body>
        <h2>Trần Quang Quân - DHTI15A5HN - 21103100304</h2>
        <form action="TranQuangQuan_TrangNhap.jsp" method="post">
            <label>Nhập Tên:</label>
            <input type="text" name="ten" required>
            <br>
            <label>Chọn Lớp:</label>
            <select name="lop">
                <option value="Lớp 10">Lớp 10</option>
                <option value="Lớp 11">Lớp 11</option>
                <option value="Lớp 12">Lớp 12</option>
            </select>
            <br><br>
            <input type="submit" value="HIỂN THỊ">
        </form>
        <%!
            HttpSession session;
            
        %>
        <%
if(request.getMethod().equalsIgnoreCase("POST")){session = request.getSession();
            String ten1 = request.getParameter("ten");
            String lop1 = request.getParameter("lop");
            
            session.setAttribute("name", ten1);
            session.setAttribute("class", lop1);
            response.sendRedirect("TranQuangQuan_TrangHienThi");}
            
        %>
    </body>
</html>
