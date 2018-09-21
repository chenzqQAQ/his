/**
 * Copyright (C),2015-2018
 * FileNmae: DepartmentAction
 * Author:   Administrator
 * Date:     2018/9/711:26
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.google.gson.Gson;
import com.youma.server.DepServer;
import com.youma.server.impl.DepServerImpl;
import com.youma.vo.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 异步请求数据库里全部的科室信息
 * @author 陈泽群
 */
@WebServlet("/departmentAction")
public class DepartmentAction extends HttpServlet {
    public static String FINDALL = "findAll";
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset:UTF-8");
        System.out.println(req.getQueryString());
        String action = req.getParameter("action");
        DepServer depServer = new DepServerImpl();
        /**
         * Json格式转换
         */
        if (FINDALL.equals(action)) {
            System.out.println("开始科室全查");
            //全查科室信息
            List<Department> list = depServer.findAllDepartment();
            //将科室信息转为json格式,方便页面js解析
            String json = gson.toJson(list);
            PrintWriter out = resp.getWriter();
            out.write(json);
            out.flush();
        }
    }
}
