package com.happydog.web;


import com.happydog.model.Cart;
import com.happydog.model.Category;
import com.happydog.model.Item;
import com.happydog.model.Product;
import com.happydog.service.CartService;
import com.happydog.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private  static final Logger logger=Logger.getLogger(LoginServlet.class.getName());
    private CartService cartService=new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String type =request.getParameter("type");

        if ("getCartPage".equals(type)) {
            HttpSession session=request.getSession();
            String username= (String)session.getAttribute("username");
            List<Item>itemList=cartService.getUserCartItems(username);

            request.setAttribute("itemList", itemList);
            request.getRequestDispatcher("/WEB-INF/jsp/Cart.jsp").forward(request, response);


        }
        else{
            request.getRequestDispatcher("/WEB-INF/jsp/Cart.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 可以处理表单提交的逻辑，然后再转发到 JSP
        doGet(request, response);
    }

}
