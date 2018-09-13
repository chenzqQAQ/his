/**
 * Copyright (C),2015-2018
 * FileNmae: InpFindAction
 * Author:   Administrator
 * Date:     2018/9/139:52
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.InpServer;
import com.youma.server.impl.InpServerImpl;
import com.youma.util.Page;
import com.youma.vo.Inpatient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 住院信息查询action
 *
 * @author 陈泽群
 */
@WebServlet("/inpFindAction")
public class InpFindAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        InpServer inpServer = new InpServerImpl();
        String action = req.getParameter("action");
        String pageNo = req.getParameter("pageNo");
        Page page = new Page();
        if ("findAll".equals(action)) {
            page.setTotalCount(inpServer.inpCount());
            if (pageNo != null && !"".equals(pageNo)) {
                page.setPageNo(Integer.parseInt(pageNo));
            } else {
                page.setPageNo(1);
            }
            List<Inpatient> list = inpServer.findInp(page);
            req.setAttribute("inps", list);
            System.out.println("当前页"+page.getPageNo());
            System.out.println("最后页"+page.getTotalPage());
            req.setAttribute("page", page);
            req.getRequestDispatcher("/hospital/index.jsp").forward(req, resp);
        }
    }
}
