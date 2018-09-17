/**
 * Copyright (C),2015-2018
 * FileNmae: DispenedAddAction
 * Author:   Administrator
 * Date:     2018/9/17 10:14
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
 * DispenedAddAction
 * 药品分发添加
 *
 * @author 陈泽群
 * @date 2018/9/17 10:14
 */
@WebServlet("/dispenedAddAction")
public class DispenedAddAction extends HttpServlet {
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
        String drugId = req.getParameter("drugId");
        System.out.println("药品id"+drugId);
        int TotalQuantity = Integer.parseInt(req.getParameter("TotalQuantity"));
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrug.setDrugId(drugId);
        dispensedDrug.setTotalQuantity(TotalQuantity);
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
        DisServer disServer = new DisServerImpl();
        if (a.length == disServer.dispensedDrugAdd(a, dispensedDrug)) {
            System.out.println("添加成功");
            resp.sendRedirect("/his/dispenedFindAction");
        }
    }
}
