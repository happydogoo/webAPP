package com.happydog.web;
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

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
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


        Date orderDate = new Date(System.currentTimeMillis());



    }
}
