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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author quant
 */
@WebServlet(urlPatterns = {"/XLTimKiemHD"})
public class XLTimKiemHD extends HttpServlet {

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
            out.println("<title>Servlet XLTimKiemHD</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet XLTimKiemHD at " + request.getContextPath() + "</h1>");
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
        String mahd = request.getParameter("mahd");
        List<Map<String,Object>>  danhSachHoaDon = new ArrayList<>();
        List<Map<String,Object>>  ketQuaTimKiem = new ArrayList<>();
        
        try(Connection con = KetNoi.KNCSDL()){
            String queryDanhSach = "SELECT * FROM hoadon";
            PreparedStatement psDanhSach = con.prepareStatement(queryDanhSach);
            ResultSet rsDanhSach = psDanhSach.executeQuery();
            while(rsDanhSach.next()){
                Map<String,Object> cn = new HashMap<>();
                cn.put("MaHD", rsDanhSach.getInt("MaHD"));
                cn.put("MaKH", rsDanhSach.getString("MaKH"));
                cn.put("NgayHD", rsDanhSach.getDate("NgayHD"));
                cn.put("SoKW", rsDanhSach.getInt("SoKW"));
                danhSachHoaDon.add(cn);
            }
            if(mahd != null && !mahd.trim().isEmpty()){
                String queryTimKiem = "SELECT * FROM hoadon WHERE MaHD LIKE ?";
                PreparedStatement psTimKiem = con.prepareStatement(queryTimKiem);
                psTimKiem.setString(1,"%"+ mahd + "%");
                ResultSet rsTimKiem = psTimKiem.executeQuery();
                while(rsTimKiem.next()){
                    Map<String,Object> cn = new HashMap<>();
                    cn.put("MaHD", rsTimKiem.getInt("MaHD"));
                    cn.put("MaKH", rsTimKiem.getString("MaKH"));
                    cn.put("NgayHD", rsTimKiem.getDate("NgayHD"));
                    cn.put("SoKW", rsTimKiem.getInt("SoKW"));
                    ketQuaTimKiem.add(cn);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(XLTimKiemHD.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("danhSachHoaDon", danhSachHoaDon);
        request.setAttribute("ketQuaTimKiem", ketQuaTimKiem);
        request.setAttribute("MaHD", mahd);
        request.getRequestDispatcher("TimKiemHD.jsp").forward(request, response);
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
