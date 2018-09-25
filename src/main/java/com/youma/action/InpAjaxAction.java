/**
 * Copyright (C),2015-2018
 * FileNmae: InpAjaxAction
 * Author:   Administrator
 * Date:     2018/9/25 19:13
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.InpServer;
import com.youma.server.impl.InpServerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * InpAjaxAction
 * 请求出院
 *
 * @author 陈泽群
 * @date 2018/9/25 19:13
 */
@WebServlet("/inpAjaxAction")
public class InpAjaxAction extends HttpServlet {
    private static final long serialVersionUID = 2771333204184456040L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int medicalNum = Integer.parseInt(req.getParameter("medicalNum"));
        InpServer inpServer = new InpServerImpl();
        PrintWriter out = resp.getWriter();
        if (0 != inpServer.delInpatient(medicalNum)) {
            // System.out.println("出院成功");
            out.write("1");
        } else {
            // System.out.println("未结账,没法出院");
            out.write("2");
        }
    }
}
