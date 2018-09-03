/**
 * Copyright (C),2015-2018
 * FileNmae: UserAddAction
 * Author:   Administrator
 * Date:     2018/8/2915:14
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
@WebServlet("/UserAddAction")
public class UserAddAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * see HttpServlet#HttpServlet()
     */
    public UserAddAction() {
        super();
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
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
        String flag = "fial";
        if ("" != req.getParameter("userName") &&
                "" != req.getParameter("userPassword") &&
                "" != req.getParameter("role")) {

            users.setUserName(req.getParameter("userName"));
            users.setUserPassword(req.getParameter("userPassword"));
            users.setRoleID(Integer.parseInt(req.getParameter("role")));
            if (0 != us.userAdd(users)) {
                System.out.println("添加成功");
                flag = "success";
                req.getRequestDispatcher("/web/User/index.jsp").forward(req, resp);
            }
        } else {
            System.out.println("传值有空");
            resp.sendRedirect("/his/User/addUser.jsp");
        }
    }

}
