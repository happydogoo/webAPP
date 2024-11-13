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

        Enumeration<String> parameterNames = httpRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            // 只处理 itemId 参数
            if ("itemId".equals(paramName)) {
                String itemId = httpRequest.getParameter(paramName);

                // 创建并记录日志
                Log log = new Log(userId, username, "生成订单", "Order", itemId, itemId, clientIp);
                logService.checkoutLog(log); // 假设 checkoutLog() 方法能处理日志对象
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 清理工作
    }
}
