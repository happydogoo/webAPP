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
                height: 10%;

                opacity: 0.9;
                padding: 1%;
                box-sizing: border-box;
            }

            menu-item {

                width: 15%;
                font-size: 0.9vw;
                color: #fff;
                margin: 2%;
            }
            .menu-item a {
                color: black; /* 自定义文字颜色 */
                text-decoration: none; /* 去掉下划线 */
                font-size: 16px; /* 设置字体大小 */
            }

            /* 悬停效果 */
            .menu-item a:hover {
                color: #ff9900; /* 悬停时的颜色 */
            }

            .search {
                opacity: 1;
                font-size: 0.9vw;

                color: #fff;
                /* padding: 10px; */
                /* 添加一些内边距 */
                box-sizing: border-box;
                /* 内边距包含在宽度和高度内 */
            }

            .Login{
            /* top: -8px; */
            /* 向上移动5px */
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