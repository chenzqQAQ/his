/**
 * Copyright (C),2015-2018
 * FileNmae: Exl
 * Author:   Administrator
 * Date:     2018/9/1215:01
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成Exl文件的工具包 配合Czq注释使用最好
 *
 * @author 陈泽群
 */
public class Exl {
    /**
     * 工作薄
     */
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;

    /**
     * 生成对应名字的工作表(使用poi)
     *
     * @param str
     */
    public Exl(String str) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(str);
    }

    /**
     * 获取一个实体类(POJO)中带有指定注释类的属性数组
     *
     * @param class1 实体类
     * @param class2 注释
     * @param <T>
     * @return 实体类(POJO)中带有指定注释类的属性数组
     */
    private  <T extends Annotation> Field[] getFiled(Class class1, Class<T> class2) {
        List<Field> list = new ArrayList<>();
        if (class2.isAnnotation()) {
            //如果class2为一个注释类,获取class1中的全部属性
            Field[] fields = class1.getDeclaredFields();
            for (Field field : fields) {
                if (field.getDeclaredAnnotation(class2) != null) {
                    //如果该属性找的到class2注释,添加到列表中
                    list.add(field);
                }
            }
            //将属性列表转为数组并返回
            return list.toArray(new Field[list.size()]);
        } else {
            //如果class2不为注释类,返回空
            return null;
        }
    }

    /**
     * 生成exl文件,根据类获取有Czq注释的属性,将有Czq注释的属性连同值一起导出。
     *
     * @param c    要导出的数据类
     * @param str  exl文件的名字(包含路径new File(str))
     * @param list exl数据的列表,要与类对应
     * @param <T>
     * @return 状态1：exl导出成功,2:导出失败
     */
    public <T> int createExl(Class<T> c, String str, List<T> list) {
        row = sheet.createRow(0);
        //获取该类中带有Czq注释的全部属性
        Field[] fields = this.getFiled(c, Czq.class);
        if (fields == null) {
            //返回的属性为空，没有属性可以导出到Exl
            System.out.println("没有可导出的属性");
            return 2;
        }
        //遍历属性,修改属性权限，提取注释中的中文注解作为表的第一行
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].isAccessible()) {
                //修改属性权限,方便反射取值
                fields[i].setAccessible(true);
            }
            Cell cell = row.createCell(i);
            Czq czq = fields[i].getDeclaredAnnotation(Czq.class);
            //有Czq注释,就输入注释的中文注解
            if (czq != null) {
                cell.setCellValue(czq.name());
            } //没Czq注释,就输入属性名
            else {
                cell.setCellValue(fields[i].getName());
            }
        }
        int max = list.size();
        for (int i = 0; i < max; i++) {
            T t = list.get(i);
            row = sheet.createRow(i + 1);
            for (int j = 0; j < fields.length; j++) {
                Cell cell = row.createCell(j);
                Czq czq = fields[j].getDeclaredAnnotation(Czq.class);
                try {
                    String string;
                    //如果有数组的注释，表示是int型,将值转为对应字符串
                    if (czq.value().length != 1) {
                        String[] strs = czq.value();
                        int index = fields[j].getInt(t);
                        if (index < 0 || index > strs.length - 1) {
                            //如果数字不在注解范围类，注入数值
                            string = String.valueOf(index);
                        } else {
                            string = strs[index];
                        }
                    } else {
                        if (fields[j].get(t) == null || "null".equals(fields[j].get(t))) {
                            //消除null
                            string = "";
                        } else {
                            string = String.valueOf(fields[j].get(t));
                        }
                    }
                    cell.setCellValue(string);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return 2;
                }
            }
        }
        //列宽自适应
        for (int i = 0; i < fields.length; i++) {
            Czq czq=fields[i].getDeclaredAnnotation(Czq.class);
            //获取注解设置的列宽(几个字符)
            int l=czq.length();
            sheet.setColumnWidth(i,l*256+148);
        }
        FileOutputStream out = null;
        try {
            //将工作表输出到对应文件中
            out = new FileOutputStream(new File(str));
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            // System.out.println("Exl文件生成出错");
            return 2;
        }
        // System.out.println("Exl生成成功");
        return 1;
    }

    /**
     * 下载exl文件,根据类获取有Czq注释的属性,将有Czq注释的属性连同值一起导出。
     *
     * @param c    要导出的数据类
     * @param resp 响应
     * @param list exl数据的列表,要与类对应
     * @param <T>
     * @return 状态1：exl导出成功,2:导出失败
     */
    // public <T> int createExl(Class<T> c, HttpServletResponse resp, List<T> list) {
    //     row = sheet.createRow(0);
    //     //获取该类中带有Czq注释的全部属性
    //     Field[] fields = this.getFiled(c, Czq.class);
    //     // Field[] fields = Register.class.getDeclaredFields();
    //     if (fields == null) {
    //         //返回的属性为空，没有属性可以导出到Exl
    //         System.out.println("没有可导出的属性");
    //         return 2;
    //     }
    //     //遍历属性,修改属性权限，提取注释中的中文注解作为表的第一行
    //     for (int i = 0; i < fields.length; i++) {
    //         if (!fields[i].isAccessible()) {
    //             //修改属性权限,方便反射取值
    //             fields[i].setAccessible(true);
    //         }
    //         Cell cell = row.createCell(i);
    //         Czq czq = fields[i].getDeclaredAnnotation(Czq.class);
    //         //有Czq注释,就输入注释的中文注解
    //         if (czq != null) {
    //             cell.setCellValue(czq.name());
    //         } //没Czq注释,就输入属性名
    //         else {
    //             cell.setCellValue(fields[i].getName());
    //         }
    //     }
    //     int max = list.size();
    //     for (int i = 0; i < max; i++) {
    //         T t = list.get(i);
    //         row = sheet.createRow(i + 1);
    //         for (int j = 0; j < fields.length; j++) {
    //             Cell cell = row.createCell(j);
    //             Czq czq = fields[j].getDeclaredAnnotation(Czq.class);
    //             try {
    //                 String string;
    //                 //如果有数组的注释，表示是int型,将值转为对应字符串
    //                 if (czq.value().length != 1) {
    //                     String[] strs = czq.value();
    //                     int index = fields[j].getInt(t);
    //                     if (index < 0 || index > strs.length - 1) {
    //                         //如果数字不在注解范围类，注入数值
    //                         string = String.valueOf(index);
    //                     } else {
    //                         string = strs[index];
    //                     }
    //                 } else {
    //                     if (fields[j].get(t) == null || "null".equals(fields[j].get(t))) {
    //                         //消除null
    //                         string = "";
    //                     } else {
    //                         string = String.valueOf(fields[j].get(t));
    //                     }
    //                 }
    //                 cell.setCellValue(string);
    //                 System.out.println(string);
    //             } catch (IllegalAccessException e) {
    //                 e.printStackTrace();
    //                 return 2;
    //             }
    //         }
    //     }
    //     //列宽自适应
    //     for (int i = 0; i < sheet.getLastRowNum(); i++) {
    //         sheet.autoSizeColumn(i, true);
    //     }
    //     OutputStream out = null;
    //     try {
    //         //将工作表输出到对应文件中
    //         out = resp.getOutputStream();
    //         workbook.write(out);
    //         out.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         System.out.println("Exl文件生成出错");
    //         return 2;
    //     }
    //     System.out.println("Exl生成成功");
    //     return 1;
    // }
}
