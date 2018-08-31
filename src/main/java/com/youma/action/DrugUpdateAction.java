/**
 * Copyright (C),2015-2018
 * FileNmae: DrugUpdateAction
 * Author:   Administrator
 * Date:     2018/8/3111:53
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
@WebServlet("/drugUpdateAction")
public class DrugUpdateAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DrugServer drugServer = new DrugServerImpl();
        Drug drug = new Drug();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        drug.setDrugID(req.getParameter("drugID"));
        drug.setDrugUrl(req.getParameter("drugUrl"));
        drug.setPurchasePrice(Double.parseDouble(req.getParameter("purchasePrice")));
        drug.setSellingPrice(Double.parseDouble(req.getParameter("sellingPrice")));
        drug.setDrugName(req.getParameter("drugName"));
        drug.setDrugType(Integer.parseInt(req.getParameter("drugType")));
        drug.setDescription(req.getParameter("description"));
        drug.setProductionDate(req.getParameter("productionDate"));
        drug.setOverdueDate(req.getParameter("overdueDate"));
        drug.setQualityLife(Integer.parseInt(req.getParameter("qualityLife")));
        drug.setDetailedDes(req.getParameter("detailedDes"));
        drug.setManufacturer(req.getParameter("manufacturer"));
        drug.setTakingDes(req.getParameter("takingDes"));
        drug.setTotalVolume(Integer.parseInt(req.getParameter("totalVolume")));
        drug.setInventory(Integer.parseInt(req.getParameter("inventory")));
        drug.setRemark(req.getParameter("remark"));
        System.out.println(drug.getDrugID());
        if (0 != drugServer.updateDrug(drug)) {
            System.out.println("更新成功");
            resp.sendRedirect("/his/drugFindAllAction");
        } else {
            System.out.println("未修改");
        }
    }
}
