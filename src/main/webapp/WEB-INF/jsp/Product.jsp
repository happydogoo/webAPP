<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happydog.model.Item" %>


<html>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


<head>
    <title>Product Details</title>
    <style>
        body{
            z-index: 1;
        }
        .product-details{

            display: flex;
            flex-direction: column;
            /*justify-content: center;*/
            /*align-items: center;*/
            margin-left: 5%;
            height: 100vh;

            color: #f0f0f0; /* 深灰色字体 */
        }

    </style>
</head>
<body>

<div class="header-container">
    <jsp:include page="/WEB-INF/jsp/common/MainHeader.jsp"/>
</div>

<div class="product-details">
    <%
        String productId = (String) session.getAttribute("product");
        List<Item> itemList = (List<Item>) session.getAttribute("itemList");

        if (itemList != null && !itemList.isEmpty()) {
    %>
    <h1>Product ID: <%= productId %></h1>
    <h2>Items:</h2>
    <ul>
        <% for (Item item : itemList) { %>
        <li>
            <strong>Item:</strong> <%= item.getItemId() %><br>
            <strong>Description:</strong> <%= item.getAttribute1() %><br>
            <strong>Price:</strong> <%= item.getListPrice() %><br>
            <form  method="post">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>"/>
                <input type="hidden" name="type" value="addToCart">
                <input type="submit" value="添加至购物车"/>
            </form>

        </li>
        <% } %>
    </ul>


    <form action="cart" method="get">
        <input type="hidden" name="type" value="getCartPage">

        <input type="submit" value="购物车"/>
    </form >


    <%
    } else {
    %>
    <h1>Product ID: <%= productId %></h1>
    <h2>No items found for this product.</h2>
    <%
        }
    %>
</div>

</body>
</html>
