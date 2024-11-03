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




        .top-menu {
            display: flex;
            position: relative;
            top: 0%;
            left: 60%;
            width: 35%;
            height: 10%;
            align-items: center;
            opacity: 0.9;
            padding: 1%;
            box-sizing: border-box;
        }

        .menu-item {

            width: 15%;
            font-size: 0.9vw;
            color: #fff;
            margin: 2%;
        }

        .search {
            opacity: 1;
            font-size: 0.9vw;
            width: 10%;
            color: #fff;
            /* padding: 10px; */
            /* 添加一些内边距 */
            box-sizing: border-box;
            /* 内边距包含在宽度和高度内 */
        }

        .Login {

            /* top: -8px; */
            /* 向上移动5px */
            font-size: 0.9vw;
            background: red;
            width: 100%;
            height: 75%;
        }
        .menu-item a {
            color: #fff; /* 设置字体颜色 */
            text-decoration: none; /* 去除下划线 */
        }

        .menu-item a:hover {
            color: #aaa; /* 鼠标悬停时变色 */
        }
        .search a{
            color: #fff; /* 设置字体颜色 */
            text-decoration: none; /* 去除下划线 */
        }
        .search a:hover{
            color: #aaa;
        }
    </style>
</head>

<body>

<div class="top-menu">
    <div class="menu-item"><a href="">商品</a></div>
    <div class="menu-item"><a href="/petfood">宠物食品</a></div>
    <div class="menu-item"><a href="/contact">联系我们</a></div>
    <div class="menu-item"><a href="/cart">购物车</a></div>
    <div class="search"><a href="/search">搜索</a></div>
    <div class="menu-item">
        <button class="Login" onclick="window.location.href='<%= request.getContextPath() %>/login';">登录</button>
    </div>
</div>
</body>
</html>
