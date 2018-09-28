/**
 * Copyright (C),2015-2018
 * FileNmae: DrugExlOut
 * Author:   Administrator
 * Date:     2018/9/28 9:22
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DrugServer;
import com.youma.server.impl.DrugServerImpl;
import com.youma.util.Exl;
import com.youma.vo.Drug;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *DrugExlOut
 *导出药品信息excel
 *@author 陈泽群
 *@date 2018/9/28 9:22
 */
@WebServlet("/drugExlOut")
public class DrugExlOut extends HttpServlet {
    private static final long serialVersionUID = -243449714007136919L;

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
        //全部挂号信息表文件
        String allRegister = "D://DrugAll.xlsx";
        //选中挂号部分信息表文件
        String partRegister = "D://DrugPart.xlsx";
        String str;
        if (all == null) {
            DrugServer drugServer = new DrugServerImpl();
            List<Drug> list = drugServer.findAllDrug();

            Exl exl = new Exl("药品信息表");
            //exl保存路径
            String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
            int i = exl.createExl(Drug.class, allRegister, list);
            PrintWriter out = resp.getWriter();
            if (1 == i) {
                str = "{\"message\":1,\"url\":\"" + allRegister + "\"}";
                out.write(str);
            } else {
                out.write("{message:2}");
            }
            out.flush();
            return;
        } else {
           /* DrugServer drugServer = new DrugServerImpl();
            List<Drug> list = drugServer.findAllDrug();

            Exl exl = new Exl("药品信息表");
            //exl保存路径
            String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
            int i = exl.createExl(Drug.class, allRegister, list);
            PrintWriter out = resp.getWriter();
            if (1 == i) {
                str = "{\"message\":1,\"url\":\"" + allRegister + "\"}";
                out.write(str);
            } else {
                out.write("{message:2}");
            }
            out.flush();
            return;*/
        }
    }
}
