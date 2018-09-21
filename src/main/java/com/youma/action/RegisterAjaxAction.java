/**
 * Copyright (C),2015-2018
 * FileNmae: RegisterAjaxAction
 * Author:   Administrator
 * Date:     2018/9/1015:55
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.google.gson.Gson;
import com.youma.server.DepServer;
import com.youma.server.DoctorServer;
import com.youma.server.InpServer;
import com.youma.server.RegisterServer;
import com.youma.server.impl.DepServerImpl;
import com.youma.server.impl.DoctorServerImpl;
import com.youma.server.impl.InpServerImpl;
import com.youma.server.impl.RegisterServerImpl;
import com.youma.vo.Department;
import com.youma.vo.Doctor;
import com.youma.vo.Inpatient;
import com.youma.vo.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 住院页面异步请求病历号信息
 * 查看是否可住院
 * @author 陈泽群
 */
@WebServlet("/registerAjaxAction")
public class RegisterAjaxAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        String medicalNum = req.getParameter("medicalNum");
        if (null != medicalNum && !("").equals(medicalNum)) {
            InpServer inpServer = new InpServerImpl();
            Inpatient inpatient = inpServer.findInpatient(Integer.parseInt(medicalNum));
            // System.out.println("病例号为" + medicalNum);
            RegisterServer registerServer = new RegisterServerImpl();
            Register register = registerServer.findRegister(Integer.parseInt(medicalNum));
            DoctorServer doctorServer = new DoctorServerImpl();
            Doctor doctor = doctorServer.findDoctor(register.getDoctorID());
            // System.out.println("医生为" + doctor.getDoctorName());
            DepServer depServer = new DepServerImpl();
            Department department = depServer.findDepartment(doctor.getDepId());
            // System.out.println("科室为" + department.getDepName());
            Gson gson = new Gson();
            String str1 = gson.toJson(register);
            String str4 = str1.substring(0, str1.length() - 1);
            String str;
            if (inpatient.getMedicalNum() != 0) {
                PrintWriter out = resp.getWriter();
                //已经住院
                str = String.format("\"noFind\":%d,", 1);
            } else {
                str = String.format("\"noFind\":%d,", 2);
            }
            str4 = str4 + String.format(",\"docName\":\"%s\",", doctor.getDoctorName()) +str+ String.format("\"depName\":\"%s\"}", department.getDepName());
            System.out.println(str4);
            PrintWriter out = resp.getWriter();
            out.write(str4);
            out.flush();
            return;
        }
    }
}
