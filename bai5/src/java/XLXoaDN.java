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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author quant
 */
@WebServlet(urlPatterns = {"/XLXoaDN"})
public class XLXoaDN extends HttpServlet {

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
            out.println("<title>Servlet XLXoaDN</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet XLXoaDN at " + request.getContextPath() + "</h1>");
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
        String madn = request.getParameter("madn");
        Connection con = null;
        try{
            con = KetNoi.KNCSDL();
            String check = "SELECT COUNT(*) FROM DoanhNghiep WHERE MaDN = ?";
            PreparedStatement psCheck = con.prepareStatement(check);
            psCheck.setString(1, madn);
            ResultSet rsCheck = psCheck.executeQuery();
            rsCheck.next();
            if(rsCheck.getInt(1)>0){
                String deleteQuery = "DELETE FROM DoanhNghiep WHERE MaDN = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteQuery);
                psDelete.setString(1, madn);
                psDelete.executeUpdate();
                request.setAttribute("message", "Da xoa doang nghiep co ma: "+madn);
            }else{
                request.setAttribute("message", "Khong ton tai doang nghiep co ma: "+madn);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(XLXoaDN.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(con != null) con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("XoaDN.jsp").forward(request, response);
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
