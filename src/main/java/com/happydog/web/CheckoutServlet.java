package com.happydog.web;
import com.happydog.model.Order;
import com.happydog.persistence.CheckoutDao;
import com.happydog.persistence.DBConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CheckoutDao checkoutDao=new CheckoutDao();

        String username = (String) session.getAttribute("username");

        // 获取表单中的订单信息
        String shipAddr1 = request.getParameter("shipAddr1");
        String shipZip = request.getParameter("shipZip");
        String shipCountry = request.getParameter("shipCountry");
        String courier = request.getParameter("courier");
        BigDecimal totalPrice = new BigDecimal(request.getParameter("totalPrice"));
        String name = request.getParameter("name"); // 修改为只获取名字
        String creditCard = request.getParameter("creditCard");
        String cardType = request.getParameter("cardType");

        String[] itemIds = request.getParameterValues("itemId");
        String[] itemQuantities = request.getParameterValues("itemQuantity");



        Date orderDate = new Date(System.currentTimeMillis());


        for (int i = 0; i < itemIds.length; i++) {
        Order order = new Order();
        order.setUsername(name);
        order.setOrderDate(orderDate);
        order.setShipAddr1(shipAddr1);
        order.setShipZip(shipZip);
        order.setShipCountry(shipCountry);
        order.setCourier(courier);
        order.setTotalPrice(totalPrice);
        order.setShipToName(name);
        order.setCreditCard(creditCard);
        order.setCardType(cardType);
        order.setItemId(itemIds[i]);

            int quantity = Integer.parseInt(itemQuantities[i]);
            order.setQuantity(quantity); // 如果 Order 类中有设置数量的方法

            checkoutDao.insertOrder(order);



        }
        response.sendRedirect("/webAPP/order");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("request register");

    }
}
