/**
 * Copyright (C),2015-2018
 * FileNmae: DoctorUpdateAction
 * Author:   Administrator
 * Date:     2018/9/815:54
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DoctorServer;
import com.youma.server.impl.DoctorServerImpl;
import com.youma.vo.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈泽群
 */
@WebServlet("/doctorUpdateAction")
public class DoctorUpdateAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset:UTF-8");
        Doctor doctor = new Doctor();
        doctor.setId(Integer.parseInt(req.getParameter("id")));
        doctor.setDoctorName(req.getParameter("doctorName"));
        doctor.setIdentifierType(Integer.parseInt(req.getParameter("identifierType")));
        doctor.setIdentifierNum(req.getParameter("identifierNum"));
        doctor.setPhoneNum(req.getParameter("phoneNum"));
        doctor.setSetaPhoneNum(req.getParameter("setaPhoneNum"));
        doctor.setSex(Integer.parseInt(req.getParameter("sex")));
        doctor.setAge(Integer.parseInt(req.getParameter("age")));
        doctor.setBirthday(req.getParameter("birthday"));
        doctor.setEmail(req.getParameter("email"));
        doctor.setDepId(Integer.parseInt(req.getParameter("depId")));
        doctor.setDegree(Integer.parseInt(req.getParameter("degree")));
        doctor.setRemark(req.getParameter("remark"));
        DoctorServer doctorServer = new DoctorServerImpl();
        if (0 != doctorServer.updateDoctor(doctor)) {
            System.out.println("修改成功");
            resp.sendRedirect("/his/doctorFindAllAction");
        } else {
            System.out.println("修改失败");
            resp.sendRedirect("/his/doctorFindAction?id="+doctor.getId());
        }

    }
}
