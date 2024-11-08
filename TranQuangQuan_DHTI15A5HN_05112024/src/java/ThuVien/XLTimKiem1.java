/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ThuVien;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quant
 */
@WebServlet(name = "XLTimKiem1", urlPatterns = {"/XLTimKiem1"})
public class XLTimKiem1 extends HttpServlet {

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
            out.println("<title>Servlet XLTimKiem1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet XLTimKiem1 at " + request.getContextPath() + "</h1>");
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
        String maDienThoai = request.getParameter("maDienThoai");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
     
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qldt", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM dienthoai WHERE Ma = ?")) {

            stmt.setString(1, maDienThoai);
            ResultSet rs = stmt.executeQuery();

            ArrayList<DienThoai> arr = new ArrayList<>();
            while (rs.next()) {
                DienThoai dt = new DienThoai();
                dt.setMa(rs.getString("Ma"));
                dt.setTen(rs.getString("Ten"));
                dt.setSoluong(rs.getInt("Soluong"));
                dt.setDongia(rs.getDouble("Dongia"));
                arr.add(dt);
            }

            request.setAttribute("arrsv", arr);
            request.getRequestDispatcher("TimKiem1.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(XLTimKiem1.class.getName()).log(Level.SEVERE, null, ex);
            
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
