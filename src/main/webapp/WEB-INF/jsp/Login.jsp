<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    <style>
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            padding: 10px;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            margin: 10px 0;
            display: none; /* 初始时隐藏 */
            text-align: center;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        .login-container {
            width: 300px;
            margin: 0 auto;
            margin-top: 100px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-container input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }
        .login-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>用户登录</h2>

    <form action="login" method="post">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">密码:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit" name="action" value="login">登录</button>
        <div class="captcha-container">
            <label for="captcha">验证码:</label>
            <img src="${pageContext.request.contextPath}/captcha" alt="Captcha" class="captcha-image">
        </div>
        <input type="text" id="captcha" name="captcha" required>
    </form>

    <a href="register">返回注册</a>

    <!-- 如果有错误信息，则显示 -->
    <c:if test="${not empty errorMessage}">
        <div id="errorMessage" class="error-message">
            <c:out value="${errorMessage}" />
        </div>
    </c:if>

    <script>
        // 如果有错误信息，2秒后自动隐藏
        window.onload = function() {
            var errorMessage = document.getElementById("errorMessage");
            if (errorMessage) {
                // 显示错误信息
                errorMessage.style.display = "block";

                // 2秒后自动隐藏
                setTimeout(function() {
                    errorMessage.style.display = "none";
                }, 2000); // 2000ms = 2秒
            }
        };
    </script>
</div>
</body>
</html>
