<%-- 
    Document   : ThemNV.jsp
    Created on : Dec 10, 2024, 1:36:14 PM
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
        <h2>Them Nhan Vien</h2>
        <form action="XLThemNV" method="post">
            Ma Nhan Vien: <input type="text" name="manv" id="manv" value="<%= request.getAttribute("MaNV") != null ? request.getAttribute("MaNV"): "" %>" required><br>
            Ho Ten: <input type="text" name="hoten" id="hoten" value="<%= request.getAttribute("Hoten") != null ? request.getAttribute("Hoten"): "" %>" required><br>
            Chuc Vu: <input type="text" name="chucvu" id="chucvu" value="<%= request.getAttribute("ChucVu") != null ? request.getAttribute("ChucVu"): "" %>" required><br>
            He So Luong: <input type="number" step="0.1" name="hesl" id="hesl" value="<%= request.getAttribute("HeSoLuong") != null ? request.getAttribute("HeSoLuong"): "" %>" required=""><br>
            <input type="submit" value="Them" />
        </form>
        <%
            String message = (String)request.getAttribute("message");
            if(message!=null){
        %>
        <p style="color:red;"><%= message %></p>
        <%
            }
        %>
        <h2>Danh Dach Nhan Vien</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Ma Nhan Vien</th>
                    <th>Ho Ten</th>
                    <th>Chuc Vu</th>
                    <th>He So Luong</th>
                    <th>Phu Cap</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Connection con = null;
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    try{
                        con = KetNoi.KNCSDL();
                        
                        String query = "SELECT * FROM NhanVien";
                        ps = con.prepareStatement(query);
                        rs = ps.executeQuery(query);
                        while(rs.next()){
                            String chucvu = rs.getString("ChucVu");
                            int phucap = chucvu.equalsIgnoreCase("Giam Doc")?10000000:
                                           chucvu.equalsIgnoreCase("Pho Giam Doc")?7000000:
                                           chucvu.equalsIgnoreCase("Truong Phong")?5000000:1000000;
                %>
                <tr>
                    <td><%=rs.getInt("MaNV")%></td>
                    <td><%=rs.getString("Hoten")%></td>
                    <td><%=rs.getString("ChucVu")%></td>
                    <td><%=rs.getFloat("HeSoLuong")%></td>
                    <td><%= phucap%></td>
                </tr>
                <%
                        }
                    }catch(SQLException e){
                       e.printStackTrace();
                    }finally{
                        if(con != null) con.close();
                        if(rs != null) rs.close();
                        if(ps != null) ps.close();
                    }
                %>
            </tbody>
        </table>

    </body>
</html>
