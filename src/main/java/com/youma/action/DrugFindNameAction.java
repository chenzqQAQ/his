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
import com.youma.util.Page;
import com.youma.vo.Drug;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 药品信息全查
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
        int type;
        if (req.getParameter("drugType") == null || "".equals(req.getParameter("drugType"))) {
            //没选择药品类型
            type = 99;
        } else {
            //获取选择的药品类型
            type = Integer.parseInt(req.getParameter("drugType"));

        }
        DrugServer drugServer = new DrugServerImpl();
        drug.setDrugName(name);
        drug.setDrugType(type);
        Page page = new Page();
        //查询符合条件的条数
        page.setTotalCount(drugServer.drugCount(drug));
        //设置当且页面,默认为首页
        if (req.getParameter("pageNo") != null && !"".equals(req.getParameter("pageNo"))) {
            page.setPageNo(Integer.parseInt(req.getParameter("pageNo")));
        } else {
            page.setPageNo(1);
        }
        //查询符合条件的药品信息
        List<Drug> list = drugServer.allDrug(drug, page);
        req.setAttribute("drugs", list);
        req.setAttribute("drug", drug);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/medicine/index.jsp").forward(req, resp);
    }
}
