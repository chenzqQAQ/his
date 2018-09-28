/**
 * Copyright (C),2015-2018
 * FileNmae: InpExlOut
 * Author:   Administrator
 * Date:     2018/9/28 19:49
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.server.InpServer;
import com.youma.server.impl.InpServerImpl;
import com.youma.util.Exl;
import com.youma.vo.Inpatient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *InpExlOut
 *直接下载住院信息
 *@author 陈泽群
 *@date 2018/9/28 19:49
 */
@WebServlet("/inpExlOut")
public class InpExlOut extends HttpServlet {
    private static final long serialVersionUID = 6238311370645910714L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        InpServer inpServer=new InpServerImpl();
        List<Inpatient> list=inpServer.findAllInpatient();
        Exl exl=new Exl("住院信息表");
        if(exl.createExl(Inpatient.class,resp,list,"InpAll.xlsx")!=1){
            System.out.println("文件下载失败");
        }

    }
}
