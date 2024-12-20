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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quant
 */
@WebServlet(urlPatterns = {"/XLTimKiemSP"})
public class XLTimKiemSP extends HttpServlet {

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
            out.println("<title>Servlet XLTimKiemSP</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet XLTimKiemSP at " + request.getContextPath() + "</h1>");
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
        String sanpham = request.getParameter("sanpham"); 
        List<Map<String, Object>> danhSachSanPham = new ArrayList<>();
        List<Map<String, Object>> ketQuaTimKiem = new ArrayList<>();
        
        try (Connection con = KetNoi.KNCSDL()) {
            // Lấy toàn bộ danh sách sinh viên
            String queryDanhSach = "SELECT * FROM sanpham";
            PreparedStatement psDanhSach = con.prepareStatement(queryDanhSach);
            ResultSet rsDanhSach = psDanhSach.executeQuery();
            
            while (rsDanhSach.next()) {
                Map<String, Object> cn = new HashMap<>();
                cn.put("MaSP", rsDanhSach.getInt("MaSP"));
                cn.put("TenSP", rsDanhSach.getString("TenSP"));
                cn.put("SoLuong", rsDanhSach.getInt("SoLuong"));
                cn.put("DonGia", rsDanhSach.getDouble("DonGia"));
                danhSachSanPham.add(cn);
            }

            // Nếu người dùng nhập mã sinh viên, tìm kiếm trong cơ sở dữ liệu
            if (sanpham != null && !sanpham.trim().isEmpty()) {
                String queryTimKiem = "SELECT * FROM sanpham WHERE HoTen LIKE ?";
                PreparedStatement psTimKiem = con.prepareStatement(queryTimKiem);
                psTimKiem.setString(1, sanpham);
                ResultSet rsTimKiem = psTimKiem.executeQuery();

                while (rsTimKiem.next()) {
                    Map<String, Object> cn = new HashMap<>();
                    cn.put("MaSP", rsTimKiem.getInt("MaSP"));
                    cn.put("TenSP", rsTimKiem.getString("TenSP"));
                    cn.put("SoLuong", rsTimKiem.getInt("SoLuong"));
                    cn.put("SoGioLam", rsTimKiem.getInt("SoGioLam"));
                    ketQuaTimKiem.add(cn);
                }
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }

        // Gửi danh sách ban đầu và kết quả tìm kiếm sang JSP
        request.setAttribute("danhSachSanPham", danhSachSanPham);
        request.setAttribute("ketQuaTimKiem", ketQuaTimKiem);
        request.setAttribute("sanpham", sanpham); // Truyền lại mã sinh viên nhập vào
        request.getRequestDispatcher("TimKiemSP.jsp").forward(request, response);
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
