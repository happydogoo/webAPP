<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册界面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        .register-container {
            width: 300px;
            margin: 0 auto;
            margin-top: 100px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .register-container input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }.error-message {
             background-color: #f8d7da;
             color: #721c24;
             padding: 10px;
             border: 1px solid #f5c6cb;
             border-radius: 5px;
             margin: 10px 0;
             display: none; /* 初始时隐藏 */
             text-align: center;
         }
        .register-container button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }
        .register-container button:hover {
            background-color: #45a049;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

</head>
<body>
<div class="register-container">
    <h2>用户注册</h2>

    <form action="${pageContext.request.contextPath}/register" method="post">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username" required>
        <span id="usernameError" ></span>
        <br>


        <label for="password">密码:</label>
        <input type="password" id="password" name="password" required>

        <label for="confirmPassword">确认密码:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>

        <label for="email">邮箱:</label>
        <input type="email" id="email" name="email" required>

        <label for="address">地址:</label>
        <input type="text" id="address" name="address" required>

        <label for="phone">电话:</label>
        <input type="text" id="phone" name="phone" required>

        <div class="captcha-container">
            <label for="captcha">验证码:</label>
            <img src="${pageContext.request.contextPath}/captcha" alt="Captcha" class="captcha-image">
        </div>

        <!-- 输入验证码 -->
        <input type="text" id="captcha" name="captcha" required>


        <button type="submit">注册</button>
    </form>
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

        $(document).ready(function() {
            $("#username").on("blur", function () { // 监听用户名输入框失焦事件
                var username = $(this).val();
                console.log("blur"); // 网页控制台输出确认是否绑定成功
                if (username.trim() !== "") {
                    console.log("blur")
                    $.ajax({
                        url: "checkUsername", // 后端接口路径
                        method: "GET",
                        data: { username: username },
                        success: function (response) {
                            if (response.exists) {
                                $("#usernameError")
                                    .text("用户名已存在，请选择其他用户名！")
                                    .css("color", "red");
                            } else {
                                $("#usernameError")
                                    .text("用户名合法")
                                    .css("color", "green");
                            }
                        },
                        error: function () {
                            $("#usernameError")
                                .text("服务器错误，请稍后重试！")
                                .css("color", "red");
                        }
                    });
                }
            });
        });

    </script>
    <a href="login">返回登录</a>


</div>
</body>
</html>
