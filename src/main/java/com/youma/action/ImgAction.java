/**
 * Copyright (C),2015-2018
 * FileNmae: ImgAction
 * Author:   Administrator
 * Date:     2018/9/15 9:04
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ImgAction
 * 解析图片文件,在页面显示本机存储的图片
 *
 * @author 陈泽群
 * @date 2018/9/15 9:04
 */
@WebServlet("/imgAction")
public class ImgAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //前台图片的src将要显示的图片目录传入
        String url = req.getParameter("url");
        File file = new File(url);
        if (!file.exists()) {
            System.out.println("显示的图片不存在");
        } else {
            //获取响应的字节流,以便图片数据的输出
            OutputStream out = resp.getOutputStream();
            //打开图片文件的读入流
            FileInputStream in = new FileInputStream(file);
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            //关闭输入输出流
            in.close();
            out.close();
        }

    }
}
