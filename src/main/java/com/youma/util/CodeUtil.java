/**
 * Copyright (C),2015-2018
 * FileNmae: CodeUtil
 * Author:   Administrator
 * Date:     2018/9/17 15:47
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * CodeUtil
 * 生成验证码和验证码图片的工具类
 *
 * @author 陈泽群
 * @date 2018/9/17 15:47
 */
public class CodeUtil {
    /**
     * 定义图片的宽度
     */
    private static int width = 90;
    /**
     * 定义图片的高度
     */
    private static int height = 20;
    /**
     * 定义图片上验证码个数
     */
    private static int codeCount = 4;
    /**
     * 字间隔
     */
    private static int xx = 15;
    /**
     * 字体高度
     */
    private static int fontHeight = 18;
    /**
     * 字位置
     */
    private static int codeY = 16;
    /**
     * 验证码集合
     */
    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 生成一个map集合
     * code为生成的验证码
     * codePic为生成验证码的图片
     * @return
     */
    public static Map<String, Object> generateCode() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = bufferedImage.getGraphics();
        Random random = new Random();
        //将图像填充成白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        //设置字体
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        gd.setFont(font);
        //画黑色边框
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
        //随机生成30条干扰线
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xx = random.nextInt(12);
            int yy = random.nextInt(12);
            gd.drawLine(x, y, x + xx, y + yy);
        }
        //保存随机生成的验证码，方便登录验证
        StringBuffer sb = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        for (int i = 0; i < codeCount; i++) {
            int r = random.nextInt(codeSequence.length);
            String code = String.valueOf(codeSequence[r]);
            //产生随机的颜色，使每位的验证码颜色不同
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);
            sb.append(code);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", sb);
        map.put("codePic", bufferedImage);
        return map;
    }
}
