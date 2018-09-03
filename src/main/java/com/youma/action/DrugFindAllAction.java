/**
 * Copyright (C),2015-2018
 * FileNmae: DrugFindAllAction
 * Author:   Administrator
 * Date:     2018/8/3017:22
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DrugServer;
import com.youma.server.impl.DrugServerImpl;
import com.youma.vo.Drug;

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
@WebServlet("/drugFindAllAction")
public class DrugFindAllAction extends HttpServlet {
    public DrugFindAllAction() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        DrugServer drugServer = new DrugServerImpl();
        List<Drug> list = drugServer.findAllDrug();
        req.setAttribute("drugs", list);
        req.getRequestDispatcher("/medicine/index.jsp").forward(req, resp);
    }
}
