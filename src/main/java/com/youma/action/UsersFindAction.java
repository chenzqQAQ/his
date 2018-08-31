/**
 * Copyright (C),2015-2018
 * FileNmae: UsersFindAction
 * Author:   Administrator
 * Date:     2018/8/3015:13
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.UsersServer;
import com.youma.server.impl.UsersServerImpl;
import com.youma.vo.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈泽群
 */
@WebServlet("/usersFindAction")
public class UsersFindAction extends HttpServlet {
    public UsersFindAction() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        UsersServer usersServer = new UsersServerImpl();
        if (null != req.getParameter("id")) {
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println(id);
            Users user = usersServer.findUsers(id);
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}
