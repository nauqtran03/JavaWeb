<%-- 
    Document   : vidu_jsp
    Created on : Oct 1, 2024, 9:17:35 AM
    Author     : quant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 60%;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            color: #4CAF50;
        }
        p {
            font-size: 18px;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
        }
        input[type="text"], input[type="submit"] {
            padding: 10px;
            font-size: 16px;
            margin-bottom: 15px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
    </head>
    
    <body>
        <div class="container">
        <h1>Trần Quang Quân - Ví dụ đầu tiên về JSP với Input</h1>
        
        <!-- Form để người dùng nhập giá trị -->
        <form action="vidu_jsp.jsp" method="post">
            <label for="inputX">Nhập giá trị của x:</label>
            <input type="text" id="inputX" name="inputX" required>

            <label for="inputSt">Nhập xâu st:</label>
            <input type="text" id="inputSt" name="inputSt" required>

            <label for="inputY">Nhập giá trị của y:</label>
            <input type="text" id="inputY" name="inputY" required>

            <input type="submit" value="Hiển thị kết quả">
        </form>

        <%
            // Lấy giá trị từ form
            String inputX = request.getParameter("inputX");
            String inputSt = request.getParameter("inputSt");
            String inputY = request.getParameter("inputY");

            if (inputX != null && inputSt != null && inputY != null) {
                try {
                    // Chuyển đổi giá trị nhập vào
                    int x = Integer.parseInt(inputX);
                    double y = Double.parseDouble(inputY);

                    // Hiển thị các giá trị đã nhập
                    out.println("<p>Giá trị của x là: <strong>" + x + "</strong></p>");
                    out.println("<p>Xâu st là: <strong>" + inputSt + "</strong></p>");
                    out.println("<p>Giá trị của biến y là: <strong>" + y + "</strong></p>");
                } catch (NumberFormatException e) {
                    out.println("<p style='color:red;'>Vui lòng nhập đúng định dạng cho x (số nguyên) và y (số thập phân).</p>");
                }
            }
        %>
    </div>
    </body>
</html>
