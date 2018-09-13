/**
 * Copyright (C),2015-2018
 * FileNmae: ExlOut
 * Author:   Administrator
 * Date:     2018/9/1215:05
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.RegisterServer;
import com.youma.server.impl.RegisterServerImpl;
import com.youma.util.Exl;
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
 * 将挂号信息导入到exl文件中
 *
 * @author 陈泽群
 */
@WebServlet("/exlOut")
public class ExlOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        String all = req.getParameter("all");
        if (all == null) {
            RegisterServer registerServer = new RegisterServerImpl();
            List<Register> list = registerServer.findAllRegister();
            Exl exl = new Exl("挂号信息表");
            PrintWriter out = resp.getWriter();
            if (1 == exl.createExl(Register.class, "D:/registerAll.xlsx", list)) {

                out.write("1");
            } else {
                out.write("2");
            }
            out.flush();
            return;
        } else {
            String[] id = req.getParameterValues("medicalNum");
            int[] ids = new int[id.length];
            for (int i = 0; i < id.length; i++) {
                ids[i] = Integer.parseInt(id[i]);
            }
            List<Register> list = new RegisterServerImpl().findAllRegister(ids);
            Exl exl = new Exl("部分挂号信息表");
            PrintWriter out = resp.getWriter();
            if (1 == exl.createExl(Register.class, "D:/registerPart.xlsx", list)) {
                out.write("1");
            } else {
                out.write("2");
            }
            out.flush();
            return;
        }
    }
}
