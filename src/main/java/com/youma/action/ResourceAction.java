/**
 * Copyright (C),2015-2018
 * FileNmae: ResourceAction
 * Author:   Administrator
 * Date:     2018/9/1111:09
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.ResourceServer;
import com.youma.server.impl.ResourceServerImpl;
import com.youma.util.Page;
import com.youma.vo.Resources;

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
@WebServlet("/resourceAction")
public class ResourceAction extends HttpServlet {

    private static final long serialVersionUID = -4986845458925922946L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        ResourceServer resourceSever = new ResourceServerImpl();
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String resName = req.getParameter("resName");
            String resUrl = req.getParameter("resUrl");
            int status = Integer.parseInt(req.getParameter("status"));
            Resources resources = new Resources();
            resources.setResName(resName);
            resources.setResUrl(resUrl);
            resources.setStatus(status);
            if (0 != resourceSever.resourcesAdd(resources)) {
                resp.sendRedirect("/his/resourceAction?action=findAll");
            }
        } else if ("findAll".equals(action)) {
            Page page = new Page();
            page.setTotalCount(resourceSever.allResourcesCount());
            if (null != req.getParameter("pageNo")) {
                page.setPageNo(Integer.parseInt(req.getParameter("pageNo")));
            } else {
                page.setPageNo(1);
            }
            List<Resources> list = resourceSever.findAllResources(page);
            req.setAttribute("resources", list);
            req.setAttribute("page", page);
            req.getRequestDispatcher("/Resource/index.jsp").forward(req, resp);
        } else if ("find".equals(action)) {
            int id = Integer.parseInt(req.getParameter("resID"));
            Resources resources = resourceSever.findResources(id);
            req.setAttribute("resources", resources);
            req.getRequestDispatcher("/Resource/edit.jsp").forward(req, resp);
        } else if ("update".equals(action)) {
            int resID = Integer.parseInt(req.getParameter("resID"));
            String resName = req.getParameter("resName");
            String resUrl = req.getParameter("resUrl");
            int status = Integer.parseInt(req.getParameter("status"));
            Resources resources = new Resources();
            resources.setResID(resID);
            resources.setResName(resName);
            resources.setResUrl(resUrl);
            resources.setStatus(status);
            if (0 != resourceSever.uepdateResources(resources)) {
                resp.sendRedirect("/his/resourceAction?action=findAll");
            }
        }
        else if ("del".equals(action)) {
            int resID = Integer.parseInt(req.getParameter("resID"));
            if (0 != resourceSever.delResources(resID)) {
                resp.sendRedirect("/his/resourceAction?action=findAll");
            }
        }
    }
}