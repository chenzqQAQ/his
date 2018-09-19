/**
 * Copyright (C),2015-2018
 * FileNmae: RoleDelAction
 * Author:   Administrator
 * Date:     2018/9/18 17:10
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.RoleServer;
import com.youma.server.impl.RoleServerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RoleDelAction
 * 角色删除
 *
 * @author 陈泽群
 * @date 2018/9/18 17:10
 */
@WebServlet("/roleDelAction")
public class RoleDelAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("roleId"));
        RoleServer roleServer = new RoleServerImpl();
        //先删除关联表中的全部资源
        roleServer.delRes(id);
        //再删除角色表中的角色
        roleServer.delRole(id);
        resp.sendRedirect("/his/roleFindAction");
    }
}
