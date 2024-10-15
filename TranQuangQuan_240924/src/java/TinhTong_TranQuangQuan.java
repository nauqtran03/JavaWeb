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
@WebServlet(urlPatterns = {"/TinhTong"})
public class TinhTong_TranQuangQuan extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String soA = request.getParameter("soA");
        String soB = request.getParameter("soB");

        // Chuyển đổi chuỗi sang kiểu số
        int a = Integer.parseInt(soA);
        int b = Integer.parseInt(soB);

        // Tính tổng
        int tong = a + b;

        // Trả về kết quả cho client
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Trần Quang Quân</h2>");
        out.println("<h2>Tổng của " + a + " và " + b + " là: " + tong + "</h2>");
        out.println("</body></html>");
    }

   

}
