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
 * 药品信息添加(有文件)
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
        //文件上传地址
        String savePath = "D:\\appach";
        //文件缓存地址
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        //实例文件上传下载类
        Upload upload = new Upload(savePath, tempPath);
        //因为有文件,不能使用一般的参数获取getParameter()方法
        //使用上传下载类,将存在的文件上传到地址中,非文件参数以map类型返回
        Map<String, String> map = upload.up(req);
        if (map.get("url") != null) {
            //文件上传成功了,获取文件的地址(目录加文件名)
            drug.setDrugUrl(map.get("url"));
            System.out.println(map.get("message"));
        } else {
            //文件没有或者上传失败,药品中的地址为空字符串
            System.out.println(map.get("message"));
        }
        //将其他非文件参数通过map获取
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
        //添加药品信息到数据库
        if (0 != drugServer.drugAdd(drug)) {
            System.out.println("添加成功");
            resp.sendRedirect("/his/drugFindNameAction");
        } else {
            System.out.println("已存在");
        }
    }
}
