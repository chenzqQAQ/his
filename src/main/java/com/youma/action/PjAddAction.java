/**
 * Copyright (C),2015-2018
 * FileNmae: PjAddAction
 * Author:   Administrator
 * Date:     2018/9/14 10:36
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.PayMgServer;
import com.youma.server.impl.PayMgServerImpl;
import com.youma.vo.PayManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PjAddAction
 * 收费项目添加
 *
 * @author 陈泽群
 * @date 2018/9/14 10:36
 */
@WebServlet("/pjAddAction")
public class PjAddAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        //获取病历号
        String medicalNum = req.getParameter("medicalNum");
        //获取要添加的项目和对应收费金额
        String[] kk = req.getParameterValues("pid");
        String[] cost = req.getParameterValues("cost");
        PayMgServer payMgServer = new PayMgServerImpl();
        List<PayManager> list = new ArrayList<>();
        if (medicalNum != null) {
            if (kk != null) {
                //将项目转为一个集合
                for (int i = 0; i < kk.length; i++) {
                    PayManager payManager = new PayManager();
                    payManager.setMedicalNum(Integer.parseInt(medicalNum));
                    payManager.setPayId(Integer.parseInt(kk[i]));
                    payManager.setChargeAmount(Double.parseDouble(cost[i]));
                    list.add(payManager);
                }
                if (kk.length == payMgServer.addPay(list)) {
                    //给该用户添加所需要增加的收费项目
                    System.out.println("添加成功");
                    resp.sendRedirect("/his/pJFindAction");
                }
            }

        }

    }
}
