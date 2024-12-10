<%@page import="Test.KetNoi"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tim Kiem San Pham</title>
    </head>
    <body>
        <h2>Tim Kiem San Pham</h2>
        <div class="search-form">
            <form action="TimKiemSP.jsp" method="get">
                <label>Nhap ten san pham: </label>
                <input type="text" id="sanpham" name="sanpham" 
                       placeholder="Nhap ten san pham" 
                       value="<%= request.getParameter("sanpham") != null ? request.getParameter("sanpham") : "" %>" required>
                <button type="submit">Tim Kiem</button>
            </form>
        </div>

        <%
        String sanpham = request.getParameter("sanpham");
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = KetNoi.KNCSDL();
            String queryDanhSach = "SELECT * FROM sanpham";
            ps = cn.prepareStatement(queryDanhSach);
            rs = ps.executeQuery();
        %>
        <h3>Danh sach san pham</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Ma San Pham</th>
                    <th>Ten San Pham</th>
                    <th>So Luong</th>
                    <th>Don Gia</th>
                    <th>Chiet Khau</th>
                </tr>
            </thead>
            <tbody>
                <%
                while (rs.next()) {
                    double dongia = rs.getDouble("DonGia");
                    int soluong = rs.getInt("SoLuong");
                    double chietkhau = soluong > 5 ? 0.05 * (soluong * dongia) : (soluong >= 10 ? 0.1 * (soluong * dongia) : 0);
                %>
                <tr>
                    <td><%= rs.getInt("MaSP") %></td>
                    <td><%= rs.getString("TenSP") %></td>
                    <td><%= rs.getInt("SoLuong") %></td>
                    <td><%= rs.getDouble("DonGia") %></td>
                    <td><%= (chietkhau > 0 ? chietkhau + " VND" : "Khong co chiet khau") %></td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
        <%
            if (sanpham != null && !sanpham.trim().isEmpty()) {
                String queryTimKiem = "SELECT * FROM sanpham WHERE TenSP = ?";
                ps = cn.prepareStatement(queryTimKiem);
                ps.setString(1, sanpham);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int masp = rs.getInt("MaSP");
                    int soluong = rs.getInt("SoLuong");
                    double dongia = rs.getDouble("DonGia");
                    double chietkhau = soluong > 5 ? 0.05 * (soluong * dongia) : (soluong >= 10 ? 0.1 * (soluong * dongia) : 0);
        %>
        <h3>Ket Qua Tim Kiem</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Ma San Pham</th>
                    <th>Ten San Pham</th>
                    <th>So Luong</th>
                    <th>Don Gia</th>
                    <th>Chiet Khau</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= masp %></td>
                    <td><%= sanpham %></td>
                    <td><%= soluong %></td>
                    <td><%= dongia %></td>
                    <td><%= (chietkhau > 0 ? chietkhau + " VND" : "Khong co chiet khau") %></td>
                </tr>
            </tbody>
        </table>
        <%
                } else {
        %>
        <p>Khong tim thay san pham voi ten: <%= sanpham %></p>
        <%
                }
            }
        } catch (SQLException ex) {
        %>
        <p>Loi ket noi toi co so du lieu: <%= ex.getMessage() %></p>
        <%
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        %>
    </body>
</html>
