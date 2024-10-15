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

/**
 *
 * @author quant
 */
@WebServlet(urlPatterns = {"/TinhToanJSP_TranQuangQuan"})
public class TinhToanJSP_TranQuangQuan extends HttpServlet {

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
            out.println("<title>Servlet TinhToanJSP_TranQuangQuan</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TinhToanJSP_TranQuangQuan at " + request.getContextPath() + "</h1>");
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
        String strA = request.getParameter("A");
        String strB = request.getParameter("B");
        String operation = request.getParameter("operation");
        String result = "";
        String message = "";
        if ("Reset".equals(operation)) {
        message = "Reset thành công";
        request.setAttribute("result", ""); 
        request.setAttribute("message", message);
        request.getRequestDispatcher("TinhToan_TranQuangQuan.jsp").forward(request, response);
        return;
    }
        try {
            double a = Double.parseDouble(strA);
            double b = Double.parseDouble(strB);

            switch (operation) {
                case "+":
                    result = ""+(a + b);
                    break;
                case "-":
                    result = ""+ (a - b);
                    break;
                case "*":
                    result = "" + (a * b);
                    break;
                case "/":
                    if (b != 0) {
                        result = "" + (a / b);
                    } else {
                        result = "Lỗi: Không thể chia cho 0.";
                    }
                    break;
                
                default:
                    result = "Lỗi: Phép toán không hợp lệ.";
            }
        } catch (NumberFormatException e) {
            result = "Lỗi: Vui lòng nhập số hợp lệ.";
        }

        // Chuyển kết quả về trang JSP
        request.setAttribute("result", result);
        request.getRequestDispatcher("TinhToan_TranQuangQuan.jsp").forward(request, response);
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
