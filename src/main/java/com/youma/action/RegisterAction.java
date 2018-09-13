/**
 * Copyright (C),2015-2018
 * FileNmae: RegisterAction
 * Author:   Administrator
 * Date:     2018/9/713:33
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.RegisterServer;
import com.youma.server.impl.DepServerImpl;
import com.youma.server.impl.DoctorServerImpl;
import com.youma.server.impl.RegisterServerImpl;
import com.youma.vo.Department;
import com.youma.vo.Doctor;
import com.youma.vo.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陈泽群
 */
@WebServlet("/registerAction")
public class RegisterAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        RegisterServer registerServer = new RegisterServerImpl();
        System.out.println("请求参数为" + req.getQueryString());
        String action = req.getParameter("action");
        System.out.println(action);
        if ("add".equals(action)) {
            System.out.println("这是添加事件");
            Register register = new Register();
            // System.out.println("姓名" + req.getParameter("registerName"));
            // System.out.println("证件类型" + req.getParameter("identifierType"));
            // System.out.println("证件号" + req.getParameter("identifierNum"));
            // System.out.println("社保号" + req.getParameter("socialSecurityNum"));
            // System.out.println("联系电话" + req.getParameter("phoneNum"));
            // System.out.println("是否自费" + req.getParameter("expenseFlag"));
            // System.out.println("性别" + req.getParameter("sex"));
            // System.out.println("年龄" + req.getParameter("age"));
            // System.out.println("职业" + req.getParameter("profession"));
            // System.out.println("初复诊" + req.getParameter("czFlag"));
            // System.out.println("所挂科室" + req.getParameter("depName"));
            // System.out.println("指定医生" + req.getParameter("docName"));
            // System.out.println("备注" + req.getParameter("remark"));
            register.setRegisterName(req.getParameter("registerName"));
            register.setIdentifierType(Integer.parseInt(req.getParameter("identifierType")));
            register.setIdentifierNum(req.getParameter("identifierNum"));
            register.setSocialSecurityNum(req.getParameter("socialSecurityNum"));
            register.setPhoneNum(req.getParameter("phoneNum"));
            register.setExpenseFlag(Integer.parseInt(req.getParameter("expenseFlag")));
            register.setSex(Integer.parseInt(req.getParameter("sex")));
            register.setAge(Integer.parseInt(req.getParameter("age")));
            register.setProfession(req.getParameter("profession"));
            register.setCzFlag(Integer.parseInt(req.getParameter("czFlag")));
            register.setDoctorID(Integer.parseInt(req.getParameter("docName")));
            register.setRemark(req.getParameter("remark"));
            if (0 != registerServer.registerAdd(register)) {
                System.out.println("添加成功");
                resp.sendRedirect("/his/registerFindAction");
            } else {
                System.out.println("添加失败");
            }
        } else if ("find".equals(action)) {
            String[] p1 = {"身份证", "护照", "军人证"};
            String[] p2 = {"否", "是"};
            String[] p3 = {"女", "男"};
            String[] p4 = {"初诊", "复诊"};
            req.setAttribute("p1", p1);
            req.setAttribute("p2", p2);
            req.setAttribute("p3", p3);
            req.setAttribute("p4", p4);
            System.out.println("这是单查");
            int id = Integer.parseInt(req.getParameter("medicalNum"));
            Register register = registerServer.findRegister(id);
            System.out.println(register.getDoctorID());
            Doctor doctor = new DoctorServerImpl().findDoctor(register.getDoctorID());
            // System.out.println(doctor.getDepId());
            Department department = new DepServerImpl().findDepartment(doctor.getDepId());
            // System.out.println(department.getDepName());
            req.setAttribute("register", register);
            req.setAttribute("docName", doctor.getDoctorName());
            req.setAttribute("depName", department.getDepName());
            req.getRequestDispatcher("/registration/look.jsp").forward(req, resp);
        } else if ("del".equals(action)) {
            System.out.println("这是删除");
            int id = Integer.parseInt(req.getParameter("medicalNum"));
            if (0 != registerServer.delRegister(id)) {
                System.out.println("删除成功");
                resp.sendRedirect("/his/registerFindAction");
            } else {
                System.out.println("删除失败");
            }

        } else if ("delAll".equals(action)) {
            System.out.println("删除选中的挂号信息");
            String[] a = req.getParameterValues("medicalNum");
            int[] id = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                id[i] = Integer.parseInt(a[i]);
            }
            if (0 != registerServer.delAllRegister(id)) {
                resp.sendRedirect("/his/registerFindAction");
            }
        } else if ("edit".equals(action)) {
            String[] p1 = {"身份证", "护照", "军人证"};
            String[] p2 = {"否", "是"};
            String[] p3 = {"女", "男"};
            String[] p4 = {"初诊", "复诊"};
            req.setAttribute("p1", p1);
            req.setAttribute("p2", p2);
            req.setAttribute("p3", p3);
            req.setAttribute("p4", p4);
            System.out.println("这是edit");
            int id = Integer.parseInt(req.getParameter("medicalNum"));
            Register register = registerServer.findRegister(id);
            System.out.println("edit 医生id" + register.getDoctorID());
            Doctor doctor = new DoctorServerImpl().findDoctor(register.getDoctorID());
            System.out.println("edit 科室id" + doctor.getDepId());
            Department department = new DepServerImpl().findDepartment(doctor.getDepId());
            System.out.println(department.getDepName());
            req.setAttribute("register", register);
            req.setAttribute("depId", department.getId());
            req.getRequestDispatcher("/registration/edit.jsp").forward(req, resp);
        } else if ("update".equals(action)) {
            System.out.println("这是修改事件");
            int id = Integer.parseInt(req.getParameter("medicalNum"));
            Register register = new Register(id);
            // System.out.println("ID" + id);
            // System.out.println("姓名" + req.getParameter("registerName"));
            // System.out.println("证件类型" + req.getParameter("identifierType"));
            // System.out.println("证件号" + req.getParameter("identifierNum"));
            // System.out.println("社保号" + req.getParameter("socialSecurityNum"));
            // System.out.println("联系电话" + req.getParameter("phoneNum"));
            // System.out.println("是否自费" + req.getParameter("expenseFlag"));
            // System.out.println("性别" + req.getParameter("sex"));
            // System.out.println("年龄" + req.getParameter("age"));
            // System.out.println("职业" + req.getParameter("profession"));
            // System.out.println("初复诊" + req.getParameter("czFlag"));
            // System.out.println("所挂科室" + req.getParameter("depName"));
            // System.out.println("指定医生" + req.getParameter("docName"));
            // System.out.println("备注" + req.getParameter("remark"));
            register.setRegisterName(req.getParameter("registerName"));
            register.setIdentifierType(Integer.parseInt(req.getParameter("identifierType")));
            register.setIdentifierNum(req.getParameter("identifierNum"));
            register.setSocialSecurityNum(req.getParameter("socialSecurityNum"));
            register.setPhoneNum(req.getParameter("phoneNum"));
            register.setExpenseFlag(Integer.parseInt(req.getParameter("expenseFlag")));
            register.setSex(Integer.parseInt(req.getParameter("sex")));
            register.setAge(Integer.parseInt(req.getParameter("age")));
            register.setProfession(req.getParameter("profession"));
            register.setCzFlag(Integer.parseInt(req.getParameter("czFlag")));
            register.setDoctorID(Integer.parseInt(req.getParameter("docName")));
            register.setRemark(req.getParameter("remark"));
            if (0 != registerServer.updateRegister(register)) {
                System.out.println("修改成功");
                resp.sendRedirect("/his/registerFindAction");
            } else {
                System.out.println("修改失败");
            }
        }
    }
}
