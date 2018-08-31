/**
 * Copyright (C),2015-2018
 * FileNmae: HosSettleDaoImpl
 * Author:   Administrator
 * Date:     2018/8/3117:00
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.HosSettleDao;
import com.youma.util.ConnectionDB;
import com.youma.vo.HosSettle;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈泽群
 */
public class HosSettleDaoImpl extends BaseDao implements HosSettleDao {
    @Override
    public int hosSettleAdd(HosSettle hosSettle) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO his.hossettle\n" +
                "(ID,\n" +
                "medicalNum,\n" +
                "flag,\n" +
                "cost,\n" +
                "deposit,\n" +
                "overplusCost,\n" +
                "balance,\n" +
                "payDate,\n" +
                "medicalCost,\n" +
                "drugCost,\n" +
                "paidCost)\n" +
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
                "?);";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, hosSettle.getId());
            ps.setInt(2, hosSettle.getMedicalNum());
            ps.setInt(3, hosSettle.getFlag());
            ps.setDouble(4, hosSettle.getCost());
            ps.setDouble(5, hosSettle.getDeposit());
            ps.setDouble(6, hosSettle.getOverplusCost());
            ps.setDouble(7, hosSettle.getBalance());
            try {
                ps.setDate(8, new Date(sdf.parse(hosSettle.getPayDate()).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ps.setDouble(9, hosSettle.getMedicalCost());
            ps.setDouble(10, hosSettle.getDrugCost());
            ps.setDouble(11, hosSettle.getPaidCost());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int updateHosSettle(HosSettle hosSettle) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE his.hossettle\n" +
                "SET\n" +
                "ID = ?,\n" +
                "medicalNum = ?,\n" +
                "flag = ?,\n" +
                "cost = ?,\n" +
                "deposit = ?,\n" +
                "overplusCost = ?,\n" +
                "balance = ?,\n" +
                "payDate = ?,\n" +
                "medicalCost = ?,\n" +
                "drugCost = ?,\n" +
                "paidCost = ?\n" +
                "WHERE ID = ?;";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, hosSettle.getId());
            ps.setInt(2, hosSettle.getMedicalNum());
            ps.setInt(3, hosSettle.getFlag());
            ps.setDouble(4, hosSettle.getCost());
            ps.setDouble(5, hosSettle.getDeposit());
            ps.setDouble(6, hosSettle.getOverplusCost());
            ps.setDouble(7, hosSettle.getBalance());
            try {
                ps.setDate(8, new Date(sdf.parse(hosSettle.getPayDate()).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ps.setDouble(9, hosSettle.getMedicalCost());
            ps.setDouble(10, hosSettle.getDrugCost());
            ps.setDouble(11, hosSettle.getPaidCost());
            ps.setInt(12, hosSettle.getId());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int delHosSettle(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "DELETE FROM hossettle\n" +
                "WHERE ID=?;";
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
    public List<HosSettle> findAllHosSettle() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT hossettle.ID,\n" +
                "    hossettle.medicalNum,\n" +
                "    hossettle.flag,\n" +
                "    hossettle.cost,\n" +
                "    hossettle.deposit,\n" +
                "    hossettle.overplusCost,\n" +
                "    hossettle.balance,\n" +
                "    hossettle.payDate,\n" +
                "    hossettle.medicalCost,\n" +
                "    hossettle.drugCost,\n" +
                "    hossettle.paidCost\n" +
                "FROM his.hossettle;";
        List<HosSettle> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HosSettle hosSettle = new HosSettle();
                hosSettle.setId(rs.getInt("id"));
                hosSettle.setMedicalNum(rs.getInt("medicalNum"));
                hosSettle.setFlag(rs.getInt("flag"));
                hosSettle.setCost(rs.getDouble("cost"));
                hosSettle.setDeposit(rs.getDouble("deposit"));
                hosSettle.setOverplusCost(rs.getDouble("overplusCost"));
                hosSettle.setBalance(rs.getDouble("balance"));
                hosSettle.setPayDate(sdf.format(rs.getDate("payDate")));
                hosSettle.setMedicalCost(rs.getDouble("medicalCost"));
                hosSettle.setDrugCost(rs.getDouble("drugCost"));
                hosSettle.setPaidCost(rs.getDouble("paidCost"));
                list.add(hosSettle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HosSettle findHosSettle(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT hossettle.ID,\n" +
                "    hossettle.medicalNum,\n" +
                "    hossettle.flag,\n" +
                "    hossettle.cost,\n" +
                "    hossettle.deposit,\n" +
                "    hossettle.overplusCost,\n" +
                "    hossettle.balance,\n" +
                "    hossettle.payDate,\n" +
                "    hossettle.medicalCost,\n" +
                "    hossettle.drugCost,\n" +
                "    hossettle.paidCost\n" +
                "FROM his.hossettle WHERE ID=?";
        HosSettle hosSettle = new HosSettle();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                hosSettle.setId(rs.getInt("id"));
                hosSettle.setMedicalNum(rs.getInt("medicalNum"));
                hosSettle.setFlag(rs.getInt("flag"));
                hosSettle.setCost(rs.getDouble("cost"));
                hosSettle.setDeposit(rs.getDouble("deposit"));
                hosSettle.setOverplusCost(rs.getDouble("overplusCost"));
                hosSettle.setBalance(rs.getDouble("balance"));
                hosSettle.setPayDate(sdf.format(rs.getDate("payDate")));
                hosSettle.setMedicalCost(rs.getDouble("medicalCost"));
                hosSettle.setDrugCost(rs.getDouble("drugCost"));
                hosSettle.setPaidCost(rs.getDouble("paidCost"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hosSettle;
    }
}
