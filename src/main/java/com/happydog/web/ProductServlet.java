package com.happydog.web;

import com.happydog.model.Cart;
import com.happydog.model.Item; // 假设有一个 Item 类
import com.happydog.model.Product;
import com.happydog.service.CartService;
import com.happydog.service.ProductService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ProductServlet.class.getName());
    private ProductService productService = new ProductService();
    private CartService cartService=new CartService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type=request.getParameter("type");
        if("addToCart".equals(type)) {
            System.out.println("prouductServlet");
            String itemId = request.getParameter("itemId");
            HttpSession session = request.getSession();
            String username=(String)session.getAttribute("username");
            int quantity= 1;

            if(username==null){
                System.out.println("sendRediret login from product");
                response.sendRedirect("/webAPP/login");
                return;
            }

            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            cartService.addItemToCart(itemId,username,quantity);
            request.getRequestDispatcher("/WEB-INF/jsp/Product.jsp").forward(request, response);


        } else if ("getItems".equals(type)) {
            System.out.println("try to floating-window getItems");
            String productId=request.getParameter("product");
            List<Item> itemList= productService.getItemById(productId);
            String jsonString = JSON.toJSONString(itemList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonString);
            System.out.println(jsonString);
        } else{
        String productId = request.getParameter("product");

        if (productId == null || productId.isEmpty()) {
            logger.warning("Product name is missing");
            response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
            return;
        }

        // 获取产品信息
        List<Item> itemList = productService.getItemById(productId); // 使用 getItemById 方法

        // 存入会话并转发到产品详情页面
        HttpSession session = request.getSession();
        session.setAttribute("product", productId);
        session.setAttribute("itemList", itemList);
        request.getRequestDispatcher("/WEB-INF/jsp/Product.jsp").forward(request, response);


    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
