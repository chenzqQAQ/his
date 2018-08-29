/**
 * Copyright (C),2015-2018
 * FileNmae: Department
 * Author:   Administrator
 * Date:     2018/8/2719:29
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * @author Administrator
 * 科室实体类
 */
public class Department {

    private int id;
    private String depNum;
    private String depName;

    public Department(int id, String depNum, String depName) {
        this.id = id;
        this.depNum = depNum;
        this.depName = depName;
    }

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepNum() {
        return depNum;
    }

    public void setDepNum(String depNum) {
        this.depNum = depNum;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}

