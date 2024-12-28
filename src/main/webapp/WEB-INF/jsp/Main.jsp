<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <style>
        .divide {
            display: flex;
            position: absolute;
            /* 使用绝对定位 */
            top: 8%;
            /* 从顶部10%的位置开始 */
            left: 0%;
            /* 从左侧开始 */

            width: 100%;
            /* 宽度占满整个容器 */
            height: 7%;


            background-color: #191414;
            /* 背景颜色为白色 */
            opacity: 0.5;
            /* 透明度 */

            align-items: center;
            /* 垂直居中子元素 */
        }

        .divide-item {
            position: relative;
            width: 6%;
            height: 70%;
            background-color: #111010;
            color: #fff;
            margin: 2%;
            display: flex;
            /* 设置为flex容器 */
            align-items: center;
            /* 垂直居中文本 */
            justify-content: center;
            /* 水平居中文本 */
            border-radius: 15px;
            font-size: 1vw;

        }




        /* .category-title {
            position: absolute;
            left: 60px;

            color: #fff;
            margin: 2%;

            align-items: center;

            justify-content: center;

        } */


        .leftbox {
            position: absolute;
            top: 18%;
            left: 5%;
            background-color: #fff;
            width: 40%;
            height: 40%;
            overflow: hidden;
            /* 确保内容不会超出边框 */
            border-radius: 15px;
            /* 设置圆角边框 */
            background-color: #f4f4f4;
            /* 背景颜色，可以根据需要调整 */
        }

        .rightbox {
            position: absolute;
            top: 18%;
            left: 55%;
            background-color: #fff;
            width: 40%;
            height: 40%;
            overflow: hidden;
            /* 确保内容不会超出边框 */
            border-radius: 15px;
            /* 设置圆角边框 */
            background-color: #f4f4f4;
            /* 背景颜色，可以根据需要调整 */
        }



        .image-section {
            position: relative;
            height: 60%;
            /* 设置图片部分的高度为盒子的60% */
            /* background-image: url('image.png'); */
            /* 设置图片 */
            background-size: cover;

            /* 填充图片，不留空白 */
            background-position: center;
            /* 图片居中显示 */
            border-radius: 15px 15px 0 0;
            /* 设置圆角边框 */

        }

        .image-section img {
            position: absolute;
            width: 100%;
            height: 100%;
            object-fit: cover;
            /* 确保图片填充整个区域而不变形 */
            border-radius: 15px 15px 0 0;
            /* 设置圆角边框 */
            top: 0;
            left: 0;
            opacity: 0;

            transition: opacity 0.5s ease-in-out;
        }

        .image-section img.active {
            opacity: 1;
        }

        .text-section {
            position: relative;
            bottom: 0;
            width: 100%;
            height: 40%;
            /* 设置文字部分的高度为盒子的40% */
            background-color: #fff;
            /* 背景颜色 */
            border-radius: 0 0 15px 15px;
            /* 设置圆角边框 */
            padding: 15px;
            /* 文字内边距 */
            box-sizing: border-box;
            /* 边框和内边距包含在宽度内 */
        }
    </style>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/Category.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/common/MainHeader.jsp"></jsp:include>


</body>

</html>