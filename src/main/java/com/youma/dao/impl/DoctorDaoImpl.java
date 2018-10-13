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
import com.youma.vo.Register;

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
                " remark,docDate)\n" +
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
                "?,?);";
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
            ps.setDate(13, new java.sql.Date(new java.util.Date().getTime()));
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
                "    remark,depName,docDate\n" +
                "FROM\n" +
                "    doctor join department on doctor.depID=department.ID;";
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
                doctor.setDepName(rs.getString("depName"));
                if (rs.getDate("birthday") == null) {
                    doctor.setBirthday("null");
                } else {

                    doctor.setBirthday(sdf1.format(rs.getDate("birthday")));
                }
                doctor.setEmail(rs.getString("email"));
                doctor.setDepId(rs.getInt("depID"));
                doctor.setDegree(rs.getInt("degree"));
                doctor.setRemark(rs.getString("remark"));
                doctor.setDocDate(sdf1.format(rs.getDate("docDate")));
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
        } finally {
            closeAll();
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
                "    remark,docDate\n" +
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
                if (rs.getDate("docDate") == null) {
                    doctor.setDocDate("未知");
                } else {

                    doctor.setDocDate(sdf1.format(rs.getDate("docDate")));
                }
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
                "    remark,docDate\n" +
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
                if (rs.getDate("docDate") == null) {
                    doctor.setDocDate("未知");
                } else {

                    doctor.setDocDate(sdf1.format(rs.getDate("docDate")));
                }
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
    public int findDocName(String name) {
        conn = ConnectionDB.getConnection();
        String sql = "select doctor.ID from doctor where doctorName=?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            rs.last();
            col = rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int allDoctorCount(String[] args) {
        conn = ConnectionDB.getConnection();
        String sql = "select count(doctor.ID) from  doctor join department\n" +
                "on doctor.depID=department.ID where 1=1\n";
        int col = 0;
        if (args[0] != null && !("").equals(args[0])) {
            sql += "and doctor.ID=?\n";
        }
        if (args[1] != null && !("").equals(args[1])) {
            sql += "and doctorName=?\n";
        }
        if (args[2] != null && !("").equals(args[2])) {
            sql += "and depName=?\n";
        }
        try {
            int index = 1;
            ps = conn.prepareStatement(sql);
            if (args[0] != null && !("").equals(args[0])) {
                ps.setInt(index++, Integer.parseInt(args[0]));
            }
            if (args[1] != null && !("").equals(args[1])) {
                ps.setString(index++, args[1]);
            }
            if (args[2] != null && !("").equals(args[2])) {
                ps.setString(index++, args[2]);
            }
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
    public List<Doctor> findAllDoctor(String[] args, Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT \n" +
                "    doctor.ID,\n" +
                "    doctorName,\n" +
                "    depID,\n" +
                "docDate\n" +
                "FROM\n" +
                "    doctor join department on doctor.depID=department.ID where 1=1 \n";
        if (args[0] != null && !("").equals(args[0])) {
            sql += " and doctor.ID=?\n";
        }
        if (args[1] != null && !("").equals(args[1])) {
            sql += " and doctorName=?\n";
        }
        if (args[2] != null && !("").equals(args[2])) {
            sql += " and depName=?\n";
        }
        sql += " limit ?,?";
        System.out.println(sql);
        List<Doctor> list = new ArrayList<>();
        try {
            int index = 1;
            ps = conn.prepareStatement(sql);
            if (args[0] != null && !("").equals(args[0])) {
                ps.setInt(index++, Integer.parseInt(args[0]));
            }
            if (args[1] != null && !("").equals(args[1])) {
                ps.setString(index++, args[1]);
            }
            if (args[2] != null && !("").equals(args[2])) {
                ps.setString(index++, args[2]);
            }
            ps.setInt(index++, page.getOffset());
            ps.setInt(index++, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt(1));
                doctor.setDoctorName(rs.getString(2));
                doctor.setDepId(rs.getInt(3));
                if (rs.getDate(4) != null) {
                    doctor.setDocDate(sdf1.format(rs.getDate(4)));
                } else {
                    doctor.setDocDate("未知");
                }
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
    public List<Register> findAllReg(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "select register.medicalNum,registerName from register left join inpatient on register.medicalNum=inpatient.medicalNum\n" +
                "where doctorID=? and register.flag=0 and isnull(inptime)";
        List<Register> list = new ArrayList<Register>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Register register = new Register();
                register.setMedicalNum(rs.getInt("medicalNum"));
                register.setRegisterName(rs.getString("registerName"));
                list.add(register);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
