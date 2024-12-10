/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Test.KetNoi;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quant
 */
@WebServlet(urlPatterns = {"/XLThemNV"})
public class XLThemNV extends HttpServlet {

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
            out.println("<title>Servlet XLThemNV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet XLThemNV at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String manv = request.getParameter("manv");
        String hoten = request.getParameter("hoten");
        String chucvu = request.getParameter("chucvu");
        String hesl = request.getParameter("hesl");

        double heso = 0;
        try {
            heso = Float.parseFloat(hesl);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Äiá»ƒm khÃ´ng há»£p lá»‡. Vui lÃ²ng nháº­p sá»‘.");
            request.getRequestDispatcher("ThemNV.jsp").forward(request, response);
            return;
        }

        try (Connection c = KetNoi.KNCSDL()) {
            if (c != null) {
                String checkSql = "SELECT * FROM NhanVien WHERE MaNV = ?";
                try (PreparedStatement checkStmt = c.prepareStatement(checkSql)) {
                    checkStmt.setString(1, manv);
                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next()) {
                            request.setAttribute("message", "Them khong thanh cong do ma da ton tai");
                        } else {
                            String insertSql = "INSERT INTO NhanVien (MaNV, Hoten, ChucVu, HeSoLuong) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement insertStmt = c.prepareStatement(insertSql)) {
                                insertStmt.setString(1, manv);
                                insertStmt.setString(2, hoten);
                                insertStmt.setString(3, chucvu);
                                insertStmt.setFloat(4, Float.parseFloat(hesl));

                                int rowsInserted = insertStmt.executeUpdate();
                                if (rowsInserted > 0) {
                                    request.setAttribute("message", "Them thanh cong ma nhan vien: " + manv);
                                } else {
                                    request.setAttribute("message", "Them khong thanh cong.");
                                }
                            }
                        }
                    }
                }
            } else {
                request.setAttribute("message", "loi ket noi toi co so du lieu");
            }
        } catch (SQLException e) {
            request.setAttribute("message", "Loi: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(XLThemNV.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("ThemNV.jsp").forward(request, response);

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
