<%-- 
    Document   : TimKiemSV
    Created on : Dec 9, 2024, 6:57:56 PM
    Author     : quant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="Test.KetNoi"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Tim kiem thong tin sinh vien</h2>
        <div class="search-form">
            <form action="TimKiemSV.jsp" method="get">
                <label>Nhap ten sinh vien</label>
            <input type="text" name="hoten" id="hoten" placeholder="Nhap ten sinh vien" value="" required>
            <input type="submit" value="Tim Kiem" >
            </form>
        </div>
        <%
            String hoten = request.getParameter("hoten");
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try{
                con = KetNoi.KNCSDL();
                String queryDanhSach = "SELECT * FROM sinhvien";
                ps = con.prepareStatement(queryDanhSach);
                rs = ps.executeQuery();
        %>
        <h3>Danh sach sinh vien</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Ma Sinh Vien</th>
                    <th>Ho Ten</th>
                    <th>Ngay Sinh</th>
                    <th>Diem Trung Binh</th>
                    <th>Hoc Bong</th>
                </tr>
            </thead>
            <tbody>
                <%
                    while(rs.next()){
                        float diemtb = rs.getFloat("DiemTB");
                        float thuong = diemtb >= 9 ? 5000000 :(diemtb >= 8.5 ? 3000000:0);
                %>
                <tr>
                    <td><%= rs.getInt("MaSV") %></td>
                    <td><%= rs.getString("HoTen") %></td>
                    <td><%= rs.getDate("NgaySinh") %></td>
                    <td><%= rs.getFloat("DiemTB") %></td>
                    <td><%=(thuong > 0 ? thuong +"VND" : "Khong co") %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            rs.close();
            
            if(hoten != null && !hoten.trim().isEmpty()){
                String queryTimKiem = "SELECT * FROM sinhvien WHERE hoten LIKE ? ";
                ps = con.prepareStatement(queryTimKiem);
                ps.setString(1, "%" + hoten + "%");
                rs = ps.executeQuery();
            if(rs.next()){
                int masv = rs.getInt("MaSV");
                Date ngaysinh = rs.getDate("NgaySinh");
                float diemtb = rs.getFloat("DiemTB");
                float thuong = thuong = diemtb >= 9 ? 5000000 :(diemtb >= 8.5 ? 3000000:0);
        %>
        <h3>Ket Qua Tim Kiem</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Ma Sinh Vien</th>
                    <th>Ho Ten</th>
                    <th>Ngay Sinh</th>
                    <th>Diem Trung Binh</th>
                    <th>Hoc Bong</th>

                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= masv %></td>
                    <td><%= hoten %></td>
                    <td><%= ngaysinh %></td>
                    <td><%= diemtb %></td>
                    <td><%=(thuong > 0 ? thuong +"VND" : "Khong co") %></td>
                </tr>
            </tbody>
        </table>
        <%
            }else{
        %>
        <p>Khong tim thay sinh vien co ten: <%= hoten %></p>
        <%
                }
            }
        %>
        <%
            }catch(SQLException ex){
        %>
        <<p>Loi Ket Noi Toi Co So Du Lieu: <%= ex.getMessage()%></p>>
        <%
            }finally{
            try{
            if(rs !=null) rs.close();
            if(ps !=null) ps.close();
            if(con != null) con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        %>
    </body>
</html>
