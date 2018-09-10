/**
 * Copyright (C),2015-2018
 * FileNmae: DoctorFindAllAction
 * Author:   Administrator
 * Date:     2018/9/518:58
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youma.server.DoctorServer;
import com.youma.server.impl.DepServerImpl;
import com.youma.server.impl.DoctorServerImpl;
import com.youma.util.Exclude;
import com.youma.util.Page;
import com.youma.vo.Department;
import com.youma.vo.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈泽群
 */
@WebServlet("/doctorFindAllAction")
public class DoctorFindAllAction extends HttpServlet {
    public static String FINDALL = "findAll";
    GsonBuilder gsonBuilder = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getAnnotation(Exclude.class) != null;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    });
    Gson gson = gsonBuilder.create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset:UTF-8");
        String action = req.getParameter("action");
        System.out.println(req.getQueryString());
        DoctorServer doctorServer = new DoctorServerImpl();
        String depName = req.getParameter("depName");
        if (null != depName && !("").equals(depName)) {
            int col = doctorServer.allDoctorCount(depName);
            System.out.println(String.format("%s科室有%d人", depName, col));
            Page page = new Page();
            page.setTotalCount(col);
            if (null != req.getParameter("pageNo")) {
                page.setPageNo(Integer.parseInt(req.getParameter("pageNo")));
            } else {
                page.setPageNo(1);
            }
            List<Doctor> list = doctorServer.findAllDoctor(depName, page);
            req.setAttribute("depName", depName);
            req.setAttribute("doctors", list);
            req.setAttribute("page", page);
        } else {
            if (FINDALL.equals(action)) {
                System.out.println("开始医生全查");
                List<Doctor> list;
                if (req.getParameter("depId") != null) {
                    int id = Integer.parseInt(req.getParameter("depId"));
                    System.out.println(id);
                    list = doctorServer.findDoctorByDep(id);
                } else {
                    list = doctorServer.findAllDoctor();
                }
                String json = gson.toJson(list);
                System.out.println(json);
                PrintWriter out = resp.getWriter();
                out.write(json);
                out.flush();
                return;
            } else {
                Page page = new Page();
                String pageNo = req.getParameter("pageNo");
                int col = doctorServer.allDoctorCount();
                page.setTotalCount(col);
                if (null != pageNo && !("").equals(pageNo)) {
                    page.setPageNo(Integer.parseInt(pageNo));
                } else {
                    page.setPageNo(1);
                }
                List<Doctor> list = new ArrayList<>();
                list = doctorServer.findAllDoctor(page);
                req.setAttribute("doctors", list);
                req.setAttribute("page", page);
            }
        }
        List<Department> list1 = new DepServerImpl().findAllDepartment();
        Map<Long, String> map = new HashMap<>();
        for (int i = 0; i < list1.size(); i++) {
            Department department = list1.get(i);
            map.put((long) department.getId(), department.getDepName());
        }
        req.setAttribute("dep", map);
        req.getRequestDispatcher("/doctor/index.jsp").forward(req, resp);

    }
}
