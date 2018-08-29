/**
 * Copyright (C),2015-2018
 * FileNmae: PayManager
 * Author:   Administrator
 * Date:     2018/8/2720:17
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * @author Administrator
 * 收费管理实体类
 */
public class PayManager {
    /**
     * id
     */
    private int id;
    /**
     *病历号
     */
    private int medicalNum;
    /**
     *收费项目id
     */
    private int payId;
    /**
     *收费金额
     */
    private double chargeAmount;
    /**
     *收费日期
     */
    private String payDate;

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

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
}
