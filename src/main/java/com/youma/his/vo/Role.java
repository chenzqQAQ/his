/**
 * Copyright (C),2015-2018
 * FileNmae: Role
 * Author:   Administrator
 * Date:     2018/8/2717:59
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * @author Administrator
 * 角色实体类
 */
public class Role {
    /**
     * 角色id
     */
    private int roleID;
    /**
     * 角色编号
     */
    private String roleNum;
    /**
     * 角色名称
     */
    private String roleName;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", roleNum='" + roleNum + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

}
