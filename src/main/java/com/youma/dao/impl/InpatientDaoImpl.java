/**
 * Copyright (C),2015-2018
 * FileNmae: InpatientDaoImpl
 * Author:   Administrator
 * Date:     2018/8/3117:25
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.InpatientDao;
import com.youma.util.ConnectionDB;
import com.youma.util.Page;
import com.youma.vo.Inpatient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈泽群
 */
public class InpatientDaoImpl extends BaseDao implements InpatientDao {
    @Override
    public int inpatientAdd(Inpatient inpatient) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO his.inpatient\n" +
                "(medicalNum,\n" +
                "nurse,\n" +
                "bedNum,\n" +
                "deposit,\n" +
                "illness)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?);";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, inpatient.getMedicalNum());
            ps.setString(2, inpatient.getNurse());
            ps.setString(3, inpatient.getBedNum());
            ps.setDouble(4, inpatient.getDeposit());
            ps.setString(5, inpatient.getIllness());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int updateInpatient(Inpatient inpatient) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE his.inpatient\n" +
                "SET\n" +
                "medicalNum = ?,\n" +
                "nurse = ?,\n" +
                "bedNum = ?,\n" +
                "deposit = ?,\n" +
                "illness = ?\n" +
                "WHERE medicalNum = ?;";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, inpatient.getMedicalNum());
            ps.setString(2, inpatient.getNurse());
            ps.setString(3, inpatient.getBedNum());
            ps.setDouble(4, inpatient.getDeposit());
            ps.setString(5, inpatient.getIllness());
            ps.setInt(6, inpatient.getMedicalNum());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int delInpatient(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "DELETE FROM his.inpatient\n" +
                "WHERE medicalNum=?;";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public List<Inpatient> findAllInpatient() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT inpatient.medicalNum,\n" +
                "    inpatient.nurse,\n" +
                "    inpatient.bedNum,\n" +
                "    inpatient.deposit,\n" +
                "    inpatient.illness,inpatient.flag,inptime,registerName,register.phoneNum as 'a',doctorName,depName\n" +
                "FROM his.inpatient join register on inpatient.medicalNum=register.medicalNum\n" +
                "join doctor on doctorID=ID join department on doctor.depID=department.ID";
        List<Inpatient> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Inpatient inpatient = new Inpatient();
                inpatient.setMedicalNum(rs.getInt("medicalNum"));
                inpatient.setNurse(rs.getString("nurse"));
                inpatient.setBedNum(rs.getString("bedNum"));
                inpatient.setDeposit(rs.getDouble("deposit"));
                inpatient.setIllness(rs.getString("illness"));
                inpatient.setFlag(rs.getInt("flag"));
                inpatient.setInpTime(sdf.format(rs.getTimestamp("inptime")));
                inpatient.setName(rs.getString("registerName"));
                inpatient.setPhone(rs.getString("a"));
                inpatient.setDoctor(rs.getString("doctorName"));
                inpatient.setDepName(rs.getString("depName"));
                list.add(inpatient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public Inpatient findInpatient(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT inpatient.medicalNum,\n" +
                "    inpatient.nurse,\n" +
                "    inpatient.bedNum,\n" +
                "    inpatient.deposit,\n" +
                "    inpatient.illness\n" +
                "FROM his.inpatient\n" +
                "WHERE medicalNum=?";
        Inpatient inpatient = new Inpatient();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                inpatient.setMedicalNum(rs.getInt("medicalNum"));
                inpatient.setNurse(rs.getString("nurse"));
                inpatient.setBedNum(rs.getString("bedNum"));
                inpatient.setDeposit(rs.getDouble("deposit"));
                inpatient.setIllness(rs.getString("illness"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return inpatient;
    }

    @Override
    public int inpCount() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT count(inpatient.medicalNum)\n" +
                "FROM his.inpatient\n";
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
    public List<Inpatient> findInp(Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT inpatient.medicalNum,\n" +
                "    inpatient.nurse,\n" +
                "    inpatient.bedNum,\n" +
                "    inpatient.deposit,\n" +
                "    inpatient.illness,inpatient.flag,inptime,registerName,register.phoneNum as 'a',doctorName,depName\n" +
                "FROM his.inpatient join register on inpatient.medicalNum=register.medicalNum\n" +
                "join doctor on doctorID=ID join department on doctor.depID=department.ID";
        sql += " limit ?,?";
        List<Inpatient> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page.getOffset());
            ps.setInt(2, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                Inpatient inpatient = new Inpatient();
                inpatient.setMedicalNum(rs.getInt("medicalNum"));
                inpatient.setNurse(rs.getString("nurse"));
                inpatient.setBedNum(rs.getString("bedNum"));
                inpatient.setDeposit(rs.getDouble("deposit"));
                inpatient.setIllness(rs.getString("illness"));
                inpatient.setFlag(rs.getInt("flag"));
                inpatient.setInpTime(sdf.format(rs.getTimestamp("inptime")));
                inpatient.setName(rs.getString("registerName"));
                inpatient.setPhone(rs.getString("a"));
                inpatient.setDoctor(rs.getString("doctorName"));
                inpatient.setDepName(rs.getString("depName"));
                list.add(inpatient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }
}
