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
            position: relative;
            top: 2%;
            left: 65%;
            width: 35%;
            min-height: 50px; /* 使用 min-height 自适应内容高度 */
            opacity: 0.9;
            padding: 1%;
            box-sizing: border-box;
            align-items: center; /* 垂直居中对齐 */
        }

        .avatar {

            position: absolute;
            left: 1px;
            width: 50px;
            height: 50px;
            border-radius: 1000px;
            background: url(https://img.js.design/assets/img/67131bf63c6b1508d8af46e4.jpg#9f819da36e47ad2c6b0787e97cf236e7) no-repeat center center;
            background-size: cover;
            border: 1px solid rgba(0, 0, 0, 1);
            box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.25);
            margin-right: 18px; /* 控制头像与菜单项的间距 */
        }

        .menu-item {
            width: 15%;
            font-size: 0.9vw;
            color: #fff;
            margin: 2%;
        }

        .menu-item a {
            color: black;
            text-decoration: none;
            font-size: 16px;
        }

        .menu-item a:hover {
            color: #ff9900;
        }

        .search {
            opacity: 1;
            font-size: 0.9vw;
            color: #fff;
            box-sizing: border-box;
        }

        .Login {
            font-size: 0.9vw;
            background: red;
            width: 100%;
            height: 75%;
        }
    </style>
</head>

<body>
<div class="w">
    <div class="top-menu">
        <div class="avatar"></div>
        <div class="menu-item"><a href="">商品</a></div>
        <div class="menu-item"><a href="/petfood">宠物食品</a></div>
        <div class="menu-item"><a href="/contact">联系我们</a></div>
        <div class="menu-item"><a href="/cart">购物车</a></div>
        <div class="search"><a href="/search">搜索</a></div>
        <div class="menu-item">
            <button class="Login" onclick="location.href='login';">登录</button>
        </div>
    </div>
</div>
</body>

</html>
