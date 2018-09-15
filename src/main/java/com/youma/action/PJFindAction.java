/**
 * Copyright (C),2015-2018
 * FileNmae: PJFindAction
 * Author:   Administrator
 * Date:     2018/9/14 11:44
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.PayMgServer;
import com.youma.server.impl.PayMgServerImpl;
import com.youma.util.Page;
import com.youma.vo.PayManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * PJFindAction
 * TODO(描述类的作用)
 *
 * @author 陈泽群
 * @date 2018/9/14 11:44
 */
@WebServlet("/pJFindAction")
public class PJFindAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        PayMgServer payMgServer = new PayMgServerImpl();
        String pageNo = req.getParameter("pageNo");
        PayManager payManager = new PayManager();
        if (req.getParameter("medicalNum") != null && !"".equals(req.getParameter("medicalNum"))) {
            payManager.setMedicalNum(Integer.parseInt(req.getParameter("medicalNum")));
        } else {
            payManager.setMedicalNum(0);
        }
        payManager.setName(req.getParameter("name"));
        int col = payMgServer.allCount(payManager);
        Page page = new Page();
        page.setTotalCount(col);
        if (pageNo != null && !"".equals(pageNo)) {
            page.setPageNo(Integer.parseInt(pageNo));
        } else {
            page.setPageNo(1);
        }
        List<PayManager> list = payMgServer.findAll(payManager, page);
        req.setAttribute("pjs", list);
        req.setAttribute("payManager", payManager);
        req.setAttribute("page", page);
        // System.out.println("偏移量" + page.getOffset());
        // System.out.println("单页" + page.getPageSize());
        req.getRequestDispatcher("/hospital/charge.jsp").forward(req, resp);
    }
}
