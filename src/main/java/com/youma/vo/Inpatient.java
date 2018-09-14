/**
 * Copyright (C),2015-2018
 * FileNmae: Inpatient
 * Author:   Administrator
 * Date:     2018/8/2720:04
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.vo;

import com.youma.util.Czq;

/**
 * 住院信息表
 *
 * @author Administrator
 */
public class Inpatient {
    /**
     * 病历号
     */
    private int medicalNum;
    /**
     * 病人姓名
     */
    private String name;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 医生姓名
     */
    private String doctor;
    /**
     * 科室姓名
     */
    private String depName;
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
    /**
     * 住院状态 0:已住院,1:未结算,2:已结算,3:已退院,4:已出院
     */
    @Czq(name = "住院状态", value = {"已住院","未结算","已结算","已退院", "已出院"})
    private int flag;
    /**
     * 入院时间
     */
    private String inpTime;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getInpTime() {
        return inpTime;
    }

    public void setInpTime(String inpTime) {
        this.inpTime = inpTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

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
