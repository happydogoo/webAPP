<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.happydog.model.Item" %>
<%@ page import="com.happydog.service.CartService" %>
<%@ page import="com.happydog.model.CheckoutInfo" %>
<%
    // 假设 CartService 用于获取购物车项目
    CartService cartService = new CartService();
    String username = (String) session.getAttribute("username"); // 从会话中获取用户名
    List<Item> cartItems = cartService.getUserCartItems(username); // 获取购物车项目
    List<CheckoutInfo>checkoutInfoList=cartService.getCheckoutInfoByUsername(username);
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
        h2{
            margin: 0;
        }
        h3{
            margin: 0;
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
        .flex-list {
            width:100%;
            display: flex;
            flex-wrap: wrap;
            padding: 6px;
            list-style: none;
        }
        .flex-list li {
            flex: 0 auto;
            margin: 10px 15px;
            min-width: calc(30.666% - 20px);
        }

        .flex-list li a {
            display: flex;
            height: 200px;
            padding: 18px;
            border: 1.4px solid #ccc;
            border-radius: 30px;
            background-color: #fff;
            text-decoration: none;
            color: #044004;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
            font-weight: bold;
            flex-direction: column;
            justify-content: flex-start; /* 子元素水平靠左对齐 */
            align-items: flex-start; /* 子元素垂直靠上对齐 */
        }
    </style>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/common/MainHeader.jsp"/>



<h1>购物车</h1>

<table style="margin-left: 3%">
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
<h2 id="totalPrice1">总价: <%= totalPrice %></h2>
    <h2 >选择您的信息</h2>
    <ul class="flex-list">




        <%
            if (checkoutInfoList != null && !checkoutInfoList.isEmpty()) {
                for (CheckoutInfo checkoutInfo : checkoutInfoList) {
        %>
        <li class="">
            <a href="#"
               onclick="chooseCheckoutInfo(this)"
               data-ship-addr1="<%= checkoutInfo.getShipAddr1() %>"
               data-ship-zip="<%= checkoutInfo.getShipZip() %>"
               data-ship-country="<%= checkoutInfo.getShipCountry() %>"
               data-courier="<%= checkoutInfo.getCourier() %>"
               data-name="<%= checkoutInfo.getShipToName() %>"
               data-credit-card="<%= checkoutInfo.getCreditCard() %>"
               data-card-type="<%= checkoutInfo.getCardType() %>">

                <h2 style="font-weight: bold; margin-bottom: 5px;">
                    <%= checkoutInfo.getShipCountry() %>
                    <%= checkoutInfo.getShipToName() %>
                </h2>
                <hr style="border: 1px solid #808080; margin: 10px 0; width: 100%">
                <h3 style="margin-bottom: 5px;">
                    <%= checkoutInfo.getShipAddr1() %>
                </h3>
                <h3>邮编: <%= checkoutInfo.getShipZip() %></h3>
                <h3 style="border-top: 2px solid #ddd;"></h3>
                <h3>快递公司: <%= checkoutInfo.getCourier() %></h3>
                <h3><%= checkoutInfo.getCardType() %>银行: <%= checkoutInfo.getCreditCard() %></h3>
            </a>
        </li>
        <%
                }
            }
        %>

        <li id="addCheckoutInfo" ><a  href="#" onclick="addCheckoutInfo(this)" style="justify-content: center; align-items: center;font-weight: bold; font-size: 24px">添加新的信息</a></li>


<%--        style="min-width: calc(30.666% - 20px);"--%>
<%--        style="--%>
<%--        display: flex;--%>
<%--        align-items: center;--%>
<%--        justify-content: center;--%>
<%--        min-width: calc(30.666% - 20px);--%>
<%--        width: 100%;--%>
<%--        height: 200px;--%>
<%--        padding: 18px;--%>
<%--        border: 1.4px solid #ccc;--%>
<%--        border-radius: 30px;--%>
<%--        background-color: #fff;--%>
<%--        text-decoration: none;--%>
<%--        color: #044004;--%>
<%--        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);--%>
<%--        transition: all 0.3s ease;--%>
<%--        font-weight: bold;"--%>







    </ul>
<form action="checkout" method="POST">
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

    <div id="popup" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); padding: 20px; background: #fff; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); z-index: 1000; border-radius: 10px;color: black">

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

        <button onclick="closePopup(this)">确认</button>

    </div>

    <%--这个用于背景--%>
    <div id="overlay" style=" display:none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.5); z-index: 999;"></div>

    <input type="submit" value="提交订单" style="
    padding: 15px 30px;
    font-size: 18px;
    border-radius: 12px;
    border: 2px solid #ccc;
    background-color: #4CAF50;
    color: white;
    cursor: pointer;
    transition: all 0.3s ease;
    margin-left: 30px;"
           onmouseover="this.style.backgroundColor='#45a049'; this.style.borderColor='#45a049';"
           onmouseout="this.style.backgroundColor='#4CAF50'; this.style.borderColor='#ccc';">

</form>

</div>


</body>
</html>
