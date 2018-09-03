/**
 * Copyright (C),2015-2018
 * FileNmae: UsersFindAllAction
 * Author:   Administrator
 * Date:     2018/8/3016:07
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
import java.util.List;

/**
 * @author 陈泽群
 */
@WebServlet("/usersFindAllAction")
public class UsersFindAllAction extends HttpServlet {
    public UsersFindAllAction() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersServer usersServer = new UsersServerImpl();
        List<Users> list = usersServer.findAllUsers();
        System.out.println("全查" + list.size());
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        req.setAttribute("users", list);
        req.getRequestDispatcher("/User/index.jsp").forward(req, resp);
    }
}
