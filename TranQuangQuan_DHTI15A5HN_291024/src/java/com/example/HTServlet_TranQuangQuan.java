/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.System.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quant
 */
@WebServlet(urlPatterns = {"/HTServlet_TranQuangQuan"})
public class HTServlet_TranQuangQuan extends HttpServlet {
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/qlxemay";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "";
    private static final String INSERT_XEMAY_SQL = "INSERT INTO xemay (Ma, Ten, HangSX, Mau) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_XEMAY_SQL = "SELECT * FROM xemay";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HTServlet_TranQuangQuan</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HTServlet_TranQuangQuan at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    String ten = request.getParameter("ten");
    String hangSX = request.getParameter("hangSX");
    String mau = request.getParameter("mau");
    String ma = request.getParameter("ma"); // Lấy mã từ form

    try (PrintWriter out = response.getWriter()) {
        out.println("<html><body>");
        out.println("<h2>Đang thực hiện thêm xe máy...</h2>");
        out.println("<p>Tham số đầu vào: Mã = " + ma + ", Tên = " + ten + ", Hãng SX = " + hangSX + ", Màu = " + mau + "</p>");

        // Tải driver JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            out.println("<p>Lỗi tải driver JDBC: " + e.getMessage() + "</p>");
            return;
        }

        // Kết nối cơ sở dữ liệu
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            

            // Thêm xe máy mới vào cơ sở dữ liệu
            PreparedStatement insertStatement = connection.prepareStatement(INSERT_XEMAY_SQL);
            insertStatement.setString(1, ma); // Thêm mã vào câu lệnh INSERT
            insertStatement.setString(2, ten);
            insertStatement.setString(3, hangSX);
            insertStatement.setString(4, mau);
            int rowsInserted = insertStatement.executeUpdate();
            insertStatement.close();


            // Lấy toàn bộ danh sách xe máy sau khi thêm
            List<XeMay> xemayList = new ArrayList<>();
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_ALL_XEMAY_SQL);
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {
                String maXe = rs.getString("Ma");
                String tenXe = rs.getString("Ten");
                String hangSXxe = rs.getString("HangSX");
                String mauXe = rs.getString("Mau");
                xemayList.add(new XeMay(maXe, tenXe, hangSXxe, mauXe));
            }

            rs.close();
            selectStatement.close();

            // Hiển thị danh sách xe máy
            out.println("<h2>Danh sách xe máy sau khi thêm</h2>");
            out.println("<table border='1'><tr><th>Mã</th><th>Tên</th><th>Hãng Sản Xuất</th><th>Màu</th></tr>");

            for (XeMay xe : xemayList) {
                out.println("<tr><td>" + xe.getMa() + "</td><td>" + xe.getTen() + "</td><td>" + xe.getHangSX() + "</td><td>" + xe.getMau() + "</td></tr>");
            }

            out.println("</table>");
            out.println("<br><a href='Them_TranQuangQuan.jsp'>Thêm xe máy mới</a>");
            out.println("</body></html>");
        } catch (SQLException e) {
            out.println("<p>Lỗi kết nối cơ sở dữ liệu hoặc truy vấn SQL: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
