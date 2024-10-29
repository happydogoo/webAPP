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
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            boolean loginResult = userDao.checkLogin(userName, password);
            if (loginResult) {
                System.out.println("pwd correct");
                response.sendRedirect("/webAPP/main");
                HttpSession session = request.getSession();
                session.setAttribute("username", userName);
                session.setAttribute("loginStatus", true);

            }
            else{
                System.out.println("pwd incorrect");
            }
        }


    }
}