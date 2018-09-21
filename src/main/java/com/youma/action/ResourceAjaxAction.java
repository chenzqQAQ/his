/**
 * Copyright (C),2015-2018
 * FileNmae: ResourceAjaxAction
 * Author:   Administrator
 * Date:     2018/9/18 11:19
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.google.gson.Gson;
import com.youma.server.ResourceServer;
import com.youma.server.impl.ResourceServerImpl;
import com.youma.vo.Resources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *ResourceAjaxAction
 *异步请求全部资源
 *@author 陈泽群
 *@date 2018/9/18 11:19
 */
@WebServlet("/resourceAjaxAction")
public class ResourceAjaxAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        ResourceServer resourceServer = new ResourceServerImpl();
        //将资源集合转为json格式,方便js用eval解析
        List<Resources> list = resourceServer.findAllResources();
        Gson gson = new Gson();
        String str = gson.toJson(list);
        // System.out.println("资源信息" + str);
        PrintWriter out = resp.getWriter();
        out.write(str);
    }
}
