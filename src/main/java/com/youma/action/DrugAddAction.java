/**
 * Copyright (C),2015-2018
 * FileNmae: DrugAddAction
 * Author:   Administrator
 * Date:     2018/8/3017:56
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DrugServer;
import com.youma.server.impl.DrugServerImpl;
import com.youma.util.Upload;
import com.youma.vo.Drug;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 陈泽群
 */
@WebServlet("/drugAddAction")
public class DrugAddAction extends HttpServlet {
    public DrugAddAction() {
    }

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
        String savePath = "D:\\appach";
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        Upload upload = new Upload(savePath, tempPath);
        Map<String, String> map = upload.up(req);
        if (map.get("url") != null) {
            drug.setDrugUrl(map.get("url"));
            System.out.println(map.get("message"));
        } else {
            System.out.println(map.get("message"));
        }
        drug.setDrugID(map.get("drugID"));
        drug.setPurchasePrice(Double.parseDouble(map.get("purchasePrice")));
        drug.setSellingPrice(Double.parseDouble(map.get("sellingPrice")));
        drug.setDrugName(map.get("drugName"));
        drug.setDrugType(Integer.parseInt(map.get("drugType")));
        drug.setDescription(map.get("description"));
        drug.setProductionDate(map.get("productionDate"));
        drug.setOverdueDate(map.get("overdueDate"));
        drug.setQualityLife(Integer.parseInt(map.get("qualityLife")));
        drug.setDetailedDes(map.get("detailedDes"));
        drug.setManufacturer(map.get("manufacturer"));
        drug.setTakingDes(map.get("takingDes"));
        drug.setTotalVolume(Integer.parseInt(map.get("totalVolume")));
        drug.setInventory(Integer.parseInt(map.get("inventory")));
        drug.setRemark(map.get("remark"));
        if (0 != drugServer.drugAdd(drug)) {
            System.out.println("添加成功");
            resp.sendRedirect("/his/drugFindNameAction");
        } else {
            System.out.println("已存在");
        }
    }
}
