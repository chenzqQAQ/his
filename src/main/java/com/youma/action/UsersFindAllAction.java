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
import com.youma.util.Page;
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
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        UsersServer usersServer = new UsersServerImpl();
        Page page = new Page();
        Users user=new Users();
        user.setUserName(req.getParameter("username"));
        int col = usersServer.allUsersCount(user);
        page.setPageSize(5);
        page.setTotalCount(col);
        int pageNo = 1;
        if (req.getParameter("pageNo") != null&&!req.getParameter("pageNo").isEmpty()) {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        page.setPageNo(pageNo);
        List<Users> list = usersServer.findAllUsers(user,page);
        System.out.println("全查" + list.size());
        System.out.println("总页数"+page.getTotalPage());
        System.out.println("总条数"+page.getTotalCount());
        System.out.println("当前页"+page.getPageNo());
        System.out.println("单页条数"+page.getPageSize());
        System.out.println("偏移量"+page.getOffset());
        req.setAttribute("users", list);
        req.setAttribute("page", page);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/User/index.jsp").forward(req, resp);
    }
}
