/**
 * Copyright (C),2015-2018
 * FileNmae: ResAjaxAction
 * Author:   Administrator
 * Date:     2018/9/18 14:20
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.RoleServer;
import com.youma.server.impl.RoleServerImpl;
import com.youma.vo.Resources;
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
 * ResAjaxAction
 * 查看角色id对应的权限
 *
 * @author 陈泽群
 * @date 2018/9/18 14:20
 */
@WebServlet("/resAjaxAction")
public class ResAjaxAction extends HttpServlet {
    private static final long serialVersionUID = 9179137250283925115L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        RoleServer roleServer = new RoleServerImpl();
        int id = Integer.parseInt(req.getParameter("roleId"));
        Role role = roleServer.findRes(id);
        List<Resources> list = role.getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            Resources resources = list.get(i);
            String str = String.format("{id:\'%d\',text:\'%s\',href:\'%s\'}", resources.getResID(), resources.getResName(), resources.getResUrl());
            if (i != 0) {
                sb.append(",");
            }
            sb.append(str);
        }
        sb.append("]");
        String r = sb.toString();
        // System.out.println(r);
        PrintWriter out=resp.getWriter();
        out.write(r);
    }
}
