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
import com.youma.util.Page;
import com.youma.vo.DispensedDrug;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
                "totalQuantity)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?)";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dispensedDrug.getMedicalNum());
            ps.setString(2, dispensedDrug.getDrugId());
            ps.setInt(3, dispensedDrug.getTotalQuantity());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
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
            ps.setString(2, dispensedDrug.getDrugId());
            ps.setInt(3, dispensedDrug.getTotalQuantity());
            ps.setInt(4, dispensedDrug.getDispensedQuantity());
            ps.setInt(5, dispensedDrug.getUndispensedQuantity());
            ps.setTimestamp(6, Timestamp.valueOf(dispensedDrug.getDispensedTime()));
            ps.setInt(7, dispensedDrug.getMedicalNum());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
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
        } finally {
            closeAll();
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
                dispensedDrug.setDrugId(rs.getString("drugID"));
                dispensedDrug.setTotalQuantity(rs.getInt("totalQuantity"));
                dispensedDrug.setDispensedQuantity(rs.getInt("dispensedQuantity"));
                dispensedDrug.setUndispensedQuantity(rs.getInt("undispensedQuantity"));
                dispensedDrug.setDispensedTime(sdf.format(rs.getTimestamp("dispensedTime")));
                list.add(dispensedDrug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public List<DispensedDrug> findDispensedDrug(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT dispenseddrug.medicalNum," +
                "drug.drugID,\n" +
                "    drug.drugName,\n" +
                "    dispenseddrug.totalQuantity,\n" +
                "    dispenseddrug.dispensedQuantity,\n" +
                "    dispenseddrug.undispensedQuantity," +
                "drug.sellingPrice*dispenseddrug.totalQuantity as k,drug.sellingPrice,\n" +
                "registerName,dispensedTime,payDate\n" +
                "FROM his.dispenseddrug join drug on drug.drugID=dispenseddrug.drugID " +
                "join register on register.medicalNum=dispenseddrug.medicalNum\n" +
                "WHERE dispenseddrug.medicalNum=?";
        List<DispensedDrug> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                DispensedDrug dispensedDrug = new DispensedDrug();
                dispensedDrug.setMedicalNum(rs.getInt("medicalNum"));
                dispensedDrug.setDrugName(rs.getString("drugName"));
                dispensedDrug.setrName(rs.getString("registerName"));
                dispensedDrug.setTotalQuantity(rs.getInt("totalQuantity"));
                dispensedDrug.setDispensedQuantity(rs.getInt("dispensedQuantity"));
                dispensedDrug.setUndispensedQuantity(rs.getInt("undispensedQuantity"));
                dispensedDrug.setDrugId(rs.getString("drugID"));
                dispensedDrug.setAccount(rs.getDouble("k"));
                dispensedDrug.setSellPrice(rs.getDouble("sellingPrice"));
                dispensedDrug.setDispensedTime(sdf.format(rs.getTimestamp("dispensedTime")));
                if(rs.getTimestamp("payDate")!=null)
                {
                    dispensedDrug.setPayTime(sdf.format(rs.getTimestamp("payDate")));
                }
                else{
                    dispensedDrug.setPayTime("未付款");
                }
                list.add(dispensedDrug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int dispensedDrugAdd(int[] id, DispensedDrug d) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO his.dispenseddrug\n" +
                "(medicalNum,\n" +
                "drugID,\n" +
                "totalQuantity,undispensedQuantity)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,?)";
        for (int i = 1; i < id.length; i++) {
            sql += " ,(?,?,?,?) ";
        }
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            for (int i = 0; i < id.length; i++) {
                ps.setInt(index++, id[i]);
                ps.setString(index++, d.getDrugId());
                ps.setInt(index++, d.getTotalQuantity());
                ps.setInt(index++, d.getTotalQuantity());
            }
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int disCount() {
        conn = ConnectionDB.getConnection();
        String sql = "select count(distinct medicalNum) from dispenseddrug";
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
    public List<DispensedDrug> findAllDispensedDrug(Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT dispenseddrug.id," +
                "dispenseddrug.medicalNum,\n" +
                "    doctorName,registerName\n" +
                "FROM his.dispenseddrug join register on dispenseddrug.medicalNum=register.medicalNum " +
                "join doctor on doctorID=register.doctorID group by medicalNum";
        sql += " limit ?,?";
        System.out.println(sql);
        List<DispensedDrug> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page.getOffset());
            ps.setInt(2, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                DispensedDrug dispensedDrug = new DispensedDrug();
                dispensedDrug.setId(rs.getInt("id"));
                dispensedDrug.setMedicalNum(rs.getInt("medicalNum"));
                dispensedDrug.setDocName(rs.getString("doctorName"));
                dispensedDrug.setrName(rs.getString("registerName"));
                list.add(dispensedDrug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int disDrug(DispensedDrug dispensedDrug) {
        conn = ConnectionDB.getConnection();
        String sql = "update dispenseddrug set dispensedQuantity=dispensedQuantity+?,undispensedQuantity=totalQuantity-dispensedQuantity " +
                "WHERE id=? and medicalNum=?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dispensedDrug.getDispensedQuantity());
            ps.setInt(2, dispensedDrug.getId());
            ps.setInt(3, dispensedDrug.getMedicalNum());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int pay(int id, Date date) {

        conn = ConnectionDB.getConnection();
        String sql = "update dispenseddrug set payDate =? where medicalNum=? and isnull(payDate)=1";
        int col=0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(date.getTime()));
            ps.setInt(2, id);
            col=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }
}
