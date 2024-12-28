<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happydog.model.Order" %>
<%
    List<Order> orders = (List<Order>) request.getAttribute("orders"); // 获取订单列表
%>
<html>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


<head>
    <title>订单列表</title>
    <style>
        body {
            background: #f0f0f0;
            font-family: Arial, sans-serif;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        .box {                    /*box这一块改的是购物车下面的信息*/
            display: flex;
            flex-direction: column;
            align-items: flex-start; /* 使内容靠左对齐 */
            margin-left: 0%; /* 距离左边15%，你要是不满意可以自己改一下位置 */
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

<jsp:include page="/WEB-INF/jsp/common/MainHeader.jsp"></jsp:include>
<div class="box">
<h1>订单列表</h1>

<table>
    <thead>
    <tr>
        <th>订单ID</th>
        <th>商品ID</th>
        <th>用户名</th>
        <th>订单日期</th>
        <th>送货地址</th>
        <th>邮政编码</th>
        <th>国家</th>
        <th>快递公司</th>
        <th>总价格</th>
        <th>收件人姓名</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
    %>
    <tr>
        <td><%= order.getOrderId() %></td>
        <td><%= order.getItemId() %></td>
        <td><%= order.getUsername() %></td>
        <td><%= order.getOrderDate() %></td>
        <td><%= order.getShipAddr1() %></td>
        <td><%= order.getShipZip() %></td>
        <td><%= order.getShipCountry() %></td>
        <td><%= order.getCourier() %></td>
        <td><%= order.getTotalPrice() %></td>
        <td><%= order.getShipToName() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="9">没有订单记录</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</div>
</body>
</html>
