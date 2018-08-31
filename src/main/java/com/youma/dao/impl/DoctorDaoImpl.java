/**
 * Copyright (C),2015-2018
 * FileNmae: DoctorDaoImpl
 * Author:   Administrator
 * Date:     2018/8/3114:13
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.DoctorDao;
import com.youma.util.ConnectionDB;
import com.youma.vo.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈泽群
 */
public class DoctorDaoImpl extends BaseDao implements DoctorDao {
    @Override
    public int doctorAdd(Doctor doctor) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO doctor \n" +
                "( ID ,\n" +
                " doctorName ,\n" +
                " identifierType ,\n" +
                " identifierNum ,\n" +
                " phoneNum ,\n" +
                " seatPhoneNum ,\n" +
                " sex ,\n" +
                " age ,\n" +
                " birthday ,\n" +
                " email ,\n" +
                " depID ,\n" +
                " degree ,\n" +
                " remark )\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?);";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, doctor.getId());
            ps.setObject(2, doctor.getDoctorName());
            ps.setObject(3, doctor.getIdentifierType());
            ps.setObject(4, doctor.getIdentifierNum());
            ps.setObject(5, doctor.getPhoneNum());
            ps.setObject(6, doctor.getSetaPhoneNum());
            ps.setObject(7, doctor.getSex());
            ps.setObject(8, doctor.getAge());
            ps.setObject(9, doctor.getBirthday());
            ps.setObject(10, doctor.getEmail());
            ps.setObject(11, doctor.getDepId());
            ps.setObject(12, doctor.getDegree());
            ps.setObject(13, doctor.getRemark());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE  doctor \n" +
                "SET\n" +
                " ID  = ?,\n" +
                " doctorName  = ?,\n" +
                " identifierType  = ?,\n" +
                " identifierNum  = ?,\n" +
                " phoneNum  = ?,\n" +
                " seatPhoneNum  = ?,\n" +
                " sex  = ?,\n" +
                " age  = ?,\n" +
                " birthday  = ?,\n" +
                " email  = ?,\n" +
                " depID  = ?,\n" +
                " degree  = ?,\n" +
                " remark  = ?\n" +
                "WHERE  ID  = ?;";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, doctor.getId());
            ps.setObject(2, doctor.getDoctorName());
            ps.setObject(3, doctor.getIdentifierType());
            ps.setObject(4, doctor.getIdentifierNum());
            ps.setObject(5, doctor.getPhoneNum());
            ps.setObject(6, doctor.getSetaPhoneNum());
            ps.setObject(7, doctor.getSex());
            ps.setObject(8, doctor.getAge());
            ps.setObject(9, doctor.getBirthday());
            ps.setObject(10, doctor.getEmail());
            ps.setObject(11, doctor.getDepId());
            ps.setObject(12, doctor.getDegree());
            ps.setObject(13, doctor.getRemark());
            ps.setObject(14, doctor.getId());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int delDoctor(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "DELETE FROM doctor\n" +
                "WHERE ID=?;";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return col;
    }

    @Override
    public List<Doctor> findAllDoctor() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT \n" +
                "    ID,\n" +
                "    doctorName,\n" +
                "    identifierType,\n" +
                "    identifierNum,\n" +
                "    phoneNum,\n" +
                "    seatPhoneNum,\n" +
                "    sex,\n" +
                "    age,\n" +
                "    birthday,\n" +
                "    email,\n" +
                "    depID,\n" +
                "    degree,\n" +
                "    remark\n" +
                "FROM\n" +
                "    doctor;";
        List<Doctor> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("ID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setIdentifierType(rs.getInt("identifierType"));
                doctor.setIdentifierNum(rs.getString("identifierNum"));
                doctor.setPhoneNum(rs.getString("phoneNum"));
                doctor.setSetaPhoneNum(rs.getString("seatPhoneNum"));
                doctor.setSex(rs.getInt("sex"));
                doctor.setAge(rs.getInt("age"));
                doctor.setBirthday(sdf1.format(rs.getDate("birthday")));
                doctor.setEmail(rs.getString("email"));
                doctor.setDepId(rs.getInt("depID"));
                doctor.setDegree(rs.getInt("degree"));
                doctor.setRemark(rs.getString("remark"));
                list.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Doctor findDoctor(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT \n" +
                "    ID,\n" +
                "    doctorName,\n" +
                "    identifierType,\n" +
                "    identifierNum,\n" +
                "    phoneNum,\n" +
                "    seatPhoneNum,\n" +
                "    sex,\n" +
                "    age,\n" +
                "    birthday,\n" +
                "    email,\n" +
                "    depID,\n" +
                "    degree,\n" +
                "    remark\n" +
                "FROM\n" +
                "    doctor\n" +
                "where ID=?;";
        Doctor doctor = new Doctor();
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                doctor.setId(rs.getInt("ID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setIdentifierType(rs.getInt("identifierType"));
                doctor.setIdentifierNum(rs.getString("identifierNum"));
                doctor.setPhoneNum(rs.getString("phoneNum"));
                doctor.setSetaPhoneNum(rs.getString("seatPhoneNum"));
                doctor.setSex(rs.getInt("sex"));
                doctor.setAge(rs.getInt("age"));
                doctor.setBirthday(sdf1.format(rs.getDate("birthday")));
                doctor.setEmail(rs.getString("email"));
                doctor.setDepId(rs.getInt("depID"));
                doctor.setDegree(rs.getInt("degree"));
                doctor.setRemark(rs.getString("remark"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }
}
