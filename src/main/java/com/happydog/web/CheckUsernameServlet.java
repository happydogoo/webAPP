package com.happydog.web;

import com.happydog.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Servlet 路径映射
@WebServlet("/checkUsername")
public class CheckUsernameServlet extends HttpServlet {

    // 将 UserService 作为类的成员变量，避免重复创建
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(); // 初始化 Service 层对象
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应类型和字符集
        System.out.println("请求checkservlet");
        response.setContentType("application/json;charset=UTF-8");

        // 获取前端传来的 username 参数
        String username = request.getParameter("username");

        // 调用 Service 层判断用户名是否存在
        boolean exists = userService.isUsernameExists(username);

        // 手动拼接 JSON 字符串
        String jsonResponse = "{\"exists\": " + exists + "}";

        // 发送 JSON 数据
        response.getWriter().write(jsonResponse);
    }
}
