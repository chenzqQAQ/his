/**
 * Copyright (C),2015-2018
 * FileNmae: Upload
 * Author:   Administrator
 * Date:     2018/9/1315:56
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 陈泽群
 * @date 2018/9/1315:56
 */
public class Upload {
    /**
     * 上传文件的保存目录
     */
    private String savePath;
    /**
     * 上传文件的临时文件目录
     */
    private String tempPath;

    public Upload(String savePath, String tempPath) {
        this.savePath = savePath;
        this.tempPath = tempPath;
    }

    /**
     * 生成上传文件的文件名,文件名以标识码+"_"+文件的原始名称
     *
     * @param filename
     * @return
     */
    private String makeFileName(String filename) {
        return UUID.randomUUID().toString() + "_" + filename;
    }

    /**
     * 防止一个目录下出现太多文件,使用hash算法打散存储
     *
     * @param filename 文件名,根据文件名生成存储目录
     * @param savePath 文件存储路径
     * @return 新的存储路径
     */
    private String makePath(String filename, String savePath) {
        int hashcode = filename.hashCode();
        //0--15
        int dir1 = hashcode & 0xf;
        //0--15
        int dir2 = (hashcode & 0xf0) >> 4;
        // upload\2\3
        String dir = savePath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }

    /**
     * 文件上传
     *
     * @return
     */
    public Map<String, String> up(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        //文件上传成败标志
        int flag = 0;
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创造临时路径
            tmpFile.mkdirs();
        }
        //使用Apache文件上传组件处理文件的步骤
        //1.创建一个DiskFileItemFactory工厂
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，默认为10KB,当文件的上传大小超过缓冲区的大小时,会生成一个临时文件
            //存放到指定的临时目录
            factory.setSizeThreshold(1014 * 100);
            //设置文件上传的临时文件保存目录
            factory.setRepository(tmpFile);
            //2.创建一个文件的上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //监听文件的上传进度
            // upload.setProgressListener(new ProgressListener() {
            //     @Override
            //     public void update(long l, long l1, int i) {
            //         System.out.println(String.format("当前已处理:%s,总的文件大小为%s", String.valueOf(l), String.valueOf(l1)));
            //     }
            // });
            //解决上传文件的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3.判断提交上来的数据是否有文件内容(enctype="multipart/form-data")
            if (!ServletFileUpload.isMultipartContent(req)) {

                //表单请求中没有文件类型
                map.put("message", "没有文件类型");
                return map;
            }
            //设置上传的单个文件的大小的最大值,目前为10MB
            upload.setFileSizeMax(1024 * 1024 * 10);
            //设置上传的文件总量的最大值，为同时上传的多个文件中的大小之和
            upload.setSizeMax(1024 * 1024 * 100);
            //4
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem fileItem : list) {
                if (!fileItem.isFormField()) {

                    //获取文件名
                    String name = fileItem.getName();
                    if (name == null || name.trim().equals("")) {
                        continue;
                    }
                    System.out.println("文件名："+name);
                    //获取文件名部分
                    name = name.substring(name.lastIndexOf("\\") + 1);
                    System.out.println("文件："+name);
                    //获取文件的扩展名
                    String fileExtName = name.substring(name.lastIndexOf(".") + 1);
                    InputStream in = fileItem.getInputStream();
                    String saveFilename = makeFileName(name);
                    String realSavePath = makePath(saveFilename, savePath);
                    String realUrl=realSavePath + "\\" +saveFilename;
                    FileOutputStream fileOutputStream = new FileOutputStream(realUrl);
                    map.put("url", realUrl);
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    in.close();
                    fileOutputStream.close();

                } else {
                    //将非文件表单请求数据加入map
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    // System.out.println(name+":"+value);
                    map.put(name,value);
                }
            }
            //文件上传成功
            map.put("message", "文件上传成功");
            flag = 1;
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            //单个文件超过最大值
            flag = 2;
            map.put("message", "单个文件超过最大值");
            return map;
        } catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            //文件总量超过最大值
            flag = 3;
            map.put("message", "文件总量超过最大值");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            //文件上传失败
            flag = 4;
            map.put("message", "文件上传失败");
            return map;
        }
        return map;
    }
}
