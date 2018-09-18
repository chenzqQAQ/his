/**
 * Copyright (C),2015-2018
 * FileNmae: RoleAjaxAction
 * Author:   Administrator
 * Date:     2018/9/18 11:01
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.google.gson.Gson;
import com.youma.server.RoleServer;
import com.youma.server.impl.RoleServerImpl;
import com.youma.vo.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * RoleAjaxAction
 * 异步请求全部角色
 *
 * @author 陈泽群
 * @date 2018/9/18 11:01
 */
@WebServlet("/roleAjaxAction")
public class RoleAjaxAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        RoleServer roleServer = new RoleServerImpl();
        List<Role> list = roleServer.findAllRole();
        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println("角色信息" + str);
        PrintWriter out = resp.getWriter();
        out.write(str);
    }
}
