/**
 * Copyright (C),2015-2018
 * FileNmae: RoleAddAction
 * Author:   Administrator
 * Date:     2018/9/18 11:59
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
import java.util.ArrayList;
import java.util.List;

/**
 * RoleAddAction
 * 添加角色，分配资源
 *
 * @author 陈泽群
 * @date 2018/9/18 11:59
 */
@WebServlet("/roleAddAction")
public class RoleAddAction extends HttpServlet {
    private static final long serialVersionUID = -1155015610380926306L;

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
        //获取权限信息
        String[] resources = req.getParameterValues("resources");
        //获取角色名称
        String roleName = req.getParameter("roleName");
        int status = Integer.parseInt(req.getParameter("status"));
        List<Resources> list = new ArrayList<>();
        Role role = new Role();
        //将权限的id存储到角色类中
        System.out.println("resources"+resources);
        if(resources!=null)
        {
            for (String str : resources) {
                Resources resources1 = new Resources();
                resources1.setResID(Integer.parseInt(str));
                list.add(resources1);
                // System.out.println(resources1.getResID());
            }
            role.setResources(list);
        }
        else{
            role.setResources(null);
        }
        role.setRoleName(roleName);
        role.setStatus(status);
        String action = req.getParameter("action");
        if (action != null && !"".equals(action)) {
            //修改权限
            //获取角色id
            int id = Integer.parseInt(req.getParameter("roleId"));
            role.setRoleID(id);
            roleServer.updateRole(role);
            //先删除原有权限资源,再重新添加
            roleServer.delRes(role.getRoleID());
            if(resources!=null)
            {
                if (resources.length == roleServer.addRes(role)) {
                    System.out.println("权限重新分配成功");
                    req.getSession().setAttribute("newid",role.getRoleID());
                } else {
                    System.out.println("权限重新分配失败");
                }
                resp.sendRedirect("/his/roleFindAction");
            }
            return;
        }
        if (roleServer.roleAdd(role) != 0) {
            System.out.println("角色添加成功");
            role.setRoleID(roleServer.findRole(roleName));
            if (resources==null||resources.length == roleServer.addRes(role)) {
                System.out.println("权限分配成功");
                resp.sendRedirect("/his/roleFindAction");
            }
        } else {
            System.out.println("角色添加失败");
            resp.sendRedirect("/his/roleFindAction");
        }

    }
}
