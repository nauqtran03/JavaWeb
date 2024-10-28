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
@WebServlet("/TranQuangQuan_HienThiDuLieu_2210")
public class TranQuangQuan_HienThiDuLieu_2210 extends HttpServlet {

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
            out.println("<title>Servlet TranQuangQuan_HienThiDuLieu_2210</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TranQuangQuan_HienThiDuLieu_2210 at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Connection ketnoi = null;
        Statement statement = null;
        ResultSet resultSet = null;
        out.print("<h1>Trần Quang Quân - DHTI15A5HN - 21103100304</h1>");
        try {
            //
            ketnoi = KetNoi.KNCSDL();
            statement = ketnoi.createStatement();
            
            String sql = "SELECT * FROM sinhvien";
            
            resultSet = statement.executeQuery(sql);
            
            int i=0;
            while (resultSet.next()) {
                String masv1 = resultSet.getString("MaSV");
                String tensv1 = resultSet.getString("TenSV");
                
                i+=1;
                out.println("<form method='POST'>");
                 out.println("<label for='masv'>Mã sinh viên thứ " + i + ":</label>");
                out.println("<input type='text' id='masv' name='masv' value='" + masv1 + "' readonly><br>");
                
                out.println("<label for='tensv'>Tên sinh viên thứ "+ i +":</label>");
                out.println("<input type='text' id='tensv' name='tensv' value='" + tensv1 + "'>");

                out.println("</form>");
            }
            
            out.println("</table>");
            out.println("</body></html>");
            
        } catch (SQLException e) {
            out.println("Lỗi: " + e.getMessage());
        } finally {
            // Đóng tài nguyên
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (ketnoi != null) ketnoi.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        out.close();
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
