/**
 * Copyright (C),2015-2018
 * FileNmae: DrugFindNameAction
 * Author:   Administrator
 * Date:     2018/9/39:23
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
@WebServlet("/drugFindNameAction")
public class DrugFindNameAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Drug drug = new Drug();
        String name = req.getParameter("drugName");
        int type = Integer.parseInt(req.getParameter("drugType"));
        DrugServer drugServer = new DrugServerImpl();
        drug.setDrugName(name);
        drug.setDrugType(type);
        List<Drug> list = drugServer.findTypeDrug(drug);
        req.setAttribute("drugs", list);
        req.setAttribute("drugType", type);
        req.getRequestDispatcher("/medicine/index.jsp").forward(req, resp);
    }
}
