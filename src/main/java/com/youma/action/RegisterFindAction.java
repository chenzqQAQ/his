/**
 * Copyright (C),2015-2018
 * FileNmae: RegisterFindAction
 * Author:   Administrator
 * Date:     2018/9/89:11
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.RegisterServer;
import com.youma.server.impl.DepServerImpl;
import com.youma.server.impl.DoctorServerImpl;
import com.youma.server.impl.RegisterServerImpl;
import com.youma.util.Page;
import com.youma.vo.Department;
import com.youma.vo.Doctor;
import com.youma.vo.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈泽群
 */
@WebServlet("/registerFindAction")
public class RegisterFindAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        Page page;
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("page") == null) {
            page = new Page();
        } else {
            page = (Page) httpSession.getAttribute("page");
        }
        RegisterServer registerServer = new RegisterServerImpl();
        String pNo = req.getParameter("pageNo");
        String id = req.getParameter("medicalNum");
        String docName = req.getParameter("docName");
        String depName = req.getParameter("depName");
        /**
         * 保留上次id,docName,depName在session,从中取出
         */
        if (id == null) {
            if (httpSession.getAttribute("medicalNum") != null && !"".equals(httpSession.getAttribute("medicalNum"))) {
                id = (String) httpSession.getAttribute("medicalNum");
            }
        }
        if (docName == null) {
            if (httpSession.getAttribute("docName") != null && !"".equals(httpSession.getAttribute("docName"))) {
                docName = (String) httpSession.getAttribute("docName");
            }
        }
        if (depName == null) {
            if (httpSession.getAttribute("depName") != null && !"".equals(httpSession.getAttribute("depName"))) {
                depName = (String) httpSession.getAttribute("depName");
            }
        }
        // System.out.println("传来的页号为" + pNo);
        List<Register> list = new ArrayList<>();
        /**
         * 当传来的页码没有时，说明准备分页
         */
        if (pNo == null || ("").equals(pNo)) {
            list = registerServer.findAllRegister();
            page.setTotalCount(list.size());
            page.setPageNo(1);
            httpSession.setAttribute("page", page);
        } else {
            page.setPageNo(Integer.parseInt(pNo));
        }
        // System.out.println("传来的病历号为" + id);
        // System.out.println("传来的医生名为" + docName);
        // System.out.println("传来的科室号为" + depName);
        List<Register> myList = new ArrayList<>();
        boolean b = (null != docName && !("").equals(docName)) || (null != depName && !("").equals(depName));
        if (null != id && !("").equals(id)) {
            list = new ArrayList<>();
            httpSession.setAttribute("medicalNum", id);
            Register register = registerServer.findRegister(Integer.parseInt(id));
            if (register.getMedicalNum() != 0) {
                // System.out.println("找到了");
                list.add(register);
                page.setTotalCount(list.size());
                page.setPageNo(1);
            } else {
                // System.out.println("没找到");
                page.setTotalCount(0);
                page.setPageNo(1);
            }
        } else if (b) {
            /**
             * 当医生名字or科室名字有(包括在会话session中时),进行模糊查询
             */
            String[] args = new String[2];
            if (null != docName && !("").equals(docName)) {
                args[0] = docName;
                httpSession.setAttribute("docName", docName);
            } else {
                args[0] = null;
                httpSession.removeAttribute("docName");
            }
            if (null != depName && !("").equals(depName)) {
                args[1] = depName;
                httpSession.setAttribute("depName", depName);
            } else {
                args[1] = null;
                httpSession.removeAttribute("depName");
            }
            // System.out.println(String.format("医生名%s", docName));
            // System.out.println(String.format("科室名%s", depName));
            int col = registerServer.findRegisterCount(args);
            page.setTotalCount(col);
            // System.out.println(String.format("条数为%d", col));
            list = registerServer.PageAllRegister(page, args);
        } else {
            httpSession.removeAttribute("medicalNum");
            httpSession.removeAttribute("depName");
            httpSession.removeAttribute("docName");
            // System.out.println("进入全查");
            // System.out.println("全查总数" + page.getTotalCount());
            // System.out.println("当前页面" + page.getPageNo());
            // System.out.println("总页面数" + page.getTotalPage());
            // System.out.println("单页条数" + page.getPageSize());
            // System.out.println("查询的偏移量" + page.getOffset());
            myList = registerServer.PageAllRegister(page);
        }
        List<Doctor> list1 = new DoctorServerImpl().findAllDoctor();
        List<Department> list2 = new DepServerImpl().findAllDepartment();
        Map<Long, String> map = new HashMap<>();
        Map<Long, String> map1 = new HashMap<>();
        for (int i = 0; i < list1.size(); i++) {
            Doctor doctor = list1.get(i);
            map.put((long) doctor.getId(), doctor.getDoctorName());
            for (int j = 0; j < list2.size(); j++) {
                if (list2.get(j).getId() == doctor.getDepId()) {
                    map1.put((long) doctor.getId(), list2.get(j).getDepName());
                    break;
                }
            }
        }
        req.setAttribute("map", map);
        req.setAttribute("map1", map1);

        String[] p1 = {"身份证", "护照", "军人证"};
        String[] p2 = {"否", "是"};
        String[] p3 = {"女", "男"};
        String[] p4 = {"初诊", "复诊"};
        req.setAttribute("p1", p1);
        req.setAttribute("p2", p2);
        req.setAttribute("p3", p3);
        req.setAttribute("p4", p4);
        if (myList.size() != 0) {
            req.setAttribute("registers", myList);
        } else {
            req.setAttribute("registers", list);
        }
        req.setAttribute("page", page);
        req.getRequestDispatcher("/registration/index.jsp").forward(req, resp);
    }
}
