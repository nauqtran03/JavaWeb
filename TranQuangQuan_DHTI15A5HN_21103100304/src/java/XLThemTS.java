/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Test.KetNoi;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author quant
 */
@WebServlet(urlPatterns = {"/XLThemTS"})
public class XLThemTS extends HttpServlet {

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
            out.println("<title>Servlet XLThemTS</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet XLThemTS at " + request.getContextPath() + "</h1>");
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
         response.setContentType("text/html;charset=UTF-8");

        // Lấy dữ liệu từ form
        String sbd = request.getParameter("SBD");
        String hoTen = request.getParameter("HoTen");
        String diemToan = request.getParameter("DiemToan");
        String diemVan = request.getParameter("DiemVan");

        Connection ketnoi = null;
        try {
            // Kết nối đến cơ sở dữ liệu
            ketnoi = KetNoi.KNCSDL();

            // Kiểm tra trùng số báo danh
            String checksql = "SELECT COUNT(*) FROM thisinh WHERE SBD = ?";
            PreparedStatement checkstm = ketnoi.prepareStatement(checksql);
            checkstm.setInt(1, Integer.parseInt(sbd));
            ResultSet rs = checkstm.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                // Trùng số báo danh, thiết lập thông báo và giữ dữ liệu
                request.setAttribute("message", "Số báo danh "+ sbd+ " đã tồn tại!");
                request.setAttribute("SBD", sbd);
                request.setAttribute("HoTen", hoTen);
                request.setAttribute("DiemToan", diemToan);
                request.setAttribute("DiemVan", diemVan);
            } else {
                // Thực hiện thêm thí sinh
                String insertsql = "INSERT INTO thisinh (SBD, HoTen, DiemToan, DiemVan) VALUES (?, ?, ?, ?)";
                PreparedStatement insertstm = ketnoi.prepareStatement(insertsql);
                insertstm.setInt(1, Integer.parseInt(sbd));
                insertstm.setString(2, hoTen);
                insertstm.setFloat(3, Float.parseFloat(diemToan));
                insertstm.setFloat(4, Float.parseFloat(diemVan));

                insertstm.executeUpdate();
                // Cập nhật thông báo thành công và giữ lại dữ liệu
                request.setAttribute("message", "Thêm thành công số báo danh: " + sbd);
                request.setAttribute("SBD", sbd);
                request.setAttribute("HoTen", hoTen);
                request.setAttribute("DiemToan", diemToan);
                request.setAttribute("DiemVan", diemVan);
            }
        } catch (Exception e) {
            // Xử lý lỗi
            request.setAttribute("message", "Lỗi: " + e.getMessage());
            request.setAttribute("SBD", sbd);
            request.setAttribute("HoTen", hoTen);
            request.setAttribute("DiemToan", diemToan);
            request.setAttribute("DiemVan", diemVan);
        } finally {
            try {
                if (ketnoi != null) {
                    ketnoi.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // Chuyển hướng lại trang ThemTS.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("ThemTS.jsp");
        dispatcher.forward(request, response);
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
