/**
 * Copyright (C),2015-2018
 * FileNmae: DisUpdateAction
 * Author:   Administrator
 * Date:     2018/9/18 9:23
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DisServer;
import com.youma.server.impl.DisServerImpl;
import com.youma.vo.DispensedDrug;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DisUpdateAction
 * 发药
 *
 * @author 陈泽群
 * @date 2018/9/18 9:23
 */
@WebServlet("/disUpdateAction")
public class DisUpdateAction extends HttpServlet {

    private static final long serialVersionUID = 2505027884252309149L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset:UTF-8");
        int medicalNum = Integer.parseInt(req.getParameter("medicalNum"));
        String drugID = req.getParameter("drugID");
        int dispensedQuantity = Integer.parseInt(req.getParameter("dispensedQuantity"));
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrug.setMedicalNum(medicalNum);
        dispensedDrug.setDrugId(drugID);
        dispensedDrug.setDispensedQuantity(dispensedQuantity);
        DisServer disServer=new DisServerImpl();
        if(0!=disServer.disDrug(dispensedDrug))
        {
            System.out.println("发药成功");
            resp.sendRedirect("/his/dispenedFindAction?action=find&medicalNum="+medicalNum);
        }

    }
}
