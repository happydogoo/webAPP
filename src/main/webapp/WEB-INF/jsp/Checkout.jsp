<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>结账页面</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/MainHeader.jsp"/>

<h1>结账</h1>

<form action="checkout" method="post">
    <label for="shipAddr1">送货地址:</label>
    <input type="text" id="shipAddr1" name="shipAddr1" required><br><br>

    <label for="shipZip">邮政编码:</label>
    <input type="text" id="shipZip" name="shipZip" required><br><br>

    <label for="shipCountry">国家:</label>
    <input type="text" id="shipCountry" name="shipCountry" required><br><br>

    <label for="courier">快递公司:</label>
    <input type="text" id="courier" name="courier" required><br><br>

    <label for="totalPrice">总价格:</label>
    <input type="text" id="totalPrice" name="totalPrice" required><br><br>

    <label for="name">收件人姓名:</label>
    <input type="text" id="name" name="name" required><br><br>


    <label for="creditCard">信用卡号:</label>
    <input type="text" id="creditCard" name="creditCard" required><br><br>

    <label for="cardType">信用卡类型:</label>
    <input type="text" id="cardType" name="cardType" required><br><br>

    <input type="submit" value="提交订单">
</form>
</body>
</html>
