/**
 * Copyright (C),2015-2018
 * FileNmae: Doctor
 * Author:   Administrator
 * Date:     2018/8/2719:18
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.vo;

/**
 * @author Administrator
 * 医生实体类
 */
public class Doctor {
    /**
     * 姓名
     */
    private int id;
    /**
     * 医生姓名
     */
    private String doctorName;
    /**
     * 证件类型
     */
    private int identifierType;
    /**
     * 证件号
     */
    private String identifierNum;
    /**
     * 手机
     */
    private String phoneNum;
    /**
     * 座机
     */
    private String setaPhoneNum;
    /**
     * 性别
     */
    private int sex;
    /**
     * 年龄
     */
    private int age;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 科室id
     */
    private int depId;

    /**
     * 学历
     */
    private int degree;

    /**
     * 备注
     */
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(int identifierType) {
        this.identifierType = identifierType;
    }

    public String getIdentifierNum() {
        return identifierNum;
    }

    public void setIdentifierNum(String identifierNum) {
        this.identifierNum = identifierNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSetaPhoneNum() {
        return setaPhoneNum;
    }

    public void setSetaPhoneNum(String setaPhoneNum) {
        this.setaPhoneNum = setaPhoneNum;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
