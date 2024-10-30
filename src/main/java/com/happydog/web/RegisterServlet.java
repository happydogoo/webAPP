package com.happydog.web;

import com.happydog.model.User;
import com.happydog.persistence.UserDao;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegisterServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("register servlet dopost");

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String userEmail = request.getParameter("email");
        String address =request.getParameter("address");
        String phone =request.getParameter("phone");

        if (!password.equals(confirmPassword)) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<h3>密码不一致，请重新输入！</h3>");
                return;
            }
        }

        //创建User对象（封装表单数据）
        User user = new User(userEmail, userName, address,phone,password);


        UserDao userDAO = new UserDao();
        boolean isSaved = userDAO.saveUser(user);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (isSaved) {
                response.sendRedirect("/webAPP[=**-" +
                        "login");
                System.out.println("register success");


                //记得重定向
                //这里要用session来保存状态


            } else {

                //这个还要看看
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("request register");
           request.getRequestDispatcher("/WEB-INF/jsp/Register.jsp").forward(request, response);

    }

}
