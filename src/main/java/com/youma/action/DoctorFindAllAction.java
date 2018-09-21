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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生全查信息
 * @author 陈泽群
 */
@WebServlet("/doctorFindAllAction")
public class DoctorFindAllAction extends HttpServlet {
    public static String FINDALL = "findAll";
    /**
     * 构造string转json的GOSN类
     * 忽略带有@Exclude注释的属性
     * 有注释的属性不加入json
     */
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
        //获取action,判断是什么操作
        String action = req.getParameter("action");
        System.out.println(req.getQueryString());
        DoctorServer doctorServer = new DoctorServerImpl();
        int flag = 0;
        String docId = req.getParameter("docId");
        String docName = req.getParameter("docName");
        String depName = req.getParameter("depName");
        String[] str = new String[3];
        if (FINDALL.equals(action)) {
            //医生全查
            System.out.println("开始医生全查");
            List<Doctor> list;
            //获取医生信息
            if (req.getParameter("depId") != null) {
                //有科室,查询该科室的全部医生
                int id = Integer.parseInt(req.getParameter("depId"));
                System.out.println(id);
                list = doctorServer.findDoctorByDep(id);
            } else {
                //没有科室选择，查询全部医生
                list = doctorServer.findAllDoctor();
            }
            //将信息转为json格式
            String json = gson.toJson(list);
            PrintWriter out = resp.getWriter();
            out.write(json);
            out.flush();
            return;
        }
        //医生查询
        //获取查询的医生id
        if (docId != null) {
            str[0] = docId;
            req.setAttribute("docId", docId);
        } else {
            str[0] = null;
            req.removeAttribute("depName");
        }
        //获取查询的医生名字
        if (docName != null) {
            str[1] = docName;
            req.setAttribute("docName", docName);
        } else {
            str[1] = null;
            req.removeAttribute("docName");
        }
        // 获取查询的科室姓名
        if (depName != null) {
            str[2] = depName;
            req.setAttribute("depName", depName);
        } else {
            str[2] = null;
            req.removeAttribute("depName");
        }
        int col = doctorServer.allDoctorCount(str);
        Page page = new Page();
        page.setTotalCount(col);
        //获取页码，没有就默认首页
        if (null != req.getParameter("pageNo") && !req.getParameter("pageNo").isEmpty()) {
            page.setPageNo(Integer.parseInt(req.getParameter("pageNo")));
        } else {
            page.setPageNo(1);
        }
        //查询当前页要显示的医生信息(符合查询的)
        List<Doctor> list = doctorServer.findAllDoctor(str, page);
        req.setAttribute("doctors", list);
        req.setAttribute("page", page);
        List<Department> list1 = new DepServerImpl().findAllDepartment();
        //将科室的id与名字对应生成map映射
        Map<Long, String> map = new HashMap<>();
        for (int i = 0; i < list1.size(); i++) {
            Department department = list1.get(i);
            //设置科室id为key,名字为value
            map.put((long) department.getId(), department.getDepName());
        }
        //将map传给页面,方便页面根据科室的id显示对应的科室名
        req.setAttribute("dep", map);
        //请求转发到医生信息列表页面
        req.getRequestDispatcher("/doctor/index.jsp").forward(req, resp);
    }
}
