/**
 * Copyright (C),2015-2018
 * FileNmae: RoleFindAction
 * Author:   Administrator
 * Date:     2018/9/18 15:33
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.RoleServer;
import com.youma.server.impl.RoleServerImpl;
import com.youma.util.Page;
import com.youma.vo.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * RoleFindAction
 * 查询角色信息
 *
 * @author 陈泽群
 * @date 2018/9/18 15:33
 */
@WebServlet("/roleFindAction")
public class RoleFindAction extends HttpServlet {
    private static final long serialVersionUID = -5193234007717681723L;

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
        String action = req.getParameter("action");
        if (action != null && !"".equals(action)) {
            //单查
            int id = Integer.parseInt(req.getParameter("roleId"));
            Role role = roleServer.findRole(id);
            Role role1 = roleServer.findRes(id);
            role.setResources(role1.getResources());
            req.setAttribute("role", role);
            req.getRequestDispatcher("/Role/editRole.jsp").forward(req, resp);
            return;
        }
        String pageNo = req.getParameter("pageNo");
        Role role=new Role();
        role.setRoleName(req.getParameter("roleName"));
        Page page = new Page();
        page.setTotalCount(roleServer.roleCount(role));
        if (pageNo != null && !pageNo.isEmpty()) {
            page.setPageNo(Integer.parseInt(pageNo));
        } else {
            page.setPageNo(1);
        }
        List<Role> list = roleServer.findAllRole(role,page);
        req.setAttribute("page", page);
        req.setAttribute("roles", list);
        req.setAttribute("role", role);
        req.getRequestDispatcher("/Role/index.jsp").forward(req, resp);
    }
}
