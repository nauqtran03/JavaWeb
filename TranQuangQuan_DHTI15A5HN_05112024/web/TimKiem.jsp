<%-- 
    Document   : TimKiem
    Created on : Nov 5, 2024, 9:44:28 AM
    Author     : quant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Điện Thoại</title>
</head>
<body>
    <h2>Danh sách Điện Thoại</h2>
    <form action="XLTimKiem" method="POST">
        Nhập mã điện thoại: <input type="text" name="maDienThoai"/>
        <input type="submit" value="Tìm kiếm"/>
    </form>
    <table border="1">
        <tr>
            <th>Mã</th>
            <th>Tên</th>
            <th>Số lượng</th>
            <th>Đơn giá</th>
        </tr>
        <%
            // Kết nối cơ sở dữ liệu và lấy dữ liệu từ bảng DienThoai
            DecimalFormat df = new DecimalFormat("#,###");  // Định dạng số với dấu phẩy
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qldt", "root", "");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM dienthoai");

                while(rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("Ma") + "</td>");
                    out.println("<td>" + rs.getString("Ten") + "</td>");
                    out.println("<td>" + rs.getInt("Soluong") + "</td>");
                    out.println("<td>" + df.format(rs.getDouble("Dongia")) + "</td>");  // Định dạng đơn giá
                    out.println("</tr>");
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        %>
    </table>
</body>
</html>
