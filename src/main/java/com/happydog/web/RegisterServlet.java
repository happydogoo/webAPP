package com.happydog.web;

import com.google.gson.Gson;
import com.happydog.model.User;
import com.happydog.persistence.UserDao;
import com.happydog.util.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.UUID;
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
        String userInputCaptcha=request.getParameter("captcha");
        UUID UUIDuserId=UUID.randomUUID();
        String userId=UUIDuserId.toString();

        HttpSession session=request.getSession();
        String realCaptcha=(String) session.getAttribute("captcha");


        if (!password.equals(confirmPassword)) {
            try (PrintWriter out = response.getWriter()) {
                sendErrorResponse(request,response, "密码不一致，请重新输入！");
                return;
            }
        }


        if (userInputCaptcha == null || !userInputCaptcha.equalsIgnoreCase(realCaptcha)) {
            sendErrorResponse(request, response, "验证码错误");
            return;
        }



        //创建User对象（封装表单数据）
        User user = new User(userEmail, userName, address,phone,password,userId);


        UserDao userDAO = new UserDao();
        boolean isSaved = userDAO.saveUser(user);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (isSaved) {
                response.sendRedirect("/webAPP/login");
                System.out.println("register success");


                //记得重定向
                //这里要用session来保存状态


            } else {

                //这个还要看看
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("/WEB_INF/jsp/Register.jsp").forward(request, response);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String captchaText = CaptchaUtils.createCaptchaImage(request,response);
//
//        HttpSession session=request.getSession();
//        //保存验证码
//        session.setAttribute("captcha",captchaText);
 //       System.out.println("captcha"+captchaText);
        System.out.println("request register");
           request.getRequestDispatcher("/WEB-INF/jsp/Register.jsp").forward(request, response);

    }
    private void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/WEB-INF/jsp/Register.jsp").forward(request, response);
    }


}
