<%-- 
    Document   : TimKiem1
    Created on : Nov 5, 2024, 1:32:36 PM
    Author     : quant
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="ThuVien.DienThoai" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh Sách Điện Thoại</title>
        <style>
            /* Đặt font mặc định cho trang */
            body {
                font-family: Arial, sans-serif;
                background-color: #fff;
                margin: 20px;
            }

            h2, h3, h1 {
                color: #333;
                margin-bottom: 20px;
            }

            /* Định dạng form tìm kiếm */
            form {
                margin-bottom: 30px;
            }

            input[type="text"] {
                padding: 8px;
                border: 1px solid #000;
                border-radius: 4px;
                font-size: 14px;
            }

            input[type="submit"] {
                padding: 8px 16px;
                border: 1px solid #000;
                border-radius: 4px;
                background-color: #fff;
                cursor: pointer;
                font-size: 14px;
            }

            input[type="submit"]:hover {
                background-color: #f0f0f0;
            }

            /* Định dạng bảng */
            table {
                width: 100%;
                border-collapse: collapse; /* Gộp các đường viền */
                margin-bottom: 30px;
            }

            table, th, td {
                border: 1px solid #000; /* Đặt viền cho bảng, tiêu đề và ô dữ liệu */
            }

            th, td {
                padding: 10px;
                text-align: center;
                font-size: 14px;
            }

            th {
                background-color: #f2f2f2; /* Màu nền nhẹ cho tiêu đề */
            }

            /* Định dạng thông báo */
            .alert {
                color: #d9534f;
                font-weight: bold;
                text-align: center;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <h2>DANH SÁCH ĐIỆN THOẠI</h2>
        <h3>Trần Quang Quân - DHTI15A5HN - 21103100304</h3>

        <!-- Form Tìm Kiếm -->
        <form action="XLTimKiem1" method="POST">
            Nhập mã điện thoại: <input type="text" name="maDienThoai" placeholder="Nhập mã điện thoại..."/>
            <input type="submit" value="Tìm kiếm"/>
        </form>

        <!-- Bảng hiển thị toàn bộ dữ liệu -->
        <table>
            <thead>
                <tr>
                    <th>Mã</th>
                    <th>Tên</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                </tr>
            </thead>
            <tbody>
                <%
                    DecimalFormat df = new DecimalFormat("#,###");
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
                            out.println("<td>" + df.format(rs.getDouble("Dongia")) + "</td>");
                            out.println("</tr>");
                        }
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                %>
            </tbody>
        </table>

        <!-- Bảng Tìm Kiếm -->
        <h1>Kết quả Tìm Kiếm</h1>
        <table>
            <thead>
                <tr>
                    <th>Mã</th>
                    <th>Tên</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                </tr>
            </thead>
            <tbody>
                <%  
                    String maDienThoai = request.getParameter("maDienThoai");
                    ArrayList<DienThoai> arrHT = (ArrayList<DienThoai>) request.getAttribute("arrsv");
                    if(arrHT != null && !arrHT.isEmpty()) {
                        for(DienThoai dt : arrHT) {
                %>
                <tr>
                    <td><%= dt.getMa() %></td>
                    <td><%= dt.getTen() %></td>
                    <td><%= dt.getSoluong() %></td>
                    <td><%= df.format(dt.getDongia()) %></td>
                </tr>
                <%
                        }
                    } else if (arrHT != null) { // Nếu đã tìm kiếm nhưng không có kết quả
                %>
                <tr>
                    <td colspan="4" class="alert">Không có dữ liệu của mã "<%= maDienThoai %>" đó trong bảng</td>
                </tr>
                <%
                    } else {
                %>
                <tr>
                    <td colspan="4" class="alert">Vui lòng nhập mã để tìm kiếm</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
