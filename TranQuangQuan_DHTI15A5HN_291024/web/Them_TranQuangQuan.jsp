<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@ page import="com.example.XeMay" %> 

<%
    ArrayList<XeMay> danhSachXe = new ArrayList<>();

    String jdbcURL = "jdbc:mysql://localhost:3306/qlxemay";
    String dbUser = "root";
    String dbPassword = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

        // Truy vấn dữ liệu từ bảng xemay
        String sql = "SELECT * FROM xemay";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String ma = rs.getString("Ma");
            String ten = rs.getString("Ten");
            String hangSX = rs.getString("HangSX");
            String mau = rs.getString("Mau");

            XeMay xeMay = new XeMay(ma, ten, hangSX, mau);
            danhSachXe.add(xeMay);
        }

        // Đóng kết nối
        rs.close();
        statement.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Xe Máy</title>
    <style>
        table { width: 50%; border-collapse: collapse; margin: 20px 0; }
        table, th, td { border: 1px solid black; }
        th, td { padding: 10px; text-align: center; }
    </style>
</head>
<body>
    <h2>Danh Sách Xe Máy</h2>
    <h3>Trần Quang Quân - DHTI15A5HN - 21103100304</h3>
    
    <table>
        <tr>
            <th>Mã</th>
            <th>Tên</th>
            <th>Hãng Sản Xuất</th>
            <th>Màu</th>
        </tr>
        <%
            for (XeMay xe : danhSachXe) {
        %>
        <tr>
            <td><%= xe.getMa() %></td>
            <td><%= xe.getTen() %></td>
            <td><%= xe.getHangSX() %></td>
            <td><%= xe.getMau() %></td>
        </tr>
        <%
            }
        %>
    </table>

    <h2>Thêm Xe Máy Mới</h2>
    <form action="HTServlet_TranQuangQuan" method="post"> <!-- Trỏ tới servlet xử lý thêm -->
        <label for="ma">Mã:</label>
        <input type="text" id="ma" name="ma" required><br><br>
        
        <label for="ten">Tên:</label>
        <input type="text" id="ten" name="ten" required><br><br>
        
        <label for="hangSX">Hãng Sản Xuất:</label>
        <input type="text" id="hangSX" name="hangSX" required><br><br>
        
        <label for="mau">Màu:</label>
        <input type="text" id="mau" name="mau" required><br><br>
        
        <input type="submit" value="Thêm Xe Máy">
    </form>
</body>
</html>