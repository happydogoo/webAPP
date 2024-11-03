<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happydog.model.Item" %>

<html>
<head>
    <title>Product Details</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/common/MainHeader.jsp"/>

<%
    // 从 session 中获取产品 ID 和 itemList
    String productId = (String) session.getAttribute("product");
    List<Item> itemList = (List<Item>) session.getAttribute("itemList");

    if (itemList != null && !itemList.isEmpty()) {
%>
<h1>Product ID: <%= productId %></h1>
<h2>Items:</h2>
<ul>
    <% for (Item item : itemList) { %>
    <li>
        <strong>Item Name:</strong> <%= item.getItemId() %><br>
        <strong>Description:</strong> <%= item.getAttribute1() %><br>
        <strong>Price:</strong> <%= item.getListPrice() %><br>
    </li>
    <% } %>
</ul>
<%
} else {
%>
<h2>No items found for this product.</h2>
<%
    }
%>

</body>
</html>
