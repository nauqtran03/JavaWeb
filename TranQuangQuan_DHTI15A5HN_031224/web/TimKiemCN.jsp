<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="Test.KetNoi"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tìm kiếm công nhân</title>
    <style>
                body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }

        h2 {
            text-align: center;
            color: #333;
            margin: 20px 0;
        }

        .search-form {
            text-align: center;
            margin-bottom: 20px;
        }

        .search-form input[type="text"] {
            width: 300px;
            padding: 8px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .search-form button {
            padding: 8px 15px;
            border: none;
            background-color: #4CAF50;
            color: white;
            font-size: 14px;
            border-radius: 5px;
            cursor: pointer;
        }

        .search-form button:hover {
            background-color: #45a049;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        .ket-qua-tim-kiem h3, 
        .danh-sach-cong-nhan h3 {
            text-align: center;
            color: #333;
            margin-top: 20px;
        }

        p {
            text-align: center;
            font-size: 16px;
            color: red;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center;">Tìm kiếm thông tin công nhân</h2>

    <!-- Form tìm kiếm -->
    <div class="search-form">
        <form action="TimKiemCN.jsp" method="get">
            <label for="masv">Nhập tên công nhân:</label>
            <!-- Giữ lại giá trị đã nhập vào ô input -->
            <input type="text" id="hoten" name="hoten" placeholder="Nhập tên công nhân" value="${param.hoten}" required>
            <button type="submit">Tìm kiếm</button>
        </form>
    </div>

    <% 
        String hoten = request.getParameter("hoten");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = KetNoi.KNCSDL();
            
            // Hiển thị danh sách toàn bộ sinh viên
            String queryDanhSach = "SELECT * FROM congnhan";
            ps = con.prepareStatement(queryDanhSach);
            rs = ps.executeQuery();
    %>

    <h3 style="text-align: center;">Danh sách công nhân</h3>
    <table>
        <thead>
            <tr>
                <th>Mã Công Nhân</th>
                <th>Họ và tên</th>
                <th>Giới Tính</th>
                <th>Số Giờ Làm</th>
                <th>Tiền Thưởng</th>
            </tr>
        </thead>
        <tbody>
            <% 
                while (rs.next()) { 
                     int sogiolam = rs.getInt("SoGioLam");
                    int tienThuong = sogiolam >= 40 ? 500000 : (sogiolam >= 30 ? 300000 : (sogiolam >=20 ? 200000 : 0));
            %>
            <tr>
                <td><%= rs.getInt("MaCN") %></td>
                <td><%= rs.getString("HoTen") %></td>
                <td><%= rs.getString("GioiTinh") %></td>
                <td><%= rs.getInt("SoGioLam") %></td>
                <td><%= (tienThuong > 0 ? tienThuong + " VNĐ" : "Không có tiền thưởng") %></td>
            </tr>
            <% 
                } 
            %>
        </tbody>
    </table>

    <% 
            // Đóng ResultSet để chuẩn bị cho phần tìm kiếm
            rs.close();
            
            // Xử lý tìm kiếm nếu người dùng nhập mã sinh viên
            if (hoten != null && !hoten.trim().isEmpty()) {
                String queryTimKiem = "SELECT * FROM congnhan WHERE HoTen = ?";
                ps = con.prepareStatement(queryTimKiem);
                ps.setString(1, hoten);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    int macn = rs.getInt("MaCN");
                    String gioitinh = rs.getString("GioiTinh");
                    int sogiolam = rs.getInt("SoGioLam");
                    int tienthuong = sogiolam >= 40 ? 500000 : (sogiolam >= 30 ? 300000 : (sogiolam >=20 ? 200000 : 0));
    %>
    <h3 style="text-align: center;">Kết quả tìm kiếm</h3>
    <table>
        <thead>
            <tr>
                <th>Mã Công Nhân</th>
                <th>Họ và tên</th>
                <th>GioiTinh</th>
                <th>Số Giờ Làm</th>
                <th>Tiền Thưởng</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><%= macn %></td>
                <td><%= hoten %></td>
                <td><%= gioitinh %></td>
                <td><%= sogiolam %></td>
                <td><%= (tienthuong > 0 ? tienthuong + " VNĐ" : "Không có tiền thưởng") %></td>
            </tr>
        </tbody>
    </table>
    <% 
                } else { 
    %>
    <p style="text-align: center; color: red;">Không tìm thấy công nhân với tên: <%= hoten %></p>
    <% 
                } 
            } 
        } catch (SQLException e) { 
    %>
    <p style="text-align: center; color: red;">Lỗi kết nối cơ sở dữ liệu: <%= e.getMessage() %></p>
    <% 
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    %>
</body>
</html>
