package com.happydog.web;

import com.happydog.model.Order;
import com.happydog.service.CheckoutService;
import com.happydog.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private CheckoutService checkoutService=new CheckoutService();
    private OrderService orderService=new OrderService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        List<Order> orders = orderService.getOrders(username); // 获取所有订单
        request.setAttribute("orders", orders); // 将订单列表设置到请求属性中

        // 转发到 JSP 页面显示订单
        request.getRequestDispatcher("/WEB-INF/jsp/Order.jsp").forward(request, response);
    }



    }
