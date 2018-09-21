/**
 * Copyright (C),2015-2018
 * FileNmae: PayAction
 * Author:   Administrator
 * Date:     2018/9/20 15:04
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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * PayAction
 * 付款，更新日期
 *
 * @author 陈泽群
 * @date 2018/9/20 15:04
 */
@WebServlet("/payAction")
public class PayAction extends HttpServlet {
    private static final long serialVersionUID = -935523341684232762L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        PayMgServer payMgServer = new PayMgServerImpl();
        DisServer disServer = new DisServerImpl();
        HosServer hosServer=new HosServerImpl();
        int id = Integer.parseInt(req.getParameter("medicalNum"));
        Date date = new Date();
        //更新该病历号的收费项目的收费时间(未收费的行,收费时间为null的行)
        int a = payMgServer.pay(id, date);
        //更新该病历号的药品分发的收费时间(未收费的行,收费时间为null的行)
        int b = disServer.pay(id, date);
        if(a!=0||b!=0)
        {
            //有需要收费的项目或药品
            System.out.println("付款成功");
        }
        else{
            System.out.println("无需付款");
        }
        //更新收费时间到结算表中
        hosServer.pay(id,date);
        //重定向到这个病历号的单查页面
        resp.sendRedirect("/his/hosFindAction?action=find&medicalNum="+id);

    }
}
