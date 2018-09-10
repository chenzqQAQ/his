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
import com.youma.util.Page;
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
                "(" +
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
                "(" +
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
            // ps.setObject(1, doctor.getId());
            ps.setObject(1, doctor.getDoctorName());
            ps.setObject(2, doctor.getIdentifierType());
            ps.setObject(3, doctor.getIdentifierNum());
            ps.setObject(4, doctor.getPhoneNum());
            ps.setObject(5, doctor.getSetaPhoneNum());
            ps.setObject(6, doctor.getSex());
            ps.setObject(7, doctor.getAge());
            ps.setObject(8, doctor.getBirthday());
            ps.setObject(9, doctor.getEmail());
            ps.setObject(10, doctor.getDepId());
            ps.setObject(11, doctor.getDegree());
            ps.setObject(12, doctor.getRemark());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
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
        } finally {
            closeAll();
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
        } finally {
            closeAll();
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
                if (rs.getDate("birthday") == null) {
                    doctor.setBirthday("null");
                } else {

                    doctor.setBirthday(sdf1.format(rs.getDate("birthday")));
                }
                doctor.setEmail(rs.getString("email"));
                doctor.setDepId(rs.getInt("depID"));
                doctor.setDegree(rs.getInt("degree"));
                doctor.setRemark(rs.getString("remark"));
                list.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
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
                if (rs.getDate("birthday") == null) {
                    doctor.setBirthday("未知");
                } else {

                    doctor.setBirthday(sdf1.format(rs.getDate("birthday")));
                }
                doctor.setEmail(rs.getString("email"));
                doctor.setDepId(rs.getInt("depID"));
                doctor.setDegree(rs.getInt("degree"));
                doctor.setRemark(rs.getString("remark"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return doctor;
    }

    @Override
    public List<Doctor> findDoctorByDep(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT \n" +
                "    ID,\n" +
                "    doctorName\n" +
                "FROM \n" +
                "    doctor where depID=?";
        List<Doctor> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            System.out.println(id);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("ID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                list.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int allDoctorCount() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT count(ID)\n" +
                "FROM\n" +
                "    doctor;";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                col = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return col;
    }

    @Override
    public List<Doctor> findAllDoctor(Page page) {
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
                "limit ?,?";
        List<Doctor> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page.getOffset());
            ps.setInt(2, page.getPageSize());
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
                if (rs.getDate("birthday") == null) {
                    doctor.setBirthday("未知");
                } else {
                    doctor.setBirthday(sdf1.format(rs.getDate("birthday")));
                }
                doctor.setEmail(rs.getString("email"));
                doctor.setDepId(rs.getInt("depID"));
                doctor.setDegree(rs.getInt("degree"));
                doctor.setRemark(rs.getString("remark"));
                list.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int allDoctorCount(String depName) {
        conn = ConnectionDB.getConnection();
        String sql = "select count(doctor.ID) from  doctor join department\n" +
                "on doctor.depID=department.ID where department.depName=?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, depName);
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
    public List<Doctor> findAllDoctor(String depName, Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT \n" +
                "    doctor.ID,\n" +
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
                "    doctor join department on doctor.depID=department.ID where department.depName=?\n" +
                "limit ?,?";
        List<Doctor> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, depName);
            ps.setInt(2, page.getOffset());
            ps.setInt(3, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("doctor.ID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setIdentifierType(rs.getInt("identifierType"));
                doctor.setIdentifierNum(rs.getString("identifierNum"));
                doctor.setPhoneNum(rs.getString("phoneNum"));
                doctor.setSetaPhoneNum(rs.getString("seatPhoneNum"));
                doctor.setSex(rs.getInt("sex"));
                doctor.setAge(rs.getInt("age"));
                if (rs.getDate("birthday") == null) {
                    doctor.setBirthday("未知");
                } else {
                    doctor.setBirthday(sdf1.format(rs.getDate("birthday")));
                }
                doctor.setEmail(rs.getString("email"));
                doctor.setDepId(rs.getInt("depID"));
                doctor.setDegree(rs.getInt("degree"));
                doctor.setRemark(rs.getString("remark"));
                list.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }
}
