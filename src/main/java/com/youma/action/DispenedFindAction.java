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
        String action = req.getParameter("action");
        if (!"".equals(action) && action != null) {
            //单查分发药品信息,获取病例号
            int medicalNum = Integer.parseInt(req.getParameter("medicalNum"));
            //获取该病例号全部的发药信息
            List<DispensedDrug> list = disServer.findDispensedDrug(medicalNum);
            req.setAttribute("drugs", list);
            //请求转发到单查页面
            req.getRequestDispatcher("/hospital/dispensing-look.jsp").forward(req, resp);
            return;
        } else {
            //全查分发药品信息
            Page page = new Page();
            //设置分页类的总条数
            page.setTotalCount(disServer.disCount());
            String pageNo = req.getParameter("pageNo");
            if (!"".equals(pageNo) && pageNo != null) {
                //有页码,设置页码
                page.setPageNo(Integer.parseInt(pageNo));
            } else {
                //没有页面,默认首页
                page.setPageNo(1);
            }
            //获取当前页的全部信息
            List<DispensedDrug> list = disServer.findAllDispensedDrug(page);
            req.setAttribute("dis", list);
            req.setAttribute("page", page);
            req.getRequestDispatcher("/hospital/dispensing.jsp").forward(req, resp);
        }
    }
}
