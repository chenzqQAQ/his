/**
 * Copyright (C),2015-2018
 * FileNmae: DispenedFindAction
 * Author:   Administrator
 * Date:     2018/9/17 11:22
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DisServer;
import com.youma.server.impl.DisServerImpl;
import com.youma.util.Page;
import com.youma.vo.DispensedDrug;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * DispenedFindAction
 * 查询分发药品信息
 *
 * @author 陈泽群
 * @date 2018/9/17 11:22
 */
@WebServlet("/dispenedFindAction")
public class DispenedFindAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        DisServer disServer = new DisServerImpl();
        Page page = new Page();
        page.setTotalCount(disServer.disCount());
        String pageNo = req.getParameter("pageNo");
        if (!"".equals(pageNo) && pageNo != null) {
            page.setPageNo(Integer.parseInt(pageNo));
        } else {
            page.setPageNo(1);
        }
        List<DispensedDrug> list = disServer.findAllDispensedDrug(page);
        System.out.println("总条数"+page.getTotalCount());
        System.out.println("集合个数"+list.size());
        req.setAttribute("dis", list);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/hospital/dispensing.jsp").forward(req, resp);
    }
}
