/**
 * Copyright (C),2015-2018
 * FileNmae: DoctorFindAction
 * Author:   Administrator
 * Date:     2018/9/814:14
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
@WebServlet("/doctorFindAction")
public class DoctorFindAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset:UTF-8");
        DoctorServer doctorServer = new DoctorServerImpl();
        String id = req.getParameter("id");
        Doctor doctor;
        if (id != null) {
            doctor = doctorServer.findDoctor(Integer.parseInt(id));
            req.setAttribute("doctor", doctor);
        }
        req.getRequestDispatcher("/doctor/edit.jsp").forward(req, resp);
    }
}
