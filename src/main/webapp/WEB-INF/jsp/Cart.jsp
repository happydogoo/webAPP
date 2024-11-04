<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.happydog.model.Item" %>
<%@ page import="com.happydog.service.CartService" %>
<%
    // 假设 CartService 用于获取购物车项目
    CartService cartService = new CartService();
    String username = (String) session.getAttribute("username"); // 从会话中获取用户名
    List<Item> cartItems = cartService.getUserCartItems(username); // 获取购物车项目

    BigDecimal totalPrice = BigDecimal.ZERO; // 初始化总价
%>
<html>
<head>
    <title>购物车</title>
    <style>
        body{
            background: #f0f0f0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        .checkout-btn {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/common/MainHeader.jsp"/>

<h1>购物车</h1>

<table>
    <thead>
    <tr>
        <th>商品ID</th>
        <th>描述</th>
        <th>价格</th>
        <th>数量</th> <!-- 新增的数量列 -->
    </tr>
    </thead>
    <tbody>
    <%
        if (cartItems != null && !cartItems.isEmpty()) {
            for (Item item : cartItems) {
                //暂时还没做多个数量的购物车功能
                int quantity = 1; // 假设 Item 有 getQuantity() 方法
                BigDecimal price = item.getListPrice(); // 假设 ListPrice 是 BigDecimal
                BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(quantity)); // 计算每个商品的总价
                totalPrice = totalPrice.add(itemTotal); // 累加总价
                String itemId=item.getItemId();

    %>
    <tr>
        <td><%= item.getItemId() %></td>
        <td><%= item.getAttribute1() %></td>
        <td><%= price %></td>
        <td><%= quantity %></td> <!-- 显示数量 -->
        <td>
            <form  method="post">
                <input type="hidden" name="type" value="removeFromCart">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="submit" value="clear">
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">购物车为空</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<!-- 显示总价 -->
<h2>总价: <%= totalPrice %></h2>

<form action="<%= request.getContextPath() %>/checkout" method="post">
    <input type="submit" class="checkout-btn" value="结算"/>
</form>

</body>
</html>
