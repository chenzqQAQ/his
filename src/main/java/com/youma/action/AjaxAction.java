/**
 * Copyright (C),2015-2018
 * FileNmae: AjaxAction
 * Author:   Administrator
 * Date:     2018/9/69:29
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.UsersServer;
import com.youma.server.impl.UsersServerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 陈泽群
 */
@WebServlet("/ajaxAction")
public class AjaxAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        System.out.println(req.getQueryString());
        // System.out.println("进入ajax");
        String action = req.getParameter("action");
        String userName = req.getParameter("userName");
        // System.out.println("用户名为" + userName);
        UsersServer usersServer = new UsersServerImpl();
        PrintWriter printWriter = resp.getWriter();
        if (null == userName || "".equals(userName)) {
            printWriter.write("2");
        } else if (0 != usersServer.findUsers(userName)) {
            printWriter.write("1");
        } else {
            printWriter.write("0");
        }
        printWriter.flush();
    }
}
