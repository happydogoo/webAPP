package com.happydog.web;

import com.happydog.persistence.UserDao;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.logging.Logger;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
   UserDao userDao=new UserDao();
    private  static final Logger logger=Logger.getLogger(LoginServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("请求login页面");
        logger.fine("请求login页面");
        // 转发请求到 /WEB-INF/jsp/Login.jsp
        request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //要根据post的action方法来进行区分
        String action=request.getParameter("action");

        System.out.println("loginservlet doPost");
        if(action.equals("login")) {
            System.out.println("loginServlet try login");
            HttpSession session= request.getSession();
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String userInputCaptcha=request.getParameter("captcha");
            String realCaptcha=(String) session.getAttribute("captcha");
            if (userInputCaptcha == null || !userInputCaptcha.equalsIgnoreCase(realCaptcha)) {

                sendErrorResponse(request, response, "验证码错误");


                return;
            }
            boolean loginResult = userDao.checkLogin(userName, password);
            if (loginResult) {
                System.out.println("pwd correct");
                response.sendRedirect("/webAPP/");

                session.setAttribute("username", userName);
                String userId=userDao.getUserId(userName);
                session.setAttribute("userId",userId);
                session.setAttribute("loginStatus", true);

            }
            else{
                sendErrorResponse(request,response,"密码错误");
                System.out.println("pwd incorrect");
            }
        }


    }private void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException {
        request.setAttribute("errorMessage", message);
        System.out.println("errorMesadawdaw"+message);
        request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
    }
}