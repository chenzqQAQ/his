/**
 * Copyright (C),2015-2018
 * FileNmae: DrugAjaxAction
 * Author:   Administrator
 * Date:     2018/9/30 10:57
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.google.gson.Gson;
import com.youma.server.DrugServer;
import com.youma.server.impl.DrugServerImpl;
import com.youma.vo.Drug;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *DrugAjaxAction
 *根据输入药品名称模糊查询符合条件的
 *@author 陈泽群
 *@date 2018/9/30 10:57
 */
@WebServlet("/drugAjaxAction")
public class DrugAjaxAction extends HttpServlet {
    private static final long serialVersionUID = 8387971693993316027L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DrugServer drugServer = new DrugServerImpl();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name=req.getParameter("name");
        List<Drug> list=drugServer.findNameDrug(name);
        Gson gson=new Gson();
        String str=gson.toJson(list);
        resp.getWriter().write(str);
        return;
    }
}
