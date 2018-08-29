/**
 * Copyright (C),2015-2018
 * FileNmae: DispensedDrug
 * Author:   Administrator
 * Date:     2018/8/2720:09
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * @author Administrator
 * 发药实体类
 */
public class DispensedDrug {
    /**
     * 病历号
     */
    private int medicalNum;
    /**
     * 药品id
     */
    private int drugId;
    /**
     * 发药数量
     */
    private int TotalQuantity;
    /**
     * 已分发药数量
     */
    private int dispensedQuantity;
    /**
     *未分配药数量
     */
    private int UndispensedQuantity;
    /**
     * 发药时间
     */
    private String dispensedTime;

    public int getMedicalNum() {
        return medicalNum;
    }

    public void setMedicalNum(int medicalNum) {
        this.medicalNum = medicalNum;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public int getDispensedQuantity() {
        return dispensedQuantity;
    }

    public void setDispensedQuantity(int dispensedQuantity) {
        this.dispensedQuantity = dispensedQuantity;
    }

    public int getUndispensedQuantity() {
        return UndispensedQuantity;
    }

    public void setUndispensedQuantity(int undispensedQuantity) {
        UndispensedQuantity = undispensedQuantity;
    }

    public String getDispensedTime() {
        return dispensedTime;
    }

    public void setDispensedTime(String dispensedTime) {
        this.dispensedTime = dispensedTime;
    }
}
