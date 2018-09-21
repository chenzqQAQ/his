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
        //获取用户名和密码
        String userName = req.getParameter("username");
        String passWord = req.getParameter("password");
        users.setUserName(userName);
        users.setUserPassword(passWord);
        //检查用户名和密码是否正确
        Users user=usersServer.findUsers(users);
        if(user==null)
        {
            //数据库里没有用户，或者用户名密码错误
            System.out.println("用户名密码不正确");
            //返回信息
            req.setAttribute("message","用户名密码不正确");
            //登录失败回到登录页面
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }
        //获取登录页面上验证码图片对应的验证码信息
        String sessionCode = String.valueOf(req.getSession().getAttribute("code"));
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
            //传来的验证码不为空，会话中有验证码信息
            if (code.equalsIgnoreCase(sessionCode)) {
                //忽略大小写,验证码正确
                System.out.println("验证通过! ");
                //传用户名
                req.getSession().setAttribute("realName",user.getRealName());
                //传角色id,以便主页面根据角色分配资源(角色所能查看的页面)
                req.getSession().setAttribute("role",user.getRoleID());
                resp.sendRedirect("/his/index.jsp");
            } else {
                //验证码不对
                System.out.println("验证失败!");
                req.setAttribute("message","验证失败!");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } else {
            //验证码为空
            System.out.println("验证失败! ");
            req.setAttribute("message","验证失败!");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
