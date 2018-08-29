/**
 * Copyright (C),2015-2018
 * FileNmae: Users
 * Author:   Administrator
 * Date:     2018/8/2717:50
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * @author Administrator
 * 用户实体类
 */
public class Users {
    /**
     * 用户id
     */
    private int userID;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 修改时间
     */
    private String modifyTime;

    public Users() {
    }

    public Users(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Users(String userName, String userPassword, String modifyTime) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.modifyTime = modifyTime;
    }

    public Users(int userID, String userName, String userPassword, String modifyTime) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.modifyTime = modifyTime;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
