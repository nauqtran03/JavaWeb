<%-- 
    Document   : TimKiemHD
    Created on : Dec 9, 2024, 10:55:25 PM
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
        <h2>Tim kiem hoa don:</h2>
        <div>
            <form action="TimKiemHD.jsp" method="get">
                <label>Nhap ma hoa don: </label>
                <input type="text" name="mahd" id="mahd" placeholder="Nhap ma hoa don" value="" required= />
                <input type="submit" value="Tim Kiem" />
            </form>
        </div>
        
        <%
            String mahd = request.getParameter("mahd");
            Connection cn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try{
                cn = KetNoi.KNCSDL();
                String queryDanhSach = "SELECT * FROM hoadon";
                ps = cn.prepareStatement(queryDanhSach);
                rs = ps.executeQuery();
                
        %>
        <h3>Danh sach hoa don</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Ma Hoa Don</th>
                    <th>Ma Khach Hang</th>
                    <th>Ngay Ghi Hoa Don</th>
                    <th>So Dien</th>
                    <th>Thanh Tien</th>
                </tr>
            </thead>
            <tbody>
                <%
                    while(rs.next()){
                    int sodien = rs.getInt("SoKW");
                    int thanhtien = sodien * 2000;
                %>
                <tr>
                    <td><%= rs.getInt("MaHD") %></td>
                    <td><%= rs.getString("MaKH") %></td>
                    <td><%= rs.getDate("NgayHD") %></td>
                    <td><%= rs.getInt("SoKW") %></td>
                    <td><%= thanhtien %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            rs.close();
            if(mahd != null && !mahd.trim().isEmpty()){
            
            String queryTimKiem = "SELECT * FROM hoadon WHERE MaHD LIKE ?";
            ps = cn.prepareStatement(queryTimKiem);
            ps.setString(1, "%"+mahd+"%");
            rs = ps.executeQuery();
            
            if(rs.next()){
                String makh = rs.getString("MaKH");
                Date ngayhd = rs.getDate("NgayHD");
                int sodien = rs.getInt("SoKW");
                int thanhtien = thanhtien = sodien * 2000;
        %>
        <h3>Ket Qua Tim Kiem</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Ma Hoa Don</th>
                    <th>Ma Khach Hang</th>
                    <th>Ngay Ghi Hoa Don</th>
                    <th>So Dien</th>
                    <th>Thanh Tien</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= mahd %></td>
                    <td><%= makh %></td>
                    <td><%= ngayhd %></td>
                    <td><%= sodien %></td>
                    <td><%= thanhtien %></td>
                </tr>
            </tbody>
        </table>
        <%
            }else{
        %>
        <p>Khong tim thay ma hoa don: <%= mahd %></p>
        <%
            }
          }
        %>
        <%
            }catch(SQLException ex){
        %>
        <<p>Loi Ket Noi Toi Co So Du Lieu: <%= ex.getMessage()%></p>>
        <%
            }
        %>
    </body>
</html>
