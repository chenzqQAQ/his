/**
 * Copyright (C),2015-2018
 * FileNmae: Drug
 * Author:   Administrator
 * Date:     2018/8/2719:41
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.vo;

import com.youma.util.Czq;

/**
 * @author Administrator
 * 药品实体类
 */
public class Drug {
    /**
     * 药品编号
     */
    @Czq(name="药品编号",length = 10)
    private String drugID;
    /**
     * 药品图片地址
     */
    //@Czq(name="药品图片地址")
    private String drugUrl;
    /**
     * 进价
     */
    @Czq(name="进价")
    private double purchasePrice;
    /**
     * 售价
     */
    @Czq(name="售价")
    private double sellingPrice;
    /**
     * 药品名字
     */
    @Czq(name="药品名字",length = 5)
    private String drugName;
    /**
     * 药品类型0"处方", 1"中药", 2"西药", 3"非处方"
     */
    @Czq(name="药品类型",value = {"处方","中药","西药","非处方"})
    private int drugType;
    /**
     * 药品简单描述
     */
    @Czq(name="药品简单描述",length = 10)
    private String description;
    /**
     * 生产日期
     */
    @Czq(name="生产日期",length = 10)
    private String productionDate;
    /**
     * 过期日期
     */
    @Czq(name="过期日期",length = 10)
    private String overdueDate;
    /**
     * 保质期
     */
    @Czq(name="保质期")
    private int qualityLife;
    /**
     * 药品详细说明
     */
    @Czq(name="药品详细说明",length = 10)
    private String detailedDes;
    /**
     * 生产厂商
     */
    @Czq(name="生产厂商",length = 5)
    private String manufacturer;
    /**
     * 服用说明
     */
    @Czq(name="服用说明",length = 5)
    private String takingDes;
    /**
     * 总的进货量
     */
    @Czq(name="总的进货量")
    private int totalVolume;
    /**
     * 库存
     */
    @Czq(name="库存")
    private int inventory;
    /**
     * 状态0"销售中", 1"售罄", 2"进货"
     */
    @Czq(name="状态",value = {"销售中","售罄","进货"})
    private int flag;
    /**
     * 备注
     */
    @Czq(name="备注",length = 10)
    private String remark;

    public Drug(String drugID, String drugUrl, double pruchasePrice, double sellingPrice, String drugName, int drugType, String description, String productionDate, String overdueDate, int qualityLife, String detailedDes, String manufacturer, String takingDes, int totalVolume, int flag, String remark) {
        this.drugID = drugID;
        this.drugUrl = drugUrl;
        this.purchasePrice = pruchasePrice;
        this.sellingPrice = sellingPrice;
        this.drugName = drugName;
        this.drugType = drugType;
        this.description = description;
        this.productionDate = productionDate;
        this.overdueDate = overdueDate;
        this.qualityLife = qualityLife;
        this.detailedDes = detailedDes;
        this.manufacturer = manufacturer;
        this.takingDes = takingDes;
        this.totalVolume = totalVolume;
        this.flag = flag;
        this.remark = remark;
    }

    public Drug() {
    }

    public String getDrugID() {
        return drugID;
    }

    public void setDrugID(String drugID) {
        this.drugID = drugID;
    }

    public String getDrugUrl() {
        return drugUrl;
    }

    public void setDrugUrl(String drugUrl) {
        this.drugUrl = drugUrl;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getDrugType() {
        return drugType;
    }

    public void setDrugType(int drugType) {
        this.drugType = drugType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(String overdueDate) {
        this.overdueDate = overdueDate;
    }

    public int getQualityLife() {
        return qualityLife;
    }

    public void setQualityLife(int qualityLife) {
        this.qualityLife = qualityLife;
    }

    public String getDetailedDes() {
        return detailedDes;
    }

    public void setDetailedDes(String detailedDes) {
        this.detailedDes = detailedDes;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getTakingDes() {
        return takingDes;
    }

    public void setTakingDes(String takingDes) {
        this.takingDes = takingDes;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugID='" + drugID + '\'' +
                ", drugUrl='" + drugUrl + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", sellingPrice=" + sellingPrice +
                ", drugName='" + drugName + '\'' +
                ", drugType=" + drugType +
                ", description=" + description +
                ", productionDate='" + productionDate + '\'' +
                ", overdueDate='" + overdueDate + '\'' +
                ", qualityLife=" + qualityLife +
                ", detailedDes=" + detailedDes +
                ", manufacturer='" + manufacturer + '\'' +
                ", takingDes='" + takingDes + '\'' +
                ", totalVolume=" + totalVolume +
                ", inventory=" + inventory +
                ", flag=" + flag +
                ", remark='" + remark + '\'' +
                '}';
    }
}
