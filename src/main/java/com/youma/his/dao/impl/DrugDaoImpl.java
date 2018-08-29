/**
 * Copyright (C),2015-2018
 * FileNmae: DrugDaoImpl
 * Author:   Administrator
 * Date:     2018/8/2910:21
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.dao.impl;

import com.youma.his.dao.DrugDao;
import com.youma.his.util.ConnectionDB;
import com.youma.his.vo.Drug;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈泽群
 */
public class DrugDaoImpl extends BaseDao implements DrugDao {
    @Override
    public int drugAdd(Drug drug) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO drug\n" +
                "(drugID,drugUrl,purchasePrice,sellingPrice,drugName,\n" +
                "drugType,description,productionDate,overdueDate,\n" +
                "qualityLife,detailedDes,manufacturer,takingDes,\n" +
                "totalVolume,inventory,flag,remark)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, drug.getDrugID());
            ps.setObject(2, drug.getDrugUrl());
            ps.setObject(3, drug.getPurchasePrice());
            ps.setObject(4, drug.getSellingPrice());
            ps.setObject(5, drug.getDrugName());
            ps.setObject(6, drug.getDrugType());
            ps.setObject(7, drug.getDescription());
            ps.setObject(8, sdf1.parse(drug.getProductionDate()));
            ps.setObject(9, sdf1.parse(drug.getOverdueDate()));
            ps.setObject(10, drug.getQualityLife());
            ps.setObject(11, drug.getDetailedDes());
            ps.setObject(12, drug.getManufacturer());
            ps.setObject(13, drug.getTakingDes());
            ps.setObject(14, drug.getTotalVolume());
            ps.setObject(15, drug.getInventory());
            ps.setObject(16, drug.getFlag());
            ps.setObject(17, drug.getRemark());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int updateDrug(Drug drug) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE  drug \n" +
                "SET drugID  = ?,\n" +
                " drugUrl  = ?,\n" +
                " purchasePrice  = ?,\n" +
                " sellingPrice  = ?,\n" +
                " drugName  = ?,\n" +
                " drugType  = ?,\n" +
                " description  = ?,\n" +
                " productionDate  = ?,\n" +
                " overdueDate  = ?,\n" +
                " qualityLife  = ?,\n" +
                " detailedDes  = ?,\n" +
                " manufacturer  = ?,\n" +
                " takingDes  = ?,\n" +
                " totalVolume  = ?,\n" +
                " inventory  = ?,\n" +
                " flag  = ?,\n" +
                " remark  = ?\n" +
                "WHERE  drugID  = ?;";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, drug.getDrugID());
            ps.setObject(2, drug.getDrugUrl());
            ps.setObject(3, drug.getPurchasePrice());
            ps.setObject(4, drug.getSellingPrice());
            ps.setObject(5, drug.getDrugName());
            ps.setObject(6, drug.getDrugType());
            ps.setObject(7, drug.getDescription());
            ps.setObject(8, sdf1.parse(drug.getProductionDate()));
            ps.setObject(9, sdf1.parse(drug.getOverdueDate()));
            ps.setObject(10, drug.getQualityLife());
            ps.setObject(11, drug.getDetailedDes());
            ps.setObject(12, drug.getManufacturer());
            ps.setObject(13, drug.getTakingDes());
            ps.setObject(14, drug.getTotalVolume());
            ps.setObject(15, drug.getInventory());
            ps.setObject(16, drug.getFlag());
            ps.setObject(17, drug.getRemark());
            ps.setObject(18, drug.getDrugID());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return col;
    }

    @Override
    public int delDrug(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "delete from drug where drugID = " + id;
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public List<Drug> findAllDrug() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT drugID , drugUrl , purchasePrice , sellingPrice , drugName , \n" +
                "drugType , description , productionDate , overdueDate , qualityLife , \n" +
                "detailedDes , manufacturer , takingDes , totalVolume , inventory , \n" +
                "flag , remark \n" +
                "FROM drug";
        List<Drug> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Drug drug = new Drug();
                int i = 1;
                drug.setDrugID(rs.getString(i++));
                drug.setDrugUrl(rs.getString(i++));
                drug.setPurchasePrice(rs.getDouble(i++));
                drug.setSellingPrice(rs.getDouble(i++));
                drug.setDrugName(rs.getString(i++));
                drug.setDrugType(rs.getInt(i++));
                drug.setDescription(rs.getString(i++));
                drug.setProductionDate(sdf1.format(rs.getDate(i++)));
                drug.setOverdueDate(sdf1.format(rs.getDate(i++)));
                drug.setQualityLife(rs.getInt(i++));
                drug.setDetailedDes(rs.getString(i++));
                drug.setManufacturer(rs.getString(i++));
                drug.setTakingDes(rs.getString(i++));
                drug.setTotalVolume(rs.getInt(i++));
                drug.setInventory(rs.getInt(i++));
                drug.setFlag(rs.getInt(i++));
                drug.setRemark(rs.getString(i++));
                list.add(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Drug findDrug(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT drugID , drugUrl , purchasePrice , sellingPrice , drugName , \n" +
                "drugType , description , productionDate , overdueDate , qualityLife , \n" +
                "detailedDes , manufacturer , takingDes , totalVolume , inventory , \n" +
                "flag , remark \n" +
                "FROM drug where drugID=" + id;
        Drug drug = new Drug();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int i = 1;
                drug.setDrugID(rs.getString(i++));
                drug.setDrugUrl(rs.getString(i++));
                drug.setPurchasePrice(rs.getDouble(i++));
                drug.setSellingPrice(rs.getDouble(i++));
                drug.setDrugName(rs.getString(i++));
                drug.setDrugType(rs.getInt(i++));
                drug.setDescription(rs.getString(i++));
                drug.setProductionDate(sdf1.format(rs.getDate(i++)));
                drug.setOverdueDate(sdf1.format(rs.getDate(i++)));
                drug.setQualityLife(rs.getInt(i++));
                drug.setDetailedDes(rs.getString(i++));
                drug.setManufacturer(rs.getString(i++));
                drug.setTakingDes(rs.getString(i++));
                drug.setTotalVolume(rs.getInt(i++));
                drug.setInventory(rs.getInt(i++));
                drug.setFlag(rs.getInt(i++));
                drug.setRemark(rs.getString(i++));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drug;
    }
}
