<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>\
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
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
            background: rgba(51, 51, 51, 1);
        }




        .top-menu {
            display: flex;
            position: relative;
            top: 0%;
            /*left: 60%;*/
            width:100%;
            height: 10%;
            align-items: center;
            justify-content: center;
            opacity: 0.9;
            padding: 1%;
            box-sizing: border-box;
        }

        .menu-item {
            display: flex;
            width: 40%;
            justify-content: center; /* 水平居中 */
            align-items: center; /* 垂直居中 */
            font-size: 0.9vw;
            padding: 5px 10px;
            color: #fff;
            margin: 20px;
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
        .modify{
            font-size: 0.9vw;
            background: green;
            width: 100%;
            height: 75%;
        }

        .Login {

            /* top: -8px; */
            /* 向上移动5px */
            font-size: 0.9vw;
            background: red;
            width: 60%;
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
        .avatar {
            left :1%;
            height: 63px;
            margin-right: 36%;
        }

        #productAutoComplete{
            background: #ccc;
            position:absolute;
            top: 70%;
            left: 33%;
            width: 40%;
            display: none;
            z-index: 999;

        }
        #productAutoList{
            margin: 0;
            padding: 0;
            border: 1px solid #aaa;

        }
        .productAutoItem{
            list-style: none;
            height: 20px;
            line-height: 20px;
            font-size: 10px;
            text-align: left;
            padding-left: 5px;
            margin-top: 3px;
            border-bottom: 1px solid#666;
            color: #005e21;
        }

        /* 定位整个搜索框 */
        .search-form {
            position: relative; /* 使子元素可以相对这个父容器定位 */
            display: flex;
            align-items: center;
            width: 100%;
        }

        .search-input {
            font-size: 0.8vw;
            width: 100%;
            padding: 3px;
            box-sizing: border-box;
        }

        .productAutoItem:hover {
            background-color: #ddd;
        }

    </style>
</head>

<body>


<div class="top-menu">
    <img src="<%= request.getContextPath() %>/images/logocsu.png" alt="Avatar" class="avatar">

    <div class="menu-item"><a href="/webAPP">商品</a></div>
<%--    <div class="menu-item"><a href="/webAPP/petfood">宠物食品</a></div>--%>
<%--    <div class="menu-item"><a href="/webAPP/contact">联系我们</a></div>--%>
    <div class="menu-item"><a href="/webAPP/cart">购物车</a></div>
    <div class="menu-item"><a href="/webAPP/order">订单</a></div>




    <form action="search" method="get" class="search-form">
        <input type="text" name="query" id="keyword" placeholder="输入搜索内容" required class="search-input">
        <input type="submit" value="搜索" class="search-btn">

    </form>
    <div id="productAutoComplete" >
        <ul id="productAutoList" >

        </ul>
    </div>





    <div class="menu-item">
        <button class="Login" onclick="window.location.href='<%= request.getContextPath() %>/login';">登录</button>
    </div>


    <div class="menu-item">
        <button class="modify" onclick="window.location.href='<%= request.getContextPath() %>/user';">修改信息</button>
<%--        <a href="/webAPP/product?product=${product.productId}"><h3>${product.name}</h3></a>--%>

    </div>
</div>
</body>


