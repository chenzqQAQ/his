/**
 * Copyright (C),2015-2018
 * FileNmae: CheckCode
 * Author:   Administrator
 * Date:     2018/9/17 16:24
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
 * CheckCode
 * 校验验证码
 *
 * @author 陈泽群
 * @date 2018/9/17 16:24
 */
@WebServlet("/checkCode")
public class CheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String code = req.getParameter("code");
        UsersServer usersServer=new UsersServerImpl();
        Users users = new Users();
        String userName = req.getParameter("username");
        String passWord = req.getParameter("password");
        users.setUserName(userName);
        users.setUserPassword(passWord);
        Users user=usersServer.findUsers(users);
        if(user==null)
        {
            System.out.println("用户名密码不正确");
            req.setAttribute("message","用户名密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }
        String sessionCode = String.valueOf(req.getSession().getAttribute("code"));
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
            if (code.equalsIgnoreCase(sessionCode)) {
                System.out.println("验证通过! ");
                req.getSession().setAttribute("realName",user.getRealName());
                req.getSession().setAttribute("role",user.getRoleID());
                resp.sendRedirect("/his/index.jsp");
            } else {
                System.out.println("验证失败!");
                req.setAttribute("message","验证失败!");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } else {
            System.out.println("验证失败! ");
            req.setAttribute("message","验证失败!");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
