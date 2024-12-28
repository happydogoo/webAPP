package com.happydog.web;

import com.happydog.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserInfoServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        if(username==null){
            System.out.println("sendRediret login from product");
            response.sendRedirect("/webAPP/login");
            return;
        }
    request.getRequestDispatcher("/WEB-INF/jsp/User.jsp").forward(request, response); // 转发到结果页面
    }
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

        String currentPassword=request.getParameter("currentPassword");
        String newPassword=request.getParameter("newPassword");
        String newUsername=request.getParameter("newUsername");
        String confirmPassword=request.getParameter("confirmPassword");
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        if(username==null){
            System.out.println("sendRediret login from product");
            response.sendRedirect("/webAPP/login");
            return;
        }

        if(confirmPassword.equals(newPassword)){
            if(userService.changePassword(username,newPassword,currentPassword)){
                userService.changeUsername(username,newUsername);



               try{ response.sendRedirect("/webAPP/login");
            }
               catch (Exception e){
                   e.printStackTrace();
               }
            }
            else{
                try{sendErrorResponse(request,response,"修改错误");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
        else {
            try{sendErrorResponse(request,response,"两次密码不同");
        }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
    private void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException {
        request.setAttribute("errorMessage", message);
        System.out.println("change password error");
        request.getRequestDispatcher("/WEB-INF/jsp/User.jsp").forward(request, response);
    }
}