/**
 * Copyright (C),2015-2018
 * FileNmae: Register
 * Author:   Administrator
 * Date:     2018/8/2719:05
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * 挂号信息实体类
 *
 * @author Administrator
 */
public class Register {
    /**
     * 病历号
     */
    private int medicalNum;
    /**
     * 挂号姓名
     */
    private String registerName;
    /**
     * 证件类型 1:身份证,2:驾驶证
     */
    private int identifierType;
    /**
     * 证件号
     */
    private String identifierNum;
    /**
     * 社保号
     */
    private String socialSecurityNum;
    /**
     * 联系电话
     */
    private String phoneNum;
    /**
     * 是否自费 0:否,1:是
     */
    private int expenseFlag;
    /**
     * 性别 0:女,1:男
     */
    private int sex;
    /**
     * 年龄
     */
    private int age;
    /**
     * 职业
     */
    private String profession;
    /**
     * 初复诊
     */
    private String czFlag;
    /**
     * 医生id
     */
    private int doctorID;
    /**
     * 状态 0:挂号,1:就诊,2:完成
     */
    private int flag;
    /**
     * 备注
     */
    private String remark;

    public int getMedicalNum() {
        return medicalNum;
    }

    public void setMedicalNum(int medicalNum) {
        this.medicalNum = medicalNum;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public int getIdentifierType() {
        return identifierType;
    }

    /**
     * @param identifierType 1:身份证,2:驾驶证
     */
    public void setIdentifierType(int identifierType) {
        this.identifierType = identifierType;
    }

    /**
     * @return 1:身份证,2:驾驶证
     */
    public String getIdentifierNum() {
        return identifierNum;
    }

    public void setIdentifierNum(String identifierNum) {
        this.identifierNum = identifierNum;
    }

    public String getSocialSecurityNum() {
        return socialSecurityNum;
    }

    public void setSocialSecurityNum(String socialSecurityNum) {
        this.socialSecurityNum = socialSecurityNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getExpenseFlag() {
        return expenseFlag;
    }

    public void setExpenseFlag(int expenseFlag) {
        this.expenseFlag = expenseFlag;
    }

    public String getCzFlag() {
        return czFlag;
    }

    public void setCzFlag(String czFlag) {
        this.czFlag = czFlag;
    }

    /**
     * @return 0:女,1:男
     */
    public int getSex() {
        return sex;
    }

    /**
     * @param sex 0:女,1:男
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * @return 0:挂号,1:就诊,2:完成
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag 0:挂号,1:就诊,2:完成
     */
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
        return "Register{" +
                "medicalNum=" + medicalNum +
                ", registerName='" + registerName + '\'' +
                ", identifierType=" + identifierType +
                ", identifierNum='" + identifierNum + '\'' +
                ", socialSecurityNum='" + socialSecurityNum + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", expenseFlag=" + expenseFlag +
                ", sex=" + sex +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                ", czFlag='" + czFlag + '\'' +
                ", doctorID=" + doctorID +
                ", flag=" + flag +
                ", remark='" + remark + '\'' +
                '}';
    }
}
