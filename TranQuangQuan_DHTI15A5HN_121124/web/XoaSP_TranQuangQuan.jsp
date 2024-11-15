<%-- 
    Document   : XoaSP_TranQuangQuan
    Created on : Nov 12, 2024, 9:10:32 AM
    Author     : quant
--%>

<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách Sản Phẩm</title>
        <style>
            body {
    font-family: Arial, sans-serif;
    background-color: #f8f8f8;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    margin: 0;
}

/* Khung chính của form */
.container {
    background-color: #fff;
    width: 500px;
    padding: 20px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    text-align: center;
}

/* Tiêu đề chính */
h2 {
    color: #e91e63;
    margin-bottom: 20px;
}

/* Form nhập mã sản phẩm */
form {
    margin-bottom: 20px;
}

form input[type="text"] {
    padding: 8px;
    font-size: 16px;
    width: 60%;
    border: 1px solid #ddd;
    border-radius: 4px;
    margin-right: 10px;
}

form button {
    padding: 8px 15px;
    font-size: 16px;
    background-color: #e91e63;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

form button:hover {
    background-color: #d81b60;
}

/* Tiêu đề bảng */
h3 {
    color: #e91e63;
    margin-top: 20px;
}

/* Định dạng bảng */
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 10px;
    text-align: center;
}

th {
    background-color: #e91e63;
    color: #fff;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}
        </style>
    </head>
    <body>
        <div class="container">
            <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                String dbURL = "jdbc:mysql://localhost:3306/QLSanPham";
                String dbUser = "root";
                String dbPass = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
                    stmt = conn.createStatement();
                    String sql = "SELECT * FROM SanPham";
                    rs = stmt.executeQuery(sql);
            %>
            <h2>Danh sách sản phẩm</h2>
            <table>
                <tr>
                    <th>Mã SP</th>
                    <th>Tên SP</th>
                    <th>Loại SP</th>
                    <th>Giá</th>
                </tr>
                <%
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("MaSP") %></td>
                    <td><%= rs.getString("TenSP") %></td>
                    <td><%= rs.getString("LoaiSP") %></td>
                    <td><%= rs.getDouble("Gia") %> VNĐ</td>
                </tr>
                <%
                    }
                %>
            </table>
            <form action="HienThiSP_TranQuangQuan" method="post">
                <label>Nhập mã sản phẩm cần xóa:</label>
                <input type="text" name="maSP" required>
                <input type="submit" value="Xóa sản phẩm">
            </form>
            <%
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<p>Có lỗi xảy ra: " + e.getMessage() + "</p>");
                } finally {
                    if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                    if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            %>
        </div>
    </body>
</html>
