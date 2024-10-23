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
        // 1. 获取表单中的用户输入数据
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String userEmail = request.getParameter("email");
        String address =request.getParameter("address");
        String phone =request.getParameter("phoone");
        // 2. 检查密码是否一致
        if (!password.equals(confirmPassword)) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<h3>密码不一致，请重新输入！</h3>");
                return;
            }
        }

        // 3. 创建User对象（封装表单数据）
        User user = new User(userEmail, userName, address,phone,password);

        // 4. 调用DAO类保存用户数据
        UserDao userDAO = new UserDao();
        boolean isSaved = userDAO.saveUser(user);

        // 5. 处理保存结果反馈
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (isSaved) {
                out.println("<h3>注册成功！</h3>");
                System.out.println("success");
            } else {
                out.println("<h3>注册失败，请重试！</h3>");
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);

    }

}