<script>
<%--<script>$(function () {--%>
<%--    $('#keyword').on('keyup', function () {--%>
<%--        var keyword = $(this).val();--%>
<%--        if (keyword !== '' && keyword !== null) {--%>
<%--            $.ajax({--%>
<%--                type: 'GET',--%>
<%--                url: 'productAuto?keyword=' + keyword, // 确保URL是完整的--%>
<%--                success: function (data) {--%>
<%--                    console.log(data);--%>
<%--                    var productlistHTML = '';--%>
<%--                    for (var i = 0; i < data.length; i++) {--%>
<%--                        productlistHTML += '<li class="productAutoItem" onclick="search(this)" data-product-id="' + data[i].productId + '">';--%>
<%--                        productlistHTML += data[i].description + ':' + data[i].name;--%>
<%--                        productlistHTML += '</li>';--%>
<%--                    }--%>
<%--                    $('#productAutoList').html(productlistHTML);--%>
<%--                    $('#productAutoComplete').show();--%>
<%--                },--%>
<%--                error: function (xhr, status, error) {--%>
<%--                    console.log('Request failed with status: ' + status);--%>
<%--                    console.log('Error: ' + error);--%>
<%--                }--%>
<%--            });--%>
<%--        } else {--%>
<%--            $('#productAutoComplete').hide();--%>
<%--        }--%>
<%--    });--%>

<%--    // 搜索框失去焦点时隐藏下拉框--%>
<%--    $('#keyword').on('blur', function (e) {--%>
<%--        // setTimeout(() => {--%>
<%--        //     const relatedTarget = e.relatedTarget || document.activeElement; // 获取焦点转移的目标--%>
<%--        //     if ($(relatedTarget).closest('#productAutoComplete').length > 0) {--%>
<%--        //         // 如果新焦点在下拉框内，不隐藏下拉框--%>
<%--        //         return;--%>
<%--        //     }--%>
<%--        //     $('#productAutoComplete').hide();--%>
<%--        // }, 200); // 延时隐藏，确保点击事件完成后再判断--%>
<%--        $('#keyword').val('');--%>
<%--        $('#productAutoComplete').hide();--%>
<%--    });--%>

<%--    // 点击下拉框选项进行跳转--%>
<%--    $(document).on('click', '.productAutoItem', function () {--%>
<%--        const productId = $(this).data('product-id'); // 获取 data-product-id--%>
<%--        $('#productAutoComplete').hide(); // 隐藏下拉框--%>
<%--        $('#keyword').val(''); // 清空搜索框--%>
<%--        window.location.href = 'http://localhost:8080/webAPP/product?product=' + productId; // 跳转到指定 URL--%>
<%--    });--%>

<%--    window.search = function (element) {--%>
<%--        var productId = $(element).data('product-id'); // 获取 data-product-id--%>
<%--        $('#keyword').val('');--%>
<%--        window.location.href = 'http://localhost:8080/webAPP/product?product=' + productId; // 跳转到指定 URL--%>
<%--    };--%>
<%--});--%>
$(function () {
    let isClickingDropdown = false;

    // 监控输入框内容变化
    $('#keyword').on('keyup', function () {
        var keyword = $(this).val();
        if (keyword !== '' && keyword !== null) {
            $.ajax({
                type: 'GET',
                url: 'productAuto?keyword=' + keyword,
                success: function (data) {
                    console.log(data);
                    var productlistHTML = '';
                    for (var i = 0; i < data.length; i++) {
                        productlistHTML += '<li class="productAutoItem" onclick="search(this)" data-product-id="' + data[i].productId + '">';
                        productlistHTML += data[i].description + ':' + data[i].name;
                        productlistHTML += '</li>';
                    }
                    $('#productAutoList').html(productlistHTML);
                    $('#productAutoComplete').show();
                },
                error: function (xhr, status, error) {
                    console.log('Request failed with status: ' + status);
                    console.log('Error: ' + error);
                }
            });
        } else {
            $('#productAutoComplete').hide();
        }
    });

    // 延迟隐藏以避免冲突
    $('#keyword').on('blur', function () {
        setTimeout(function () {
            if (!isClickingDropdown) {
                $('#productAutoComplete').hide();
                $('#keyword').val('');
            }
            isClickingDropdown = false;
        }, 200);
    });

    // 标记点击下拉框事件
    $(document).on('mousedown', '.productAutoItem', function () {
        isClickingDropdown = true;
    });

    // 点击选项后跳转
    $(document).on('click', '.productAutoItem', function () {
        const productId = $(this).data('product-id'); // 获取 data-product-id
        $('#productAutoComplete').hide(); // 隐藏下拉框
        $('#keyword').val(''); // 清空搜索框
        window.location.href = 'http://localhost:8080/webAPP/product?product=' + productId; // 跳转到指定 URL
    });
});


</script>
</html>
