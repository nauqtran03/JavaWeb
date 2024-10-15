/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package TranQuangQuan_TrangHienThi;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author quant
 */
@WebServlet(name = "TranQuangQuan_TrangHienThi", urlPatterns = {"/TranQuangQuan_TrangHienThi"})
public class TranQuangQuan_TrangHienThi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        response.setContentType("text/html;charset=UTF-8");
 
        HttpSession session = request.getSession();

        String name1 = (String) session.getAttribute("name");
        String class1 = (String) session.getAttribute("class");
  
        PrintWriter out = response.getWriter();
        
      
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Trang Hiển Thị</title>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<h1>Trần Quang Quân_DHTI15A5HN_21103100304</h1>");
        out.println("<h3>Thông tin của bạn:</h3>");
        
        
        if (name1 != null && class1 != null) {
            out.println("Tên của bạn: " + name1 + "<br>");
            out.println("Bạn học lớp: " + class1 + "<br>");
        } else {
            out.println("Không có thông tin để hiển thị. Vui lòng quay lại trang nhập thông tin.");
        }
        
        out.println("</body>");
        out.println("</html>");
    }

}
