/**
 * Copyright (C),2015-2018
 * FileNmae: DrugFindAction
 * Author:   Administrator
 * Date:     2018/8/3020:22
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
 * 医生单查
 * @author 陈泽群
 */
@WebServlet("/drugFindAction")
public class DrugFindAction extends HttpServlet {
    private static final long serialVersionUID = 3052487058278888970L;

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
        String id = req.getParameter("drugid");
        Drug drug = drugServer.findDrug(id);
        String str=drug.getDrugUrl();
        //去掉磁盘
        //文件地址在数据库存的是绝对路径,例如D:\appach\13\14\84fbe6b1-6db0-4241-82a4-a98924355287_头孢克洛.jpg
        if(str!=null)
        {
            //需要将地址转为配置好的虚拟路径
            // /appach对应D:\appach
            int i=str.lastIndexOf("appach");
            drug.setDrugUrl(str.substring(i-1));
        }
        req.setAttribute("drug", drug);
        req.getRequestDispatcher("/medicine/edit.jsp").forward(req, resp);
    }
}
