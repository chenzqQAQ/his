/**
 * Copyright (C),2015-2018
 * FileNmae: Down
 * Author:   Administrator
 * Date:     2018/9/14 16:46
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.util.Upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Down
 * 文件下载
 * 传来url
 * @author 陈泽群
 * @date 2018/9/14 16:46
 */
@WebServlet("/down")
public class Down extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Upload upload = new Upload();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //获取文件在服务器的存放地址(最好是绝对路径)
        System.out.println(req.getParameter("url"));
        //使用编写的文件上传下载类来请求下载文件
        int i=upload.down(req.getParameter("url"),resp);
        if(0!=i)
        {
            //返回0,下载成功
            System.out.println("文件下载成功");
        }
        resp.sendRedirect("his/registerFindAction");
    }
}
