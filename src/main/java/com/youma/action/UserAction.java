/**
 * Copyright (C),2015-2018
 * FileNmae: UserAction
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
@WebServlet("/UserAciton")
public class UserAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * see HttpServlet#HttpServlet()
     */
    public UserAction() {
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
        Users users = new Users();
        UsersServer us = new UsersServerImpl();
        String flag = "fial";
        if (null != req.getParameter("userName") &&
                null != req.getParameter("userPassword")) {

            users.setUserName(req.getParameter("userName"));
            users.setUserPassword(req.getParameter("userPassword"));
            if (0 != us.userAdd(users)) {
                System.out.println("添加成功");
                flag = "success";
            }
        }
        req.setAttribute("flag", flag);
        req.getRequestDispatcher("/web/User/add.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("执行销毁方法");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("执行init初始化方法");
        super.init();
    }
}
