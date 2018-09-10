/**
 * Copyright (C),2015-2018
 * FileNmae: RegisterDaoImpl
 * Author:   Administrator
 * Date:     2018/8/2816:46
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.RegisterDao;
import com.youma.util.ConnectionDB;
import com.youma.util.Page;
import com.youma.vo.Register;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterDaoImpl extends BaseDao implements RegisterDao {
    @Override
    public int registerAdd(Register register) {
        conn = ConnectionDB.getConnection();
        String sql = "insert into register(medicalNum,registerName,identifierType,identifierNum,socialSecurityNum,phoneNum,expenseFlag,sex,age,profession,czFlag,doctorID,flag,remark)\n" +
                " value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setObject(i++, register.getMedicalNum());
            ps.setObject(i++, register.getRegisterName());
            ps.setObject(i++, register.getIdentifierType());
            ps.setObject(i++, register.getIdentifierNum());
            ps.setObject(i++, register.getSocialSecurityNum());
            ps.setObject(i++, register.getPhoneNum());
            ps.setObject(i++, register.getExpenseFlag());
            ps.setObject(i++, register.getSex());
            ps.setObject(i++, register.getAge());
            ps.setObject(i++, register.getProfession());
            ps.setObject(i++, register.getCzFlag());
            ps.setObject(i++, register.getDoctorID());
            ps.setObject(i++, register.getFlag());
            ps.setObject(i++, register.getRemark());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return col;
    }

    @Override
    public int updateRegister(Register register) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE register SET medicalNum = ?, registerName = ?, identifierType = ?, " +
                "identifierNum = ?, socialSecurityNum = ?, phoneNum =?, expenseFlag = ?, " +
                "sex = ?, age = ?, profession = ?, czFlag = ?, doctorID = ?, flag = ?, " +
                "remark = ? WHERE medicalNum =?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setObject(i++, register.getMedicalNum());
            ps.setObject(i++, register.getRegisterName());
            ps.setObject(i++, register.getIdentifierType());
            ps.setObject(i++, register.getIdentifierNum());
            ps.setObject(i++, register.getSocialSecurityNum());
            ps.setObject(i++, register.getPhoneNum());
            ps.setObject(i++, register.getExpenseFlag());
            ps.setObject(i++, register.getSex());
            ps.setObject(i++, register.getAge());
            ps.setObject(i++, register.getProfession());
            ps.setObject(i++, register.getCzFlag());
            ps.setObject(i++, register.getDoctorID());
            ps.setObject(i++, register.getFlag());
            ps.setObject(i++, register.getRemark());
            ps.setObject(i++, register.getMedicalNum());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int delRegister(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "delete from register\n" +
                "where medicalNum=?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public List<Register> findAllRegister() {
        List<Register> list = new ArrayList<Register>();
        conn = ConnectionDB.getConnection();
        String sql = "SELECT \n" +
                "    medicalNum,\n" +
                "    registerName,\n" +
                "    identifierType,\n" +
                "    identifierNum,\n" +
                "    socialSecurityNum,\n" +
                "    phoneNum,\n" +
                "    expenseFlag,\n" +
                "    sex,\n" +
                "    age,\n" +
                "    profession,\n" +
                "    czFlag,\n" +
                "    doctorID,\n" +
                "    flag,\n" +
                "    remark,\n" +
                "    rtime\n" +
                "FROM\n" +
                "    register\n";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Register register = new Register();
                register.setMedicalNum(rs.getInt("medicalNum"));
                register.setRegisterName(rs.getString("registerName"));
                register.setIdentifierType(rs.getInt("identifierType"));
                register.setIdentifierNum(rs.getString("identifierNum"));
                register.setSocialSecurityNum(rs.getString("socialSecurityNum"));
                register.setPhoneNum(rs.getString("phoneNum"));
                register.setExpenseFlag(rs.getInt("expenseFlag"));
                register.setSex(rs.getInt("sex"));
                register.setAge(rs.getInt("age"));
                register.setProfession(rs.getString("profession"));
                register.setCzFlag(rs.getString("czFlag"));
                register.setDoctorID(rs.getInt("doctorID"));
                register.setFlag(rs.getInt("flag"));
                register.setRemark(rs.getString("remark"));
                String str = sdf.format(rs.getTimestamp("rtime").getTime());
                register.setRtime(str);
                list.add(register);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public Register findRegister(int id) {
        conn = ConnectionDB.getConnection();
        Register register = null;
        String sql = "SELECT \n" +
                "    medicalNum,\n" +
                "    registerName,\n" +
                "    identifierType,\n" +
                "    identifierNum,\n" +
                "    socialSecurityNum,\n" +
                "    phoneNum,\n" +
                "    expenseFlag,\n" +
                "    sex,\n" +
                "    age,\n" +
                "    profession,\n" +
                "    czFlag,\n" +
                "    doctorID,\n" +
                "    flag,\n" +
                "    remark,\n" +
                "    rtime\n" +
                "FROM\n" +
                "    register\n" +
                "WHERE\n" +
                "    medicalNum = ?";
        try {
            register = new Register();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                register.setMedicalNum(rs.getInt("medicalNum"));
                register.setRegisterName(rs.getString("registerName"));
                register.setIdentifierType(rs.getInt("identifierType"));
                register.setIdentifierNum(rs.getString("identifierNum"));
                register.setSocialSecurityNum(rs.getString("socialSecurityNum"));
                register.setPhoneNum(rs.getString("phoneNum"));
                register.setExpenseFlag(rs.getInt("expenseFlag"));
                register.setSex(rs.getInt("sex"));
                register.setAge(rs.getInt("age"));
                register.setProfession(rs.getString("profession"));
                register.setCzFlag(rs.getString("czFlag"));
                register.setDoctorID(rs.getInt("doctorID"));
                register.setFlag(rs.getInt("flag"));
                register.setRemark(rs.getString("remark"));
                String str = sdf.format(rs.getTimestamp("rtime").getTime());
                register.setRtime(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return register;
    }

    @Override
    public List<Register> PageAllRegister(Page page) {
        List<Register> list = new ArrayList<Register>();
        conn = ConnectionDB.getConnection();
        String sql = "SELECT \n" +
                "    medicalNum,\n" +
                "    registerName,\n" +
                "    identifierType,\n" +
                "    identifierNum,\n" +
                "    socialSecurityNum,\n" +
                "    phoneNum,\n" +
                "    expenseFlag,\n" +
                "    sex,\n" +
                "    age,\n" +
                "    profession,\n" +
                "    czFlag,\n" +
                "    doctorID,\n" +
                "    flag,\n" +
                "    remark,\n" +
                "    rtime\n" +
                "FROM\n" +
                "    register\n" +
                "LIMIT ?,?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page.getOffset());
            ps.setInt(2, page.getPageSize());
            System.out.println("查询时的偏移量" + page.getOffset());
            System.out.println("查询时的单页条数" + page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                Register register = new Register();
                register.setMedicalNum(rs.getInt("medicalNum"));
                register.setRegisterName(rs.getString("registerName"));
                register.setIdentifierType(rs.getInt("identifierType"));
                register.setIdentifierNum(rs.getString("identifierNum"));
                register.setSocialSecurityNum(rs.getString("socialSecurityNum"));
                register.setPhoneNum(rs.getString("phoneNum"));
                register.setExpenseFlag(rs.getInt("expenseFlag"));
                register.setSex(rs.getInt("sex"));
                register.setAge(rs.getInt("age"));
                register.setProfession(rs.getString("profession"));
                register.setCzFlag(rs.getString("czFlag"));
                register.setDoctorID(rs.getInt("doctorID"));
                register.setFlag(rs.getInt("flag"));
                register.setRemark(rs.getString("remark"));
                String str = sdf.format(rs.getTimestamp("rtime").getTime());
                register.setRtime(str);
                list.add(register);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int findRegisterCount(String[] args) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT \n" +
                "    COUNT(register.medicalNum)\n" +
                "FROM\n" +
                "    register\n" +
                "        JOIN\n" +
                "    doctor ON register.doctorID = doctor.ID\n" +
                "        JOIN\n" +
                "    department ON department.ID = doctor.depID\n" +
                "where 1=1 ";
        if (null != args[0]) {
            sql += "and doctorName = ? ";
        }
        if (null != args[1]) {
            sql += "and depName = ?";
        }
        System.out.println(sql);
        int col = 0;
        try {
            int index = 1;
            ps = conn.prepareStatement(sql);
            if (null != args[0]) {
                ps.setString(index++, args[0]);
                System.out.println("医生名" + args[0]);
            }
            if (null != args[1]) {
                ps.setString(index++, args[1]);
                System.out.println("科室名" + args[1]);
            }
            System.out.println(index);
            rs = ps.executeQuery();
            if (rs.next()) {

                col = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public List<Register> PageAllRegister(Page page, String[] args) {
        conn = ConnectionDB.getConnection();
        List<Register> list = new ArrayList<Register>();
        String sql = "SELECT \n" +
                "    medicalNum,register.doctorID,rtime,flag\n" +
                "FROM\n" +
                "    register\n" +
                "        JOIN\n" +
                "    doctor ON register.doctorID = doctor.ID\n" +
                "        JOIN\n" +
                "    department ON department.ID = doctor.depID\n" +
                "where 1=1 ";
        if (null != args[0]) {
            sql += "and doctorName = ? ";
        }
        if (null != args[1]) {
            sql += "and depName = ?";
        }
        sql += " Limit ?,?";
        System.out.println(sql);
        try {
            int index = 1;
            ps = conn.prepareStatement(sql);
            if (null != args[0]) {
                ps.setString(index++, args[0]);
                System.out.println("医生名" + args[0]);
            }
            if (null != args[1]) {
                ps.setString(index++, args[1]);
                System.out.println("科室名" + args[1]);
            }
            ps.setInt(index++, page.getOffset());
            ps.setInt(index++, page.getPageSize());
            System.out.println(index);
            rs = ps.executeQuery();
            while (rs.next()) {
                Register register = new Register();
                register.setMedicalNum(rs.getInt(1));
                register.setDoctorID(rs.getInt(2));
                register.setRtime(sdf.format(rs.getTimestamp("rtime").getTime()));
                register.setFlag(rs.getInt("flag"));
                list.add(register);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
