package com.happydog.web.Filter;

import com.happydog.model.Log;
import com.happydog.service.LogService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/product"})  // 仅对商品相关请求进行日志记录
public class CartFilter implements Filter {

    private LogService logService = new LogService();  // 日志服务类，用于将日志写入数据库

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 可以在此进行一些初始化工作，当前不需要
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("enter cartfilter");
        // 将 ServletRequest 和 ServletResponse 转换为 HttpServletRequest 和 HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        String itemId = httpRequest.getParameter("itemId");
        HttpSession session = httpRequest.getSession();
        String username = (String) session.getAttribute("username");

        if ((itemId != null)&&(username!=null)) {
            // 获取当前用户的用户名（假设通过 session 存储用户名）
             String userId=(String) session.getAttribute("userId") ;
            // 获取客户端的 IP 地址
            String clientIp = httpRequest.getRemoteAddr();
            String targetId= itemId;
            // 记录用户点击商品的日志
            Log log=new Log(userId, username, "a", "Cart",  targetId, targetId, clientIp);
            logService.addItemToCartLog(log);
        }

        // 继续请求处理，传递给下一个过滤器或最终的 Servlet
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 进行清理操作，这里不需要额外的清理工作
    }
}
