<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Menu</title>
    <style>

        html,
        body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        .w {
            width: 100%;
            height: 100%;
            opacity: 1;
            background: rgba(51, 51, 51, 1);
        }

        .top-menu {
            display: flex;
            position: absolute;
            top: 2%;
            right: 10%;
            width: 35%;
            height: 4%;

            opacity: 0.9;
            padding: 10px;
            box-sizing: border-box;
        }

        .menu-item {
            color: #fff;
            margin: 2%;
        }

        .search {
            opacity: 1;

            color: #fff;
            padding: 10px;
            /* 添加一些内边距 */
            box-sizing: border-box;
            /* 内边距包含在宽度和高度内 */
        }

        .Login {
            top: -8px;
            /* 向上移动5px */
            position: relative;
            /* 添加相对定位 */
            background: red;
            width: 112px;
            height: 40px;
        }
    </style>
</head>

<body>
<div class="w">
    <div class="top-menu">
        <div class="menu-item">折扣</div>
        <div class="menu-item">商品</div>
        <div class="menu-item">宠物食品</div>
        <div class="menu-item">联系我们</div>
        <div class="menu-item">购物车</div>
        <div class="search">搜索</div>
        <div class="menu-item">

            <button class="Login" onclick="location.href='login.jsp';">登录</button>
        </div>
    </div>
</div>
</body>

</html>