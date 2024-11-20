<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="Test.KetNoi"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tìm kiếm sinh viên</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .search-form {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center;">Tìm kiếm thông tin sinh viên</h2>

    <!-- Form tìm kiếm -->
    <div class="search-form">
        <form action="TimKiemSV.jsp" method="get">
            <label for="masv">Nhập mã sinh viên:</label>
            <!-- Giữ lại giá trị đã nhập vào ô input -->
            <input type="text" id="masv" name="masv" placeholder="Nhập mã sinh viên" value="${param.masv}" required>
            <button type="submit">Tìm kiếm</button>
        </form>
    </div>

    <% 
        String masv = request.getParameter("masv");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = KetNoi.KNCSDL();
            
            // Hiển thị danh sách toàn bộ sinh viên
            String queryDanhSach = "SELECT * FROM sinhvien";
            ps = con.prepareStatement(queryDanhSach);
            rs = ps.executeQuery();
    %>

    <h3 style="text-align: center;">Danh sách sinh viên</h3>
    <table>
        <thead>
            <tr>
                <th>Mã SV</th>
                <th>Họ và tên</th>
                <th>Ngày sinh</th>
                <th>Điểm trung bình</th>
                <th>Học bổng</th>
            </tr>
        </thead>
        <tbody>
            <% 
                while (rs.next()) { 
                    float diemTB = rs.getFloat("DiemTB");
                    int hocBong = diemTB >= 9 ? 5000000 : (diemTB >= 8.5 ? 3000000 : 0);
            %>
            <tr>
                <td><%= rs.getString("MaSV") %></td>
                <td><%= rs.getString("HoTen") %></td>
                <td><%= rs.getDate("NgaySinh") %></td>
                <td><%= diemTB %></td>
                <td><%= (hocBong > 0 ? hocBong + " VNĐ" : "Không có học bổng") %></td>
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
            if (masv != null && !masv.trim().isEmpty()) {
                String queryTimKiem = "SELECT * FROM sinhvien WHERE MaSV = ?";
                ps = con.prepareStatement(queryTimKiem);
                ps.setString(1, masv);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    String hoTen = rs.getString("HoTen");
                    Date ngaySinh = rs.getDate("NgaySinh");
                    float diemTB = rs.getFloat("DiemTB");
                    int hocBong = diemTB >= 9 ? 5000000 : (diemTB >= 8.5 ? 3000000 : 0);
    %>
    <h3 style="text-align: center;">Kết quả tìm kiếm</h3>
    <table>
        <thead>
            <tr>
                <th>Mã SV</th>
                <th>Họ và tên</th>
                <th>Ngày sinh</th>
                <th>Điểm trung bình</th>
                <th>Học bổng</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><%= masv %></td>
                <td><%= hoTen %></td>
                <td><%= ngaySinh %></td>
                <td><%= diemTB %></td>
                <td><%= (hocBong > 0 ? hocBong + " VNĐ" : "Không có học bổng") %></td>
            </tr>
        </tbody>
    </table>
    <% 
                } else { 
    %>
    <p style="text-align: center; color: red;">Không tìm thấy sinh viên với mã: <%= masv %></p>
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
