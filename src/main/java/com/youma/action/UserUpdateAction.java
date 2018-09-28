/**
 * Copyright (C),2015-2018
 * FileNmae: UserUpdateAction
 * Author:   Administrator
 * Date:     2018/9/317:34
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
@WebServlet("/userUpdateAction")
public class UserUpdateAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Users users = new Users();
        UsersServer us = new UsersServerImpl();
        users.setUserID(Integer.parseInt(req.getParameter("userID")));
        users.setUserName(req.getParameter("userName"));
        users.setUserPassword(req.getParameter("userPassword"));
        users.setRealName(req.getParameter("realName"));
        users.setEmail(req.getParameter("email"));
        users.setFlag(Integer.parseInt(req.getParameter("flag")));
        users.setRoleID(Integer.parseInt(req.getParameter("role")));
        if (0 != us.updateUsers(users)) {
            resp.sendRedirect("/his/usersFindAllAction");
        } else {
            System.out.println("修改失败");
            req.setAttribute("false", "失败");
            req.getRequestDispatcher("usersFindAction?drugid=" + req.getParameter("userID")).forward(req, resp);
        }

    }
}
