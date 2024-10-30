package com.happydog.web;

import com.happydog.model.Category;
import com.happydog.model.Product;
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

@WebServlet(urlPatterns = {"/main", "/category"})
public class MainServlet extends HttpServlet {
    private  static final Logger logger=Logger.getLogger(LoginServlet.class.getName());
   private CategoryService categoryService=new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type =request.getParameter("type");
        if(type==null||type.isEmpty())type="DOGS";
        Category category= categoryService.getCategory(type);
        List<Product> productList = categoryService.getProductListByCategory(type);

        System.out.println("request main page");
          categoryService=new CategoryService();
        HttpSession session=request.getSession();
        session.setAttribute("category",category);
        session.setAttribute("productList",productList);
        request.getRequestDispatcher("/WEB-INF/jsp/Main.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 可以处理表单提交的逻辑，然后再转发到 JSP
        doGet(request, response);
    }
}
