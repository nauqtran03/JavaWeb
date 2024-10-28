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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author quant
 */
@WebServlet(urlPatterns = {"/TranQuangQuan_HTSach"})
public class TranQuangQuan_HTSach extends HttpServlet {

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
            out.println("<title>Servlet TranQuangQuan_HTSach</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TranQuangQuan_HTSach at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
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
            
            String sql = "SELECT * FROM sach";
            
            resultSet = statement.executeQuery(sql);
            
           
            while (resultSet.next()) {
                String ma = resultSet.getString("Ma");
                String ten = resultSet.getString("Ten");
                String tacgia = resultSet.getString("Tacgia");
                String nhaxb = resultSet.getString("NhaXB");
                
               
                out.println("<form method='POST'>");
                 out.println("<label for='masv'>Mã sách:</label>");
                out.println("<input type='text' id='ma' name='ma' value='" + ma + "' readonly><br>");
                
                out.println("<label for='tensv'>Tên sách:</label>");
                out.println("<input type='text' id='ten' name='ten' value='" + ten + "'><br>");
                out.println("<label for='tensv'>Tên tác giả:</label>");
                out.println("<input type='text' id='tacgia' name='tacgia' value='" + tacgia + "'><br>");
                out.println("<label for='tensv'>Nhà xuất bản:</label>");
                out.println("<input type='text' id='nhaxb' name='nhaxb' value='" + nhaxb + "'><br>");

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
