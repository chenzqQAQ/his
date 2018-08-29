/**
 * Copyright (C),2015-2018
 * FileNmae: Inpatient
 * Author:   Administrator
 * Date:     2018/8/2720:04
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * @author Administrator
 * 住院信息表
 */
public class Inpatient {
    /**
     * 病历号
     */
    private int medicalNum;
    /**
     * 护理人
     */
    private String nurse;
    /**
     * 病床号
     */
    private String bedNum;
    /**
     * 缴费押金
     */
    private double deposit;
    /**
     * 病情
     */
    private String illness;

    public int getMedicalNum() {
        return medicalNum;
    }

    public void setMedicalNum(int medicalNum) {
        this.medicalNum = medicalNum;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }
}
