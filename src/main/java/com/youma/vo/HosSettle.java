/**
 * Copyright (C),2015-2018
 * FileNmae: HosSettleDao
 * Author:   Administrator
 * Date:     2018/8/2720:20
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.vo;

/**
 * 住院结算表
 *
 * @author Administrator
 */
public class HosSettle {
    /**
     * id
     */
    private int id;
    /**
     * 病历号
     */
    private int medicalNum;
    /**
     * 病患名字
     */
    private String rName;
    /**
     * 状态(0:未结算,1:已结算)
     */
    private int flag;
    /**
     * 总花费
     */
    private double cost;
    /**
     * 押金
     */
    private double deposit;
    /**
     * 还需缴纳
     */
    private double overplusCost;
    /**
     * 余额
     */
    private double balance;
    /**
     * 结算日期
     */
    private String payDate;
    /**
     * 住院日期
     */
    private String inpDate;
    /**
     * 医疗费用
     */
    private double medicalCost;
    /**
     * 药品费用
     */
    private double drugCost;
    /**
     * 已缴金额
     */
    private double paidCost;
    /**
     * 住院天数
     */
    private int hosDay;

    public int getHosDay() {
        return hosDay;
    }

    public void setHosDay(int hosDay) {
        this.hosDay = hosDay;
    }

    public String getInpDate() {
        return inpDate;
    }

    public void setInpDate(String inpDate) {
        this.inpDate = inpDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicalNum() {
        return medicalNum;
    }

    public void setMedicalNum(int medicalNum) {
        this.medicalNum = medicalNum;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getOverplusCost() {
        return overplusCost;
    }

    public void setOverplusCost(double overplusCost) {
        this.overplusCost = overplusCost;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMedicalCost() {
        return medicalCost;
    }

    public void setMedicalCost(double medicalCost) {
        this.medicalCost = medicalCost;
    }

    public double getDrugCost() {
        return drugCost;
    }

    public void setDrugCost(double drugCost) {
        this.drugCost = drugCost;
    }

    public double getPaidCost() {
        return paidCost;
    }

    public void setPaidCost(double paidCost) {
        this.paidCost = paidCost;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
}
