package com.happydog.web;


import com.happydog.model.*;
import com.happydog.service.CartService;
import com.happydog.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import com.alibaba.fastjson.JSON;

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
            String username=(String)session.getAttribute("username");
            System.out.println( "search from user"+username);
            if(username==null){
                System.out.println("sendRediret login from getCartPage");
                response.sendRedirect("/webAPP/login");
                return;
            }
            System.out.println("try to fin uername"+username);
            List<Item>itemList=cartService.getUserCartItems(username);
            List<CheckoutInfo>checkoutInfoList=cartService.getCheckoutInfoByUsername(username);
            request.setAttribute("itemList", itemList);
            request.setAttribute("checkoutInfoList",checkoutInfoList);
            request.getRequestDispatcher("/WEB-INF/jsp/Cart.jsp").forward(request, response);


        } else if ("removeFromCart".equals(type)) {
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("username");
            System.out.println( "search from user"+username);
            if(username==null){
                System.out.println("sendRediret login from removeFromCart");
                response.sendRedirect("/webAPP/login");
                return;
            }
            String itemId=request.getParameter("itemId");
            System.out.println("清除Cart中的"+itemId);
            cartService.removeItemFromCart(itemId, username);
            response.sendRedirect("/webAPP/cart");
        } else{
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("username");
            System.out.println( "search from user"+username);
            if(username==null){
                System.out.println("sendRediret login from removeFromCart");
                response.sendRedirect("/webAPP/login");
                return;
            }
            request.getRequestDispatcher("/WEB-INF/jsp/Cart.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type =request.getParameter("type");
        if(type.equals("updateQuantity")){
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("username");

            System.out.println(username+" updateQuantity now");
            String itemId=request.getParameter("itemId");
            int quantity=Integer.parseInt(request.getParameter("quantity"));
            System.out.println(itemId+quantity+"udat");
            if(cartService.updateQuantity(itemId,username,quantity)){

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");

                Map<String, Object> responseData = new HashMap<>();
                responseData.put("status", "success");
                responseData.put("message", "商品数量更新成功");
                responseData.put("currentQuantity", quantity);
                String jsonString=JSON.toJSONString(responseData);


                response.getWriter().write(jsonString);
                System.out.println(username+" updateQuantity success");

                //返回给浏览器

            }else System.out.println("update false");
        } else if (type.equals("removeFromCart")) {
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("username");
            System.out.println( "search from user"+username);
            if(username==null){
                System.out.println("sendRediret login from removeFromCart");
                response.sendRedirect("/webAPP/login");
                return;
            }
            String itemId=request.getParameter("itemId");
            System.out.println("清除Cart中的"+itemId);
            cartService.removeItemFromCart(itemId, username);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("status", "success");
            String jsonString=JSON.toJSONString(responseData);
            response.getWriter().write(jsonString);

        }

        else if(type.equals("saveCheckoutInfo")){
            // 获取请求参数
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("username");
            Date orderDate = new Date(System.currentTimeMillis());

            String shipAddr1 = request.getParameter("shipAddr1");
            String shipZip = request.getParameter("shipZip");
            String shipCountry = request.getParameter("shipCountry");
            String courier = request.getParameter("courier");
            String name = request.getParameter("name");
            String creditCard = request.getParameter("creditCard");
            String cardType = request.getParameter("cardType");

            // 验证参数是否为空
            if(shipAddr1 == null || shipZip == null || shipCountry == null || courier == null
                    || name == null || creditCard == null || cardType == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Missing required parameters");
                return;
            }
            CheckoutInfo checkoutInfo=new CheckoutInfo(username,orderDate,shipAddr1,shipZip,shipCountry,courier,name,creditCard,cardType);
            if(cartService.saveCheckoutInfo(checkoutInfo)){
                //给浏览器返回

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("status", "success");
                String jsonString=JSON.toJSONString(responseData);
                response.getWriter().write(jsonString);


            }


        }


    }

}
