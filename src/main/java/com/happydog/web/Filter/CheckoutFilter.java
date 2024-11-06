package com.happydog.web.Filter;

import com.happydog.model.Log;
import com.happydog.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(urlPatterns = {"/checkout"}) // 仅对结账请求进行日志记录
public class CheckoutFilter implements Filter {

    private LogService logService = new LogService();  // 日志服务类

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化工作
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 转换为 HttpServletRequest 和 HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取当前用户的用户名和 ID
        HttpSession session = httpRequest.getSession();
        String username = (String) session.getAttribute("username");
        String userId = (String) session.getAttribute("userId");
        String clientIp = httpRequest.getRemoteAddr();

        // 获取所有请求参数（假设商品信息作为参数传递）
        Enumeration<String> parameterNames = httpRequest.getParameterNames();
        StringBuilder productDetails = new StringBuilder();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = httpRequest.getParameter(paramName);
            productDetails.append(paramName).append(": ").append(paramValue).append(", ");
        }

        // 移除最后一个逗号和空格
        if (productDetails.length() > 0) {
            productDetails.setLength(productDetails.length() - 2);
        }

        // 记录用户结账的日志
        Log log = new Log(userId, username, "订单", "Order", productDetails.toString(), productDetails.toString(), clientIp);
        logService.checkoutLog(log); // 记录日志的方法需实现

        // 继续请求处理，传递给下一个过滤器或最终的 Servlet
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 清理工作
    }
}
