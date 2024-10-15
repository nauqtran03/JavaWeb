<%-- 
    Document   : TranQuangQuan_DTHCN
    Created on : Oct 1, 2024, 10:08:21 AM
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
        <h1>Trần Quang Quân - DHTI15A5HN</h1>
        <h1>Tính Diện Tích Hình Chữ Nhật</h1>
        
        <!-- Form để người dùng nhập chiều dài và chiều rộng -->
        <form action="TranQuangQuan_DTHCN.jsp" method="post">
            <label for="inputLength">Nhập chiều dài:</label>
            <input type="text" id="inputLength" name="inputLength" required>

            <label for="inputWidth">Nhập chiều rộng:</label>
            <input type="text" id="inputWidth" name="inputWidth" required>

            <!-- Nút tính diện tích -->
            <input type="submit" name="operation" value="Tính Diện Tích">
        </form>

        
        <%
            String inputLength = request.getParameter("inputLength");
            String inputWidth = request.getParameter("inputWidth");
            String result = "";

            if (inputLength != null && inputWidth != null) {
                try {
                    // Chuyển đổi giá trị nhập vào
                    double length = Double.parseDouble(inputLength);
                    double width = Double.parseDouble(inputWidth);

                    // Tính diện tích
                    double area = length * width;
                    result = "Diện tích hình chữ nhật là: " + area;
                } catch (NumberFormatException e) {
                    result = "Lỗi: Vui lòng nhập đúng định dạng cho chiều dài và chiều rộng.";
                }
            }
        %>

        <p><%= result %></p>
    </div>
    </body>
</html>
