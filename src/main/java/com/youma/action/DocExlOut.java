/**
 * Copyright (C),2015-2018
 * FileNmae: DocExlOut
 * Author:   Administrator
 * Date:     2018/9/15 11:30
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.DoctorServer;
import com.youma.server.impl.DoctorServerImpl;
import com.youma.util.Exl;
import com.youma.vo.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * DocExlOut
 * 异步导出医生信息exl文件到服务器的缓存地址
 *
 * @author 陈泽群
 * @date 2018/9/15 11:30
 */
@WebServlet("/docExlOut")
public class DocExlOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset:UTF-8");
        String all = req.getParameter("all");
        //全部挂号信息表文件存放地址
        String allDoc = "D://docAll.xlsx";
        //选中挂号部分信息表存放地址
        String partDoc = "D://docPart.xlsx";
        String str;
        DoctorServer doctorServer = new DoctorServerImpl();
        if (all == null) {
            //导出全部的医生信息表
            List<Doctor> list = doctorServer.findAllDoctor();
            //设置工作表的名字
            Exl exl = new Exl("医生信息表");
            //exl保存路径
            int i = exl.createExl(Doctor.class, allDoc, list);

            PrintWriter out = resp.getWriter();
            if (1 == i) {
                //将文件的缓存地址发给页面,以便下载找到文件原始地址
                str = "{\"message\":1,\"url\":\"" + allDoc + "\"}";
                out.write(str);
            } else {
                //exl文件生成失败
                out.write("{message:2}");
            }
            out.flush();
            return;
        } else {
            //导出选中部分的医生信息
            String[] id = req.getParameterValues("medicalNum");
            int[] ids = new int[id.length];
            for (int i = 0; i < id.length; i++) {
                ids[i] = Integer.parseInt(id[i]);
            }
            List<Doctor> list = doctorServer.findAllDoctor();
            Exl exl = new Exl("部分医生信息表");
            PrintWriter out = resp.getWriter();
            if (1 == exl.createExl(Doctor.class, partDoc, list)) {
                str = "{\"message\":1,\"url\":\"" + partDoc + "\"}";
                out.write(str);
            } else {
                out.write("2");
            }
            out.flush();
            return;
        }
    }
}
