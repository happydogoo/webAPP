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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/Cart.js"></script>

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
        .box {                    /*box这一块改的是购物车下面的信息*/
            display: flex;
            flex-direction: column;
            align-items: flex-start; /* 使内容靠左对齐 */
            margin-left: 5%; /* 距离左边15%，你要是不满意可以自己改一下位置 */
            color: #fff; /* 字体颜色设置为白色 */
            padding: 20px; /* 根据需要添加内边距 */
            border-radius: 5px; /* 可选：添加圆角 */
        }

        h1 {
            color: #fff; /* 将“购物车”标题字体颜色设置为白色 */
            margin-left: 48%;
        }



        /* 新增的样式 */
        table{
            background-color: #fff; /* 白色背景 */
            color: #000; /* 黑色文字 */
        }

        input, button {
            background-color: #fff; /* 白色背景 */
            color: #000; /* 黑色文字 */
            border: 1px solid #ddd; /* 边框颜色 */
        }

        button, .checkout-btn {
            background-color: #4CAF50; /* 绿色背景 */
            color: white; /* 白色文字 */
            border: none; /* 无边框 */
            cursor: pointer; /* 鼠标悬停时显示指针 */
        }

        button:hover, .checkout-btn:hover {
            background-color: #45a049; /* 鼠标悬停时的背景颜色 */
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
        <th>数量</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (cartItems != null && !cartItems.isEmpty()) {
            for (Item item : cartItems) {
                int quantity = item.getQuantity();
                BigDecimal price = item.getListPrice(); // 假设 ListPrice 是 BigDecimal
                BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(quantity)); // 计算每个商品的总价
                totalPrice = totalPrice.add(itemTotal); // 累加总价
                String itemId=item.getItemId();

    %>

    <tr>
        <td><%= item.getItemId() %></td>
        <td><%= item.getAttribute1() %></td>
        <td><%= price %></td>
        <td >
            <form  style="display:inline;">
                <input type="hidden" name="type" value="decreaseQuantity">
                <input type="hidden" id="itemId" name="itemId" data-id=<%=itemId%> value="<%= item.getItemId() %>">
                <input type="submit" value="-" onclick="decreaseQuantity(this)" style="width:30px; height:30px;"/>
            </form>
            <span class="quantity"> <%= quantity %></span>
            <form  style="display:inline;">
                <input type="hidden" name="type" value="increaseQuantity">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="submit" value="+" onclick="increaseQuantity(this)" style="width:30px; height:30px;"/>
            </form>
        </td>
        <td>
            <form>
                <input type="hidden" name="type" value="removeFromCart">
                <input type="hidden" name="itemId" data-id=<%=itemId%> value="<%= item.getItemId() %>">
                <input type="submit" value="清除" onclick="deleteItem(this)">
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

<div class="box">
<!-- 显示总价 -->
<h2>总价: <%= totalPrice %></h2>

<form action="checkout" method="post">
    <label for="shipAddr1">送货地址:</label>
    <input type="text" id="shipAddr1" name="shipAddr1" required><br><br>

    <label for="shipZip">邮政编码:</label>
    <input type="text" id="shipZip" name="shipZip" required><br><br>

    <label for="shipCountrySelect">国家:</label>
    <select id="shipCountrySelect" name="shipCountry" required>
        <option value="">请选择国家</option>
        <option value="US">美国</option>
        <option value="CA">加拿大</option>
        <option value="GB">英国</option>
        <option value="DE">德国</option>
        <option value="FR">法国</option>
        <option value="IT">意大利</option>
        <option value="ES">西班牙</option>
        <option value="CN">中国</option>
        <option value="IN">印度</option>
        <option value="JP">日本</option>
    </select><br><br>
    <label for="courier">快递公司:</label>
    <input type="text" id="courier" name="courier" required><br><br>


    <input type="hidden" id="totalPrice" name="totalPrice"  value=<%= totalPrice %>>

    <label for="name">收件人姓名:</label>
    <input type="text" id="name" name="name" required><br><br>


    <label for="creditCard">信用卡号:</label>
    <input type="text" id="creditCard" name="creditCard" required><br><br>

    <label for="cardType">信用卡类型:</label>
    <input type="text" id="cardType" name="cardType" required><br><br>

    <%
        if (cartItems != null && !cartItems.isEmpty()) {
            for (Item item : cartItems) {
                BigDecimal price = item.getListPrice(); // 获取每个商品的单价
    %>
    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
    <input type="hidden" name="itemQuantity" value="<%= item.getQuantity() %>"> <!-- 假设数量为1 -->
    <input type="hidden" name="itemPrice" value="<%= price %>"> <!-- 每个商品的单价 -->
    <%
            }
        }
    %>



    <input type="submit" value="提交订单">
</form>

</div>

</body>
</html>
