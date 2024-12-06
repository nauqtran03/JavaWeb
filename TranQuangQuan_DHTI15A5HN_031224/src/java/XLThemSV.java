import Test.KetNoi;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "XLThemSV", urlPatterns = {"/XLThemSV"})
public class XLThemSV extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mssv = request.getParameter("MSSV");
        String hoTen = request.getParameter("Hoten");
        String lop = request.getParameter("Lop");
        String diemTBStr = request.getParameter("DiemTB");

        Connection conn = null;
        try {
            conn = KetNoi.KNCSDL();

            // Kiểm tra MSSV đã tồn tại
            String checkSQL = "SELECT COUNT(*) FROM Student WHERE MSSV = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
            checkStmt.setString(1, mssv);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                // Nếu MSSV đã tồn tại, set thông báo lỗi
                request.setAttribute("message", "Mã số sinh viên "+mssv+" đã tồn tại!");
            } else {
                // Nếu MSSV chưa tồn tại, thực hiện thêm sinh viên
                String insertSQL = "INSERT INTO Student (MSSV, HoTen, Lop, DiemTB) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
                insertStmt.setString(1, mssv);
                insertStmt.setString(2, hoTen);
                insertStmt.setString(3, lop);
                insertStmt.setFloat(4, Float.parseFloat(diemTBStr));

                insertStmt.executeUpdate();
                // Thông báo thành công
                request.setAttribute("message", "Sinh viên " + mssv + " đã được thêm thành công!");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Lỗi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Lưu lại dữ liệu người dùng nhập vào form
        request.setAttribute("MSSV", mssv);
        request.setAttribute("Hoten", hoTen);
        request.setAttribute("Lop", lop);
        request.setAttribute("DiemTB", diemTBStr);

        // Chuyển hướng về lại trang ThemSV.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("ThemSV.jsp");
        dispatcher.forward(request, response);
    }
}
