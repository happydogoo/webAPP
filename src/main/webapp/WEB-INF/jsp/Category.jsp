<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>产品展示</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/Category.js"></script>

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

        .container1 {
            display: flex;
            flex-direction: column; /* 垂直排列 */
            align-items: center; /* 水平居中对齐 */
            margin-top: 1.5%; /* 添加顶部间距 */
            position: absolute;
            height: 130%;
            top: 15%;
            left: 5%;
            width: 40%;
        }

        .container2 {
            display: flex;
            flex-direction: column; /* 垂直排列 */
            align-items: center; /* 水平居中对齐 */
            position: absolute;
            margin-top: 1.5%; /* 添加顶部间距 */
            height: 130%;
            top: 15%;
            left: 31%;
            width: 40%;
        }

        .leftbox, .rightbox {
            position: relative;
            top: auto;
            background-color: #f4f4f4;
            width: 80%;
            height: 30%; /* 根据内容自适应高度 */
            overflow: hidden;
            border-radius: 70px;
            margin-bottom: 40px; /* 添加底部间距以分开盒子 */
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
            opacity: 1;
            transition: opacity 0.5s ease-in-out;
        }

        .image-section img.active {
            opacity: 1;
        }
        .floating-window {
            position: absolute;
            top: 50%;
            left: 200px;
            width: 150px;
            height: auto;
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            opacity: 0;
            transition: opacity 0.3s ease-in-out;
            z-index: 10;
            }
        .text-section {
            width: 100%;
            height: 40%;
            background-color: #fff;
            border-radius: 0 0 15px 15px;
            padding: 15px;
            box-sizing: border-box;
        }

        /* 修改所有链接的样式 */
        a {
            text-decoration: none; /* 去除下划线 */
            color: inherit; /* 继承父元素颜色 */
        }

        a:hover {
            color: #007bff; /* 鼠标悬停时变色为蓝色 */
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

<div class="container1">
    <c:forEach var="product" items="${productList}" varStatus="status">
        <c:choose>
            <c:when test="${status.index % 2 == 0}">
                <!-- 左侧显示的商品信息框 -->
                <div class="leftbox">
                    <div class="image-section" onmouseenter="mouseOver(this)" onmouseout="mouseOut(this)">
                        <img src="${product.imageUrl}" >
                        <div id="${product.name}-float-window" class="floating-window" data-product-id="${product.productId}">
                            <h4>${product.name}</h4>
                            <p>${product.description}</p>
                        </div>
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
                      <div class="image-section" onmouseenter="mouseOver(this)" onmouseout="mouseOut(this)">
                    <img src="${product.imageUrl}" >
                    <div id="${product.name}-float-window" class="floating-window" data-product-id="${product.productId}">
                        <h4>${product.name}</h4>
                        <p>${product.description}</p>
                    </div>
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
</body>
</html>
