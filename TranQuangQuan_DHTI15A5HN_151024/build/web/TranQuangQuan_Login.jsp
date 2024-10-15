<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"], input[type="button"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #45a049;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }

        .info-message {
            color: blue;
            margin-top: 10px;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h2>ĐĂNG NHẬP</h2>
        <h2>Trần Quang Quân - DHTI15A5HN - 21103100304</h2>
        <form action="TranQuangQuan_Login.jsp" method="POST">
  
            <input type="text" name="username" 
                   value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>" 
                   placeholder="Tên đăng nhập" required /><br>

            <input type="password" name="password" 
                   value="<%= request.getParameter("password") != null ? request.getParameter("password") : "" %>" 
                   placeholder="Mật khẩu" required /><br>

            <input type="submit" value="Đăng Nhập" />
            <input type="button" value="Thoát" onclick="window.location.href='Thoat.jsp';" />
        </form>

        <%
    
            String correctUsername = "Trần Quang Quân";
            String correctPassword = "123";

 
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            String errorMessage = "";
            String infoMessage = "";

            if (username != null && password != null) {
    
                if (!username.equals(correctUsername) && !password.equals(correctPassword)) {
                    errorMessage = "Tên đăng nhập và mật khẩu đều không chính xác!";
                    infoMessage = "Bạn đã nhập: Tên đăng nhập: '" + username + "' và Mật khẩu: '" + password + "'";
                } 
     
                else if (!username.equals(correctUsername)) {
                    errorMessage = "Tên đăng nhập không chính xác!";
                    infoMessage = "Bạn đã nhập: Tên đăng nhập: '" + username + "'";
                } 
    
                else if (!password.equals(correctPassword)) {
                    errorMessage = "Mật khẩu không chính xác!";
                    infoMessage = "Bạn đã nhập: Mật khẩu: '" + password + "'";
                } 
    
                else {
    
                    session.setAttribute("username", username);

                    response.sendRedirect("DNThanhCong.jsp");
                }

                if (!errorMessage.isEmpty()) {
                    out.println("<div class='error-message'>" + errorMessage + "</div>");
                }

                if (!infoMessage.isEmpty()) {
                    out.println("<div class='info-message'>" + infoMessage + "</div>");
                }
            }
        %>
    </div>

</body>
</html>