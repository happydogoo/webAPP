package com.happydog.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


import com.happydog.model.Item;
import com.happydog.model.Product;
import com.happydog.service.ItemService; // 假设你有一个服务来处理商品相关操作
import com.happydog.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private ItemService itemService = new ItemService(); // 实例化服务
    private ProductService productService=new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<Product> searchResults = productService.searchProduct(query); // 假设你有一个搜索方法

        request.setAttribute("results", searchResults);
        System.out.println(searchResults+"searchReuslys");
        request.getRequestDispatcher("/WEB-INF/jsp/Results.jsp").forward(request, response); // 转发到结果页面
    }
}