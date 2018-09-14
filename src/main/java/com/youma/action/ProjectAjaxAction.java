/**
 * Copyright (C),2015-2018
 * FileNmae: ProjectAjaxAction
 * Author:   Administrator
 * Date:     2018/9/14 9:27
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.google.gson.Gson;
import com.youma.server.PayPjServer;
import com.youma.server.impl.PayPjServerImpl;
import com.youma.vo.PayProject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * ProjectAjaxAction
 * 异步返回获取的全部收费项目
 *
 * @author 陈泽群
 * @date 2018/9/14 9:27
 */
@WebServlet("/projectAjaxAction")
public class ProjectAjaxAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        Gson gson=new Gson();
        PayPjServer payPjServer=new PayPjServerImpl();
        System.out.println("开始收费项目全查");
        List<PayProject> list = payPjServer.findAllPayProject();
        String json = gson.toJson(list);
        System.out.println("收费项目的JSON"+json);
        PrintWriter out = resp.getWriter();
        out.write(json);
        out.flush();

    }
}
