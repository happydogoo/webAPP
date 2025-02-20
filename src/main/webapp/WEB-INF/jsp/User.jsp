<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>修改密码</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
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
        .container{
            width: 300px;
            margin: 0 auto;
            margin-top: 100px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .h2{
            margin-top:20px ;
            text-align: center;

        }
    </style>
</head>
<body style="background-color: #ffffff !important;">

<%--<jsp:include page="/WEB-INF/jsp/common/MainHeader.jsp"/>--%>

<h2 class="h2">修改密码</h2>
<div class="container">
    <p>欢迎, <%= session.getAttribute("username") %></p>

<!-- 密码输入框 -->
<form action="<%= request.getContextPath() %>/user" method="post">

    <label for="username">用户名:</label>
    <input type="text" id="username" name="newUsername" required>
    <br>

    <span id="usernameError" ></span>
    <br>
    <label for="currentPassword">当前密码:</label>
    <input type="password" id="currentPassword" name="currentPassword" required><br><br>

    <label for="newPassword">新密码:</label>
    <input type="password" id="newPassword" name="newPassword" required><br><br>

    <label for="confirmPassword">确认新密码:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>

    <button type="submit">确认修改</button>
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
</div>

</body>
</html>
