/**
 * Copyright (C),2015-2018
 * FileNmae: Resources
 * Author:   Administrator
 * Date:     2018/8/2718:02
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.vo;

/**
 * 权限资源实体类
 * @author Administrator
 */
public class Resources {
    /**
     * 权限id
     */
    private int resID;
    /**
     * 资源名称
     */
    private String resName;
    /**
     * 权限url
     */
    private String resUrl;
    /**
     * 父资源id
     */
    private int resParentId;
    /**
     * 状态, 0:无效,1:有效
     */
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public int getResParentId() {
        return resParentId;
    }

    public void setResParentId(int resParentId) {
        this.resParentId = resParentId;
    }
}
