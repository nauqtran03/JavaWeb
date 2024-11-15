/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
@WebServlet(urlPatterns = {"/HienThiSP_TranQuangQuan"})
public class HienThiSP_TranQuangQuan extends HttpServlet {

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
            out.println("<title>Servlet HienThiSP_TranQuangQuan</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HienThiSP_TranQuangQuan at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        String maSP = request.getParameter("maSP");
        
        String dbURL = "jdbc:mysql://localhost:3306/QLSanPham";
        String dbUser = "root";
        String dbPass = ""; // Thay 'your_password' bằng mật khẩu của bạn
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Kết quả xóa sản phẩm</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f8f8f8; color: #333; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }");
            out.println(".container { max-width: 800px; padding: 20px; background-color: #fff; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); border-radius: 8px; text-align: center; }");
            out.println(".message { color: #28a745; font-weight: bold; margin-bottom: 20px; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
            out.println("table, th, td { border: 1px solid #ddd; }");
            out.println("th, td { padding: 12px; text-align: left; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f8f8f8; }");
            out.println("tr:hover { background-color: #ddd; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='container'>");
            
            // Kiểm tra mã sản phẩm có tồn tại
            String checkSQL = "SELECT * FROM SanPham WHERE MaSP = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
            checkStmt.setInt(1, Integer.parseInt(maSP));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Nếu tồn tại, thực hiện xóa
                String deleteSQL = "DELETE FROM SanPham WHERE MaSP = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);
                deleteStmt.setInt(1, Integer.parseInt(maSP));
                deleteStmt.executeUpdate();

                out.println("<h3>Xóa thành công mã sản phẩm: " + maSP + "</h3>");
            } else {
                out.println("<h3>Không có mã sản phẩm " + maSP + " cần xóa</h3>");
            }

            // Hiển thị lại danh sách sản phẩm
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM SanPham";
            rs = stmt.executeQuery(sql);

            out.println("<h2>Danh sách sản phẩm</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Mã SP</th><th>Tên SP</th><th>Loại SP</th><th>Giá</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("MaSP") + "</td><td>" +
                            rs.getString("TenSP") + "</td><td>" +
                            rs.getString("LoaiSP") + "</td><td>" +
                            rs.getDouble("Gia")  + "VNĐ </td></tr>");
            }
            out.println("</table>");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            out.close();
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
