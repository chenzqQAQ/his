/**
 * Copyright (C),2015-2018
 * FileNmae: AjaxAction1
 * Author:   Administrator
 * Date:     2018/9/1211:00
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DoctorServer;
import com.youma.server.impl.DoctorServerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *@author 陈泽群
 */
@WebServlet("/ajaxAction1")
public class AjaxAction1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        System.out.println(req.getQueryString());
        // System.out.println("进入ajax");
        String action = req.getParameter("action");
        String docName = req.getParameter("docName");
        // System.out.println("用户名为" + userName);
        DoctorServer doctorServer=new DoctorServerImpl();
        PrintWriter printWriter = resp.getWriter();
        if (null == docName || "".equals(docName)) {
            printWriter.write("2");
        } else if (0 != doctorServer.findDocName(docName)) {
            printWriter.write("1");
        } else {
            printWriter.write("0");
        }
        printWriter.flush();
    }
}