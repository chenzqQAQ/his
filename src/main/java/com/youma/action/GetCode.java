/**
 * Copyright (C),2015-2018
 * FileNmae: GetCode
 * Author:   Administrator
 * Date:     2018/9/17 16:17
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.action;

import com.youma.util.CodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

/**
 * GetCode
 * 后台产生验证码
 *
 * @author 陈泽群
 * @date 2018/9/17 16:17
 */
@WebServlet("/getCode")
public class GetCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> codeMap = CodeUtil.generateCode();
        HttpSession session = req.getSession();
        session.setAttribute("code", codeMap.get("code").toString());

        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);
        resp.setContentType("image/jpeg");
        //将图像输出到响应中
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", out);
        out.close();
    }
}
