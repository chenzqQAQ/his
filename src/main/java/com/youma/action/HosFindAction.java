/**
 * Copyright (C),2015-2018
 * FileNmae: HosFindAction
 * Author:   Administrator
 * Date:     2018/9/19 10:14
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DisServer;
import com.youma.server.HosServer;
import com.youma.server.PayMgServer;
import com.youma.server.impl.DisServerImpl;
import com.youma.server.impl.HosServerImpl;
import com.youma.server.impl.PayMgServerImpl;
import com.youma.util.Page;
import com.youma.vo.DispensedDrug;
import com.youma.vo.HosSettle;
import com.youma.vo.PayManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * HosFindAction
 * 全查住院结算信息
 *
 * @author 陈泽群
 * @date 2018/9/19 10:14
 */
@WebServlet("/hosFindAction")
public class HosFindAction extends HttpServlet {
    private static final long serialVersionUID = -5396920432011176787L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset:UTF-8");
        HosServer hosServer = new HosServerImpl();
        String action = req.getParameter("action");
        if (!"".equals(action) && action != null) {
            int medicalNum = Integer.parseInt(req.getParameter("medicalNum"));
            //计算全部花费
            hosServer.updateCost(medicalNum);
            //查询收费项目
            PayMgServer payMgServer = new PayMgServerImpl();
            //查询药品信息
            DisServer disServer = new DisServerImpl();
            HosSettle hosSettle=hosServer.findHosSettle(medicalNum);
            List<PayManager> list = payMgServer.findAll(medicalNum);
            List<DispensedDrug> list1 = disServer.findDispensedDrug(medicalNum);
            req.setAttribute("medicalNum", medicalNum);
            req.setAttribute("pays", list);
            req.setAttribute("drugs", list1);
            req.setAttribute("hosSettle", hosSettle);
            req.getRequestDispatcher("/hospital/account-look.jsp").forward(req, resp);
            return;
        }
        HosSettle hosSettle = new HosSettle();
        if(req.getParameter("medicalNum")!=null&&!req.getParameter("medicalNum").isEmpty())
        {
            hosSettle.setMedicalNum(Integer.parseInt(req.getParameter("medicalNum")));
        }
        Page page = new Page();
        page.setTotalCount(hosServer.allCount(hosSettle));
        if(req.getParameter("pageNo")!=null&&!req.getParameter("pageNo").isEmpty())
        {
            page.setPageNo(Integer.parseInt(req.getParameter("pageNo")));
        }
        else{
            page.setPageNo(1);
        }
        List<HosSettle> list = hosServer.findAll(hosSettle,page);
        for(int i=0;i<list.size();i++)
        {
            hosServer.updateCost(list.get(i).getMedicalNum());
        }
        req.setAttribute("hos", list);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/hospital/account.jsp").forward(req, resp);
    }

}
