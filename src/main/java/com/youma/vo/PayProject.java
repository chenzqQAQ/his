/**
 * Copyright (C),2015-2018
 * FileNmae: PayProject
 * Author:   Administrator
 * Date:     2018/8/2720:07
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.vo;

/**
 * 收费项目表
 * @author Administrator
 */
public class PayProject {
    /**
     *id
     */
    private int id;
    /**
     *收费项目名字
     */
    private String projectName;
    /**
     *应收金额
     */
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
