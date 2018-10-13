/**
 * Copyright (C),2015-2018
 * FileNmae: DoctorDelAction
 * Author:   Administrator
 * Date:     2018/10/13 15:45
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DoctorServer;
import com.youma.server.impl.DoctorServerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DoctorDelAction
 * 删除医生
 *
 * @author 陈泽群
 * @date 2018/10/13 15:45
 */
@WebServlet("/doctorDelAction")
public class DoctorDelAction extends HttpServlet {
    private static final long serialVersionUID = -2140422919059818624L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        DoctorServer doctorServer = new DoctorServerImpl();
        doctorServer.delDoctor(id);
        resp.sendRedirect("/his/doctorFindAllAction");
    }
}
