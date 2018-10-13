/**
 * Copyright (C),2015-2018
 * FileNmae: QfAction
 * Author:   Administrator
 * Date:     2018/10/13 14:49
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.HosServer;
import com.youma.server.impl.HosServerImpl;
import com.youma.vo.HosSettle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * QfAction
 * TODO(描述类的作用)
 *
 * @author 陈泽群
 * @date 2018/10/13 14:49
 */
@WebServlet("/qfAction")
public class QfAction extends HttpServlet {
    private static final long serialVersionUID = -6620151068075037736L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HosServer hosServer = new HosServerImpl();
        List<HosSettle> list = hosServer.findAllCash();
        req.setAttribute("hos", list);
        req.getRequestDispatcher("/hospital/account.jsp").forward(req, resp);
    }
}
