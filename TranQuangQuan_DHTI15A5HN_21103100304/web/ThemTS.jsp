<%-- 
    Document   : ThemTS
    Created on : Nov 19, 2024, 9:16:25 AM
    Author     : quant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@ page import="Test.KetNoi" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <style>
            /* Định dạng cho trang */
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

            /* Định dạng cho form */
            form {
                max-width: 600px;
                margin: 20px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            form input {
                width: 100%;
                padding: 10px;
                margin: 8px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            form button {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            form button:hover {
                background-color: #45a049;
            }

            /* Định dạng cho thông báo lỗi hoặc thành công */
            p {
                font-size: 1.2em;
                font-weight: bold;
                text-align: center;
            }

            p.style {
                color: red;
            }

            /* Định dạng cho bảng */
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

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #ddd;
            }
        </style>
    </head>
    <body>
        <h2>Thêm Thí Sinh</h2>
        <form action="XLThemTS" method="post">
            Số báo danh: 
            <input type="text" name="SBD" value="<%= request.getAttribute("SBD") != null ? request.getAttribute("SBD") : ""%>" required><br>
            Họ tên: 
            <input type="text" name="HoTen" value="<%= request.getAttribute("HoTen") != null ? request.getAttribute("HoTen") : ""%>" required><br>
            Điểm Toán: 
            <input type="number" name="DiemToan" step="0.1" value="<%= request.getAttribute("DiemToan") != null ? request.getAttribute("DiemToan") : ""%>" required><br>
            Điểm Văn: 
            <input type="number" name="DiemVan" step="0.1" value="<%= request.getAttribute("DiemVan") != null ? request.getAttribute("DiemVan") : ""%>" required><br>
            <button type="submit">Thêm</button>
        </form>

        <%-- Hiển thị thông báo lỗi hoặc thành công --%>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <p style="color: red;"><%= message%></p>
        <%
            }
        %>

        <h2>Danh sách thí sinh</h2>
        <table border="1">
            <tr>
                <th>SBD</th>
                <th>Họ tên</th>
                <th>Điểm Toán</th>
                <th>Điểm Văn</th>
                <th>Kết quả</th>
            </tr>
            <%
                Connection ketnoi = null;
                Statement statement = null;
                ResultSet resultSet = null;

                try {
                    ketnoi = KetNoi.KNCSDL();
                    statement = ketnoi.createStatement();

                    String sql = "SELECT * FROM thisinh";

                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        float diemToan = resultSet.getFloat("DiemToan");
                        float diemVan = resultSet.getFloat("DiemVan");
                        String ketQua = (diemToan + diemVan >= 10) ? "Đỗ" : "Trượt";
            %>
            <tr>
                <td><%= resultSet.getInt("SBD")%></td>
                <td><%= resultSet.getString("HoTen")%></td>
                <td><%= diemToan%></td>
                <td><%= diemVan%></td>
                <td><%= ketQua%></td>
            </tr>
            <%
                    }
                    ketnoi.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </table>
    </body>
</html>
