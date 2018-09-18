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
        String pageNo = req.getParameter("pageNo");
        Page page = new Page();
        page.setTotalCount(roleServer.roleCount());
        if (pageNo != null && !"".equals(pageNo)) {
            page.setPageNo(Integer.parseInt(pageNo));
        } else {
            page.setPageNo(1);
        }
        List<Role> list = roleServer.findAllRole(page);
        req.setAttribute("page", page);
        req.setAttribute("roles", list);
        req.getRequestDispatcher("/Role/index.jsp").forward(req, resp);
    }
}
