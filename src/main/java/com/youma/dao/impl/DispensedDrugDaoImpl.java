/**
 * Copyright (C),2015-2018
 * FileNmae: DispensedDrugDaoImpl
 * Author:   Administrator
 * Date:     2018/8/3116:31
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.DispensedDrugDao;
import com.youma.util.ConnectionDB;
import com.youma.vo.DispensedDrug;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈泽群
 */
public class DispensedDrugDaoImpl extends BaseDao implements DispensedDrugDao {
    @Override
    public int dispensedDrugAdd(DispensedDrug dispensedDrug) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO his.dispenseddrug\n" +
                "(medicalNum,\n" +
                "drugID,\n" +
                "totalQuantity,\n" +
                "dispensedQuantity,\n" +
                "undispensedQuantity,\n" +
                "dispensedTime)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?)";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dispensedDrug.getMedicalNum());
            ps.setInt(2, dispensedDrug.getDrugId());
            ps.setInt(3, dispensedDrug.getTotalQuantity());
            ps.setInt(4, dispensedDrug.getDispensedQuantity());
            ps.setInt(5, dispensedDrug.getUndispensedQuantity());
            ps.setTimestamp(6, Timestamp.valueOf(dispensedDrug.getDispensedTime()));
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int updateDispensedDrug(DispensedDrug dispensedDrug) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE his.dispenseddrug\n" +
                "SET\n" +
                "medicalNum = ?,\n" +
                "drugID = ?,\n" +
                "totalQuantity = ?,\n" +
                "dispensedQuantity = ?,\n" +
                "undispensedQuantity = ?,\n" +
                "dispensedTime = ?\n" +
                "WHERE medicalNum = ?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dispensedDrug.getMedicalNum());
            ps.setInt(2, dispensedDrug.getDrugId());
            ps.setInt(3, dispensedDrug.getTotalQuantity());
            ps.setInt(4, dispensedDrug.getDispensedQuantity());
            ps.setInt(5, dispensedDrug.getUndispensedQuantity());
            ps.setTimestamp(6, Timestamp.valueOf(dispensedDrug.getDispensedTime()));
            ps.setInt(7, dispensedDrug.getMedicalNum());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int delDispensedDrug(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "DELETE FROM his.dispenseddrug\n" +
                "WHERE medicalNum=?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public List<DispensedDrug> findAllDispensedDrug() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT dispenseddrug.medicalNum,\n" +
                "    dispenseddrug.drugID,\n" +
                "    dispenseddrug.totalQuantity,\n" +
                "    dispenseddrug.dispensedQuantity,\n" +
                "    dispenseddrug.undispensedQuantity,\n" +
                "    dispenseddrug.dispensedTime\n" +
                "FROM his.dispenseddrug";
        List<DispensedDrug> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DispensedDrug dispensedDrug = new DispensedDrug();
                dispensedDrug.setMedicalNum(rs.getInt("medicalNum"));
                dispensedDrug.setDrugId(rs.getInt("drugID"));
                dispensedDrug.setTotalQuantity(rs.getInt("totalQuantity"));
                dispensedDrug.setDispensedQuantity(rs.getInt("dispensedQuantity"));
                dispensedDrug.setUndispensedQuantity(rs.getInt("undispensedQuantity"));
                dispensedDrug.setDispensedTime(sdf.format(rs.getTimestamp("dispensedTime")));
                list.add(dispensedDrug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DispensedDrug findDispensedDrug(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT dispenseddrug.medicalNum,\n" +
                "    dispenseddrug.drugID,\n" +
                "    dispenseddrug.totalQuantity,\n" +
                "    dispenseddrug.dispensedQuantity,\n" +
                "    dispenseddrug.undispensedQuantity,\n" +
                "    dispenseddrug.dispensedTime\n" +
                "FROM his.dispenseddrug\n" +
                "WHERE medicalNum=?";
        DispensedDrug dispensedDrug = new DispensedDrug();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dispensedDrug.setMedicalNum(rs.getInt("medicalNum"));
                dispensedDrug.setDrugId(rs.getInt("drugID"));
                dispensedDrug.setTotalQuantity(rs.getInt("totalQuantity"));
                dispensedDrug.setDispensedQuantity(rs.getInt("dispensedQuantity"));
                dispensedDrug.setUndispensedQuantity(rs.getInt("undispensedQuantity"));
                dispensedDrug.setDispensedTime(sdf.format(rs.getTimestamp("dispensedTime")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dispensedDrug;
    }
}
