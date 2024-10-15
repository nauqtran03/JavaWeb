<%-- 
    Document   : TinhToan_TranQuangQuan
    Created on : Oct 1, 2024, 10:39:14 AM
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
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 400px;
            padding: 20px;
            background-color: #fff;
            border: 2px solid #4CAF50;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #4CAF50;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="submit"] {
            width: calc(100% - 20px);
            padding: 10px;
            font-size: 16px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
        }
        .button-container input {
            flex: 1;
            margin: 0 5px; /* Thêm khoảng cách giữa các nút */
        }
        .reset-button {
            background-color: #f44336; /* Màu đỏ cho nút Reset */
        }
        .reset-button:hover {
            background-color: #e53935;
        }
        .message {
            color: red; /* Màu xanh cho thông báo */
            text-align: center;
            margin: 10px 0;
        }
    </style>
    
    </head>
    <body>
       <div class="container">
        <h1>FORM TÍNH TOÁN</h1>

        <form action="TinhToanJSP_TranQuangQuan" method="post">
            <label for="inputA">Nhập a:</label>
            <input type="text" id="A" name="A" required>

            <label for="inputB">Nhập b:</label>
            <input type="text" id="tB" name="B" required>

            <label for="result">Kết quả:</label>
            <input type="text" id="result" class="result" value="<%= request.getAttribute("result") != null ? request.getAttribute("result") : "" %>" readonly>

            <div class="message">
                <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
            </div>
            <div class="button-container">
                <input type="submit" name="operation" value="+">
                <input type="submit" name="operation" value="-">
                <input type="submit" name="operation" value="*">
                <input type="submit" name="operation" value="/">
                <input type="submit" class="reset-button" name="operation" value="Reset">
            </div>
        </form>        
    </div>
            
    </body>
</html>
