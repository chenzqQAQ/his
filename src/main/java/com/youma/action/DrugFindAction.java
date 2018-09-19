/**
 * Copyright (C),2015-2018
 * FileNmae: DrugFindAction
 * Author:   Administrator
 * Date:     2018/8/3020:22
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

/**
 * @author 陈泽群
 */
@WebServlet("/drugFindAction")
public class DrugFindAction extends HttpServlet {
    private static final long serialVersionUID = 3052487058278888970L;

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
        String id = req.getParameter("drugid");
        Drug drug = drugServer.findDrug(id);
        // System.out.println("全查" + list.size());
        String str=drug.getDrugUrl();
        //去掉磁盘
        if(str!=null)
        {
            int i=str.lastIndexOf("appach");
            drug.setDrugUrl(str.substring(i-1));
        }
        req.setAttribute("drug", drug);
        req.getRequestDispatcher("/medicine/edit.jsp").forward(req, resp);
    }
}
