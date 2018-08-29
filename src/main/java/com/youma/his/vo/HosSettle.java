/**
 * Copyright (C),2015-2018
 * FileNmae: HosSettle
 * Author:   Administrator
 * Date:     2018/8/2720:20
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * @author Administrator
 * 住院结算表
 */
public class HosSettle {
    /**
     *id
     */
    private int id;
    /**
     *病历号
     */
    private int medicalNum;
    /**
     *状态
     */
    private int flag;
    /**
     *总花费
     */
    private double cost;
    /**
     *押金
     */
    private double deposit;
    /**
     *还需缴纳
     */
    private double overplusCost;
    /**
     *余额
     */
    private double balance;
    /**
     *医疗费用
     */
    private double medicalCost;
    /**
     *药品费用
     */
    private double drugCost;
    /**
     *已缴金额
     */
    private double paidCost;

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
}
