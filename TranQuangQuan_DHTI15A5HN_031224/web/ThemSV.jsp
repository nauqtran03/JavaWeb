<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@ page import="Test.KetNoi" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Thí Sinh</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 20px;
            }
            h2 {
                text-align: center;
                color: #333;
            }
            form {
                max-width: 600px;
                margin: 20px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            form input, form button {
                width: 100%;
                padding: 10px;
                margin: 8px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            form button {
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
            }
            form button:hover {
                background-color: #45a049;
            }
            p {
                font-size: 1.2em;
                text-align: center;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 12px;
                text-align: center;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
        </style>
    </head>
    <body>
        <h2>Thêm Sinh Viên</h2>
        <form action="XLThemSV" method="post">
            Mã Sinh Viên: <input type="text" name="MSSV" value="<%= request.getAttribute("MSSV") != null ? request.getAttribute("MSSV") : "" %>" required><br>
            Họ tên: <input type="text" name="Hoten" value="<%= request.getAttribute("Hoten") != null ? request.getAttribute("Hoten") : "" %>" required><br>
            Lớp: <input type="text" name="Lop" value="<%= request.getAttribute("Lop") != null ? request.getAttribute("Lop") : "" %>" required><br>
            Điểm Trung Bình: <input type="number" name="DiemTB" value="<%= request.getAttribute("DiemTB") != null ? request.getAttribute("DiemTB") : "" %>" step="0.1" required><br>
            <button type="submit">Thêm</button>
        </form>
        
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <p style="color: red;"><%= message %></p>
        <%
            }
        %>

        <h2>Danh sách sinh viên</h2>
        <table>
            <tr>
                <th>MSSV</th>
                <th>Họ tên</th>
                <th>Lớp</th>
                <th>Điểm Trung Bình</th>
                <th>Học Lực</th>
            </tr>
            <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    conn = KetNoi.KNCSDL();
                    stmt = conn.createStatement();
                    String sql = "SELECT * FROM Student";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        float diemTB = rs.getFloat("DiemTB");
                        String hocLuc = diemTB >= 8.5 ? "Giỏi" :
                                        diemTB >= 6.5 ? "Khá" :
                                        diemTB >= 5.0 ? "Trung Bình" : "Yếu";
            %>
            <tr>
                <td><%= rs.getString("MSSV") %></td>
                <td><%= rs.getString("HoTen") %></td>
                <td><%= rs.getString("Lop") %></td>
                <td><%= diemTB %></td>
                <td><%= hocLuc %></td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                }
            %>
        </table>
    </body>
</html>
