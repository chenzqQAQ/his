/**
 * Copyright (C),2015-2018
 * FileNmae: loginFilter
 * Author:   Administrator
 * Date:     2018/9/25 9:55
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * loginFilter
 * 登录过滤器
 *
 * @author 陈泽群
 * @date 2018/9/25 9:55
 */
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("登录系统过滤器启动");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI();
        // System.out.println("过滤的url请求为" + path);
        if (path.contains("/login.jsp")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if (path.contains("/his/checkCode")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if (path.contains("/his/getCode")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if (req.getSession().getAttribute("realName") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if (StringUtils.containsAny(path, ".css", ".js")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            resp.sendRedirect("/his/login.jsp");
        }
    }

    @Override
    public void destroy() {
        System.out.println("登录系统过滤器销毁");
    }
}
