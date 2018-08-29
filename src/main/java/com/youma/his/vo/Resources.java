/**
 * Copyright (C),2015-2018
 * FileNmae: Resources
 * Author:   Administrator
 * Date:     2018/8/2718:02
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * @author Administrator
 * 权限资源实体类
 */
public class Resources {
    /**
     * 权限id
     */
    private int resID;
    /**
     * 资源名臣
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
