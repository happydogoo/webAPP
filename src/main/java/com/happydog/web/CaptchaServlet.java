package com.happydog.web;

import com.google.gson.Gson;
import com.happydog.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建验证码图片并返回图片上的文本
        String captchaText = CaptchaUtils.createCaptchaImage(request,response);
        // 将验证码文本保存到Session中
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captchaText);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户输入的验证码
        String userInputCaptcha = request.getParameter("captcha");

        // 从Session中获取真正的验证码
        HttpSession session = request.getSession();
        String realCaptcha = (String) session.getAttribute("captcha");


        // 比较用户输入的验证码和真正的验证码是否一致
        // 设置响应类型为JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        String json;
        if (userInputCaptcha != null && userInputCaptcha.equalsIgnoreCase(realCaptcha)) {
            json=gson.toJson("success");
        } else {
            json=gson.toJson("fail");
        }
        out.write(json);
    }
}