/**
 * Copyright (C),2015-2018
 * FileNmae: DispensedDrug
 * Author:   Administrator
 * Date:     2018/8/2720:09
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.vo;

/**
 * 发药实体类
 * @author Administrator
 */
public class DispensedDrug {
    /**
     * 主键id
     */
    private int id;
    /**
     * 病历号
     */
    private int medicalNum;
    /**
     * 药品id
     */
    private String drugId;
    /**
     * 药品名称
     */
    private String drugName;

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    /**
     * 病患姓名
     */
    private  String rName;
    /**
     * 负责人名字
     */
    private  String docName;
    /**
     * 发药数量
     */
    private int totalQuantity;
    /**
     * 已分发药数量
     */
    private int dispensedQuantity;
    /**
     *未分配药数量
     */
    private int undispensedQuantity;
    /**
     * 发药时间
     */
    private String dispensedTime;
    /**
     * 药品金额
     */
    private double account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public int getMedicalNum() {
        return medicalNum;
    }

    public void setMedicalNum(int medicalNum) {
        this.medicalNum = medicalNum;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getDispensedQuantity() {
        return dispensedQuantity;
    }

    public void setDispensedQuantity(int dispensedQuantity) {
        this.dispensedQuantity = dispensedQuantity;
    }

    public int getUndispensedQuantity() {
        return undispensedQuantity;
    }

    public void setUndispensedQuantity(int undispensedQuantity) {
        this.undispensedQuantity = undispensedQuantity;
    }

    public String getDispensedTime() {
        return dispensedTime;
    }

    public void setDispensedTime(String dispensedTime) {
        this.dispensedTime = dispensedTime;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }
}
