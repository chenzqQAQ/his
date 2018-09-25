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
        Inpatient inpatient = new Inpatient();
        Page page = new Page();
        if ("findAll".equals(action)) {
            if (req.getParameter("medicalNum") != null && !req.getParameter("medicalNum").isEmpty()) {
                inpatient.setMedicalNum(Integer.parseInt(req.getParameter("medicalNum")));
            }
           inpatient.setDoctor(req.getParameter("docName"));
           inpatient.setDepName(req.getParameter("depName"));
            page.setTotalCount(inpServer.inpCount(inpatient));
            if (pageNo != null && !"".equals(pageNo)) {
                page.setPageNo(Integer.parseInt(pageNo));
            } else {
                page.setPageNo(1);
            }
            List<Inpatient> list = inpServer.findInp(inpatient, page);
            req.setAttribute("inps", list);
            // System.out.println("当前页"+page.getPageNo());
            // System.out.println("最后页"+page.getTotalPage());
            req.setAttribute("page", page);
            req.setAttribute("inp", inpatient);
            req.getRequestDispatcher("/hospital/index.jsp").forward(req, resp);
        }
        else if("find".equals(action)){
            int id= Integer.parseInt(req.getParameter("medicalNum"));
            inpatient=inpServer.findInpatient(id);
            req.setAttribute("inp", inpatient);
            req.getRequestDispatcher("/hospital/edit.jsp").forward(req, resp);
        }
        else if("look".equals(action)){
            int id= Integer.parseInt(req.getParameter("medicalNum"));
            inpatient=inpServer.findInpatient(id);
            req.setAttribute("inp", inpatient);
            req.getRequestDispatcher("/hospital/look.jsp").forward(req, resp);
        }
    }
}
