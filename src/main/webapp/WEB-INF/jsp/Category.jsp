<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>产品展示</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .divide {
            display: flex;
            position: absolute;
            top: 8%;
            left: 0;
            width: 100%;
            height: 7%;
            background-color: #191414;
            opacity: 0.5;
            align-items: center;
        }

        .divide-item {
            width: 6%;
            height: 70%;
            background-color: #111010;
            color: #fff;
            margin: 2%;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 15px;
            font-size: 1vw;
        }

        .divide-item a {
            color: white; /* 设置字体颜色为白色 */
            text-decoration: none; /* 去除下划线 */
        }

        .divide-item a:hover {
            color: #aaa; /* 鼠标悬停时变色 */
        }

        .container {
            display: flex;
            align-items: center; /* 水平居中对齐 */
            margin-top: 2%; /* 添加顶部间距 */

        }
        .container1 {
            display: flex;
            flex-direction: column; /* 垂直排列 */
            align-items: center; /* 水平居中对齐 */
            margin-top: 3%; /* 添加顶部间距 */
        }
        .container2 {
            display: flex;

            flex-direction: column; /* 垂直排列 */
            align-items: center; /* 水平居中对齐 */
            margin-top: 3%; /* 添加顶部间距 */
        }

        .leftbox, .rightbox {
            position: relative;
            background-color: #f4f4f4;
            width: 60%;
            height: 28%; /* 根据内容自适应高度 */
            overflow: hidden;
            border-radius: 15px;
            margin-bottom: 20px; /* 添加底部间距以分开盒子 */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
        }

        .image-section {
            position: relative;
            height: 60%;
            background-size: cover;
            background-position: center;
            border-radius: 15px 15px 0 0;
        }

        .image-section img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 15px 15px 0 0;
            opacity: 0;
            transition: opacity 0.5s ease-in-out;
        }

        .image-section img.active {
            opacity: 1;
        }

        .text-section {
            width: 100%;
            height: 40%;
            background-color: #fff;
            border-radius: 0 0 15px 15px;
            padding: 15px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
<div class="divide">
    <div class="category-title">分类</div>
    <div class="divide-item"><a href="/webAPP/category?type=DOGS">狗狗</a></div>
    <div class="divide-item"><a href="/webAPP/category?type=CATS">小猫</a></div>
    <div class="divide-item"><a href="/webAPP/category?type=BIRDS">鸟</a></div>
    <div class="divide-item"><a href="/webAPP/category?type=FISH">鱼</a></div>
</div>

<div class="container">
    <div class="container1">
    <c:forEach var="product" items="${productList}" varStatus="status">
        <c:choose>
            <c:when test="${status.index % 2 == 0}">
                <!-- 左侧显示的商品信息框 -->
                <div class="leftbox">
                    <div class="image-section">
                        <img src="${product.imageUrl}" alt="${product.description}" data-text="${product.description}" class="active">
                    </div>
                    <div class="text-section">
                        <a href="/webAPP/product?product=${product.productId}"><h3>${product.name}</h3></a>
                        <p>${product.description}</p>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </c:forEach>
    </div>
    <div class="container2">
        <c:forEach var="product" items="${productList}" varStatus="status">
            <c:choose>
                <c:when test="${status.index % 2 == 1}">

                <div class="rightbox">
                    <div class="image-section">
                        <img src="${product.imageUrl}" alt="${product.description}" data-text="${product.description}" class="active">
                    </div>
                    <div class="text-section">
                        <a href="/webAPP/product?product=${product.productId}"><h3>${product.name}</h3></a>
                        <p>${product.description}</p>
                    </div>
                </div>
                </c:when>
        </c:choose>
    </c:forEach>
</div>
</div>

<script>
    let leftImages = document.querySelectorAll('.leftbox .image-section img');
    let rightImages = document.querySelectorAll('.rightbox .image-section img');
    let index = 0;

    function cycleImages() {
        // 移除当前active类
        leftImages[index].classList.remove("active");
        rightImages[index].classList.remove("active");
        // 更新index
        index = (index + 1) % leftImages.length;
        // 给下一张图片添加active类
        leftImages[index].classList.add("active");
        rightImages[index].classList.add("active");
    }

    // 初始化轮播
    cycleImages();

    // 每3秒切换一次图片
    setInterval(cycleImages, 3000);
</script>
</body>
</html>
