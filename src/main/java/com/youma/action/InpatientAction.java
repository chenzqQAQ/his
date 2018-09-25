/**
 * Copyright (C),2015-2018
 * FileNmae: InpatientAction
 * Author:   Administrator
 * Date:     2018/9/139:40
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.HosServer;
import com.youma.server.InpServer;
import com.youma.server.impl.HosServerImpl;
import com.youma.server.impl.InpServerImpl;
import com.youma.vo.Inpatient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 住院信息修改action
 *
 * @author 陈泽群
 */
@WebServlet("/inpatientAction")
public class InpatientAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        InpServer inpServer = new InpServerImpl();
        HosServer hosServer = new HosServerImpl();
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            //进入住院信息添加操作;
            int medicalNum = Integer.parseInt(req.getParameter("medicalNum"));
            String nurse = req.getParameter("nurse");
            String bedNum = req.getParameter("bedNum");
            double deposit = Double.parseDouble(req.getParameter("deposit"));
            String illness = req.getParameter("illness");
            Inpatient inpatient = new Inpatient();
            inpatient.setMedicalNum(medicalNum);
            inpatient.setNurse(nurse);
            inpatient.setBedNum(bedNum);
            inpatient.setDeposit(deposit);
            inpatient.setIllness(illness);
            if (0 != inpServer.inpatientAdd(inpatient) && 0 != hosServer.hosSettleAdd(inpatient)) {
                //添加成功，跳转到全查结果
                resp.sendRedirect("/his/inpFindAction?action=findAll");
            }
        } else if("update".equals(action)){
            int medicalNum = Integer.parseInt(req.getParameter("medicalNum"));
            String nurse = req.getParameter("nurse");
            String bedNum = req.getParameter("bedNum");
            double deposit = Double.parseDouble(req.getParameter("deposit"));
            String illness = req.getParameter("illness");
            Inpatient inpatient = new Inpatient();
            inpatient.setMedicalNum(medicalNum);
            inpatient.setNurse(nurse);
            inpatient.setBedNum(bedNum);
            inpatient.setDeposit(deposit);
            inpatient.setIllness(illness);
            if (0 != inpServer.updateInpatient(inpatient)) {
                //添加成功，跳转到全查结果
                resp.sendRedirect("/his/inpFindAction?action=findAll");
            }

        }
    }
}
