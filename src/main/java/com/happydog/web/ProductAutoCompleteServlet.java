package com.happydog.web;

import com.alibaba.fastjson.JSON;
import com.happydog.model.Product;
import com.happydog.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/productAuto")
public class ProductAutoCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String keyword = req.getParameter("keyword");
            System.out.println("Received keyword: " + keyword); // 打印接收到的关键字

            if (keyword == null || keyword.trim().isEmpty()) {
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().println("[]"); // 返回空数组
                return;
            }

            CategoryService service = new CategoryService();
            List<Product> productlist = service.searchProductList(keyword);
            System.out.println("Product list size: " + (productlist != null ? productlist.size() : 0)); // 打印结果大小

            String result = JSON.toJSONString(productlist);
            System.out.println("Returned JSON: " + result); // 打印返回的 JSON




            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println(result);
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常堆栈
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("{\"error\":\"Internal Server Error\"}");
        }
    }
}
