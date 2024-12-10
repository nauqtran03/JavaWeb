<%-- 
    Document   : XoaDN
    Created on : Dec 10, 2024, 11:48:07 PM
    Author     : quant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="Test.KetNoi" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Xoa doanh nghiep</h2>
        <form action="XLXoaDN" method="post">
            Ma Doanh Nghiep: <input type="text" name="madn" required>
            <input type="submit" value="Xoa" />
        </form>
        <%
            String message = (String) request.getAttribute("message");
            if(message != null){
        %>
        <p style="color:red;"><%=message%></p>
        <%
            }
        %>
        <h2>Danh sach doanh nghiep</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Ma Doanh Nghiep</th>
                    <th>Ten Doanh Nghiep</th>
                    <th>Von Dau Tu</th>
                    <th>Doanh Thu</th>
                    <th>Tien Thue</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Connection con = null;
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    try{
                        con = KetNoi.KNCSDL();
                        String query = "SELECT * FROM DoanhNghiep";
                        ps = con.prepareStatement(query);
                        rs= ps.executeQuery();
                        while(rs.next()){
                            long von = rs.getLong("SoVDT");
                            double doanhthu = rs.getDouble("DoanhThu");
                            double thue = von >500000000 ? (0.1*doanhthu):(0.05*doanhthu);
                %>
                <tr>
                    <td><%= rs.getInt("MaDN") %></td>
                    <td><%= rs.getString("TenDN") %></td>
                    <td><%= rs.getDouble("SoVDT") %></td>
                    <td><%= rs.getDouble("DoanhThu") %></td>
                    <td><%= thue %></td>
                </tr>
                <%
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }finally{
                        if(con !=null) con.close();
                        if(ps !=null) con.close();
                        if(rs !=null) con.close();
                    }
                %>
            </tbody>
        </table>

    </body>
</html>
