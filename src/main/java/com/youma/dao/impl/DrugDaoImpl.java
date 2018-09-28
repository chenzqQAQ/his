/**
 * Copyright (C),2015-2018
 * FileNmae: DrugDaoImpl
 * Author:   Administrator
 * Date:     2018/8/2910:21
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.DrugDao;
import com.youma.util.ConnectionDB;
import com.youma.util.Page;
import com.youma.vo.Drug;

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
        } finally {
            closeAll();
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
        } finally {
            closeAll();
        }

        return col;
    }

    @Override
    public int delDrug(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "delete from drug where drugID = ?";
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
    public List<Drug> findAllDrug() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT drug.drugID , drugUrl , purchasePrice , sellingPrice , drugName , \n" +
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
        } finally {
            closeAll();
        }

        return list;
    }

    @Override
    public Drug findDrug(String id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT drugID , drugUrl , purchasePrice , sellingPrice , drugName , \n" +
                "drugType , description , productionDate , overdueDate , qualityLife , \n" +
                "detailedDes , manufacturer , takingDes , totalVolume , inventory , \n" +
                "flag , remark \n" +
                "FROM drug where drugID=?";
        Drug drug = new Drug();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
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
            else {
                drug=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return drug;
    }

    @Override
    public List<Drug> findNameDrug(Drug drug1) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT drug.drugID , drugUrl , purchasePrice , sellingPrice , drugName , \n" +
                "drugType , description , productionDate , overdueDate , qualityLife , \n" +
                "detailedDes , manufacturer , takingDes , totalVolume , inventory , \n" +
                "flag , remark \n" +
                "FROM drug\n" +
                "WHERE drugType=? and drugName=?";
        List<Drug> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, drug1.getDrugType());
            ps.setString(2, drug1.getDrugName());
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
        } finally {
            closeAll();
        }

        return list;
    }

    @Override
    public List<Drug> findTypeDrug(int type) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT drug.drugID , drugUrl , purchasePrice , sellingPrice , drugName , \n" +
                "drugType , description , productionDate , overdueDate , qualityLife , \n" +
                "detailedDes , manufacturer , takingDes , totalVolume , inventory , \n" +
                "flag , remark \n" +
                "FROM drug WHERE drugType=?";
        List<Drug> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, type);
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
        } finally {
            closeAll();
        }

        return list;
    }

    @Override
    public int drugCount(Drug drug) {
        conn = ConnectionDB.getConnection();
        String sql = "select count(drug.drugID) from drug where 1=1";
        if (drug.getDrugName() != null && !"".equals(drug.getDrugName())) {
            sql += " and drugName=?";
        }
        if (drug.getDrugType() != 99) {
            sql += " and drugType=?";
        }
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (drug.getDrugName() != null && !"".equals(drug.getDrugName())) {
                ps.setString(index++, drug.getDrugName());
            }
            if (drug.getDrugType() != 99) {
                ps.setInt(index++, drug.getDrugType());
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
    public List<Drug> allDrug(Drug drug1, Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT drug.drugID , drugUrl , purchasePrice , sellingPrice , drugName , \n" +
                "drugType , description , productionDate , overdueDate , qualityLife , \n" +
                "detailedDes , manufacturer , takingDes , totalVolume , inventory , \n" +
                "flag , remark \n" +
                "FROM drug\n" +
                "WHERE 1=1";
        if (drug1.getDrugName() != null && !"".equals(drug1.getDrugName())) {
            sql += " and drugName=?";
        }
        if (drug1.getDrugType() != 99) {
            sql += " and drugType=?";
        }
        sql += " limit ?,?";
        List<Drug> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (drug1.getDrugName() != null && !"".equals(drug1.getDrugName())) {
                ps.setString(index++, drug1.getDrugName());
            }
            if (drug1.getDrugType() != 99) {
                ps.setInt(index++, drug1.getDrugType());
            }
            ps.setInt(index++, page.getOffset());
            ps.setInt(index++, page.getPageSize());
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
        } finally {
            closeAll();
        }

        return list;
    }
}
