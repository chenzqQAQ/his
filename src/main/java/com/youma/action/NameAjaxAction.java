/**
 * Copyright (C),2015-2018
 * FileNmae: NameAjaxAction
 * Author:   Administrator
 * Date:     2018/9/17 10:15
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.google.gson.Gson;
import com.youma.server.RegisterServer;
import com.youma.server.impl.RegisterServerImpl;
import com.youma.vo.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * NameAjaxAction
 * 异步获取病例号对应的病患名
 * @author 陈泽群
 * @date 2018/9/17 10:15
 */
@WebServlet("/nameAjaxAction")
public class NameAjaxAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        String medicalNum = req.getParameter("medicalNum");
        RegisterServer registerServer = new RegisterServerImpl();
        //获取逗号分隔的全部病历号
        String[] strs = medicalNum.split(",");
        int[] a = new int[strs.length];
        String k;
        for (int i = 0; i < strs.length; i++) {
            k = strs[i];
            if (k != null && !"".equals(k)) {
                a[i] = Integer.parseInt(k);
            } else {
                a[i] = 0;
            }
        }
        //用sql 中的in来查询符合对应病历号的全部挂号信息
        List<Register> registers = registerServer.findAllRegister(a);
        Gson gson = new Gson();
        String s = gson.toJson(registers);
        System.out.println(s);
        PrintWriter out = resp.getWriter();
        out.write(s);
        out.flush();
    }
}
