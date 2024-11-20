/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Test.KetNoi;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.*;
import java.util.HashMap;

/**
 *
 * @author quant
 */
@WebServlet(urlPatterns = {"/XLTimKiemSV"})
public class XLTimKiemSV extends HttpServlet {

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
            out.println("<title>Servlet XLTimKiemSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet XLTimKiemSV at " + request.getContextPath() + "</h1>");
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
        String maSV = request.getParameter("masv"); // Lấy mã sinh viên từ request
        List<Map<String, Object>> danhSachSinhVien = new ArrayList<>();
        List<Map<String, Object>> ketQuaTimKiem = new ArrayList<>();
        
        try (Connection con = KetNoi.KNCSDL()) {
            // Lấy toàn bộ danh sách sinh viên
            String queryDanhSach = "SELECT * FROM sinhvien";
            PreparedStatement psDanhSach = con.prepareStatement(queryDanhSach);
            ResultSet rsDanhSach = psDanhSach.executeQuery();
            
            while (rsDanhSach.next()) {
                Map<String, Object> sv = new HashMap<>();
                sv.put("MaSV", rsDanhSach.getString("MaSV"));
                sv.put("HoTen", rsDanhSach.getString("HoTen"));
                sv.put("NgaySinh", rsDanhSach.getDate("NgaySinh"));
                sv.put("DiemTB", rsDanhSach.getFloat("DiemTB"));
                danhSachSinhVien.add(sv);
            }

            // Nếu người dùng nhập mã sinh viên, tìm kiếm trong cơ sở dữ liệu
            if (maSV != null && !maSV.trim().isEmpty()) {
                String queryTimKiem = "SELECT * FROM sinhvien WHERE MaSV = ?";
                PreparedStatement psTimKiem = con.prepareStatement(queryTimKiem);
                psTimKiem.setString(1, maSV);
                ResultSet rsTimKiem = psTimKiem.executeQuery();

                while (rsTimKiem.next()) {
                    Map<String, Object> sv = new HashMap<>();
                    sv.put("MaSV", rsTimKiem.getString("MaSV"));
                    sv.put("HoTen", rsTimKiem.getString("HoTen"));
                    sv.put("NgaySinh", rsTimKiem.getDate("NgaySinh"));
                    sv.put("DiemTB", rsTimKiem.getFloat("DiemTB"));
                    ketQuaTimKiem.add(sv);
                }
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }

        // Gửi danh sách ban đầu và kết quả tìm kiếm sang JSP
        request.setAttribute("danhSachSinhVien", danhSachSinhVien);
        request.setAttribute("ketQuaTimKiem", ketQuaTimKiem);
        request.setAttribute("maSV", maSV); // Truyền lại mã sinh viên nhập vào
        request.getRequestDispatcher("TimKiemSV.jsp").forward(request, response);
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
        processRequest(request, response);
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
