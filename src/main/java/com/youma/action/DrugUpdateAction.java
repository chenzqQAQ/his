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
 * 药品信息更细
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
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        DrugServer drugServer = new DrugServerImpl();
        Drug drug = null;
        //文件的保存路径,与设置的虚拟路径对应
        String savePath = "D:\\appach";
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        Upload upload = new Upload(savePath, tempPath);
        Map<String, String> map = upload.up(req);
        //获取药品id对应的药品信息
        //因为页面的file的value属性是只读,当用户没有选择新图时,值为空,需要获取药品图片的原来地址
        drug=drugServer.findDrug(map.get("drugID"));
        if (map.get("url") != null) {
            //当修改页面选择了新的图片,设置新的图片地址
            drug.setDrugUrl(map.get("url"));
            System.out.println("图片地址"+map.get("url"));
            System.out.println(map.get("message"));
        } else {
            //药品图片地址保留为原来地址
            System.out.println(map.get("message"));
        }
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
        if (0 != drugServer.updateDrug(drug)) {
            System.out.println("更新成功");
            resp.sendRedirect("/his/drugFindNameAction");
        } else {
            System.out.println("未修改");
        }
    }
}
