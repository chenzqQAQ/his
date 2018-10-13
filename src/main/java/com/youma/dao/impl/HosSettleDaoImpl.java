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
import com.youma.util.Page;
import com.youma.vo.HosSettle;
import com.youma.vo.Inpatient;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int hosSettleAdd(Inpatient inpatient) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO his.hossettle\n" +
                "(\n" +
                "medicalNum,\n" +
                "flag,\n" +
                "deposit,\n" +
                "paidCost)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?);";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, inpatient.getMedicalNum());
            ps.setInt(2, 0);
            ps.setDouble(3, inpatient.getDeposit());
            ps.setDouble(4, inpatient.getDeposit());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
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
        } finally {
            closeAll();
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
        } finally {
            closeAll();
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
        } finally {
            closeAll();
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
                "    hossettle.paidCost,\n" +
                "inptime,\n" +
                "datediff(now(),iFNULL(payDate, inptime))+ IF(ISNULL(payDate), 1, 0) as p\n" +
                "FROM his.hossettle join inpatient on hossettle.medicalNum=inpatient.medicalNum\n" +
                "WHERE hossettle.medicalNum=?";
        System.out.println(sql);
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
                if (rs.getDate("payDate") != null) {

                    hosSettle.setPayDate(sdf.format(rs.getTimestamp("payDate")));
                } else {
                    hosSettle.setPayDate("未付款");
                }
                hosSettle.setInpDate(sdf.format(rs.getTimestamp("inptime")));
                hosSettle.setMedicalCost(rs.getDouble("medicalCost"));
                hosSettle.setDrugCost(rs.getDouble("drugCost"));
                hosSettle.setPaidCost(rs.getDouble("paidCost"));
                hosSettle.setHosDay(rs.getInt("p"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return hosSettle;
    }

    @Override
    public int updateCost(int id) {
        conn = ConnectionDB.getConnection();
        int col = 0;
        String sql = "update hossettle, \n" +
                "(SELECT \n" +
                "    hossettle.medicalNum,\n" +
                "    p1.a1 as a,\n" +
                "    p2.a2 as b,\n" +
                "datediff(now(),iFNULL(payDate, inptime))+ IF(ISNULL(payDate), 1, 0) as c\n" +
                "FROM\n" +
                "    hossettle join inpatient on hossettle.medicalNum=inpatient.medicalNum,\n" +
                "    (SELECT \n" +
                "        hossettle.medicalNum, IFNULL(SUM(chargeAmount), 0.0) AS a1\n" +
                "    FROM\n" +
                "        hossettle\n" +
                "    LEFT JOIN paymanager ON paymanager.medicalNum = hossettle.medicalNum\n" +
                "    GROUP BY hossettle.medicalNum) p1,\n" +
                "    (SELECT \n" +
                "        hossettle.medicalNum,\n" +
                "            IFNULL(SUM(drug.sellingPrice * dispenseddrug.totalQuantity), 0.0) AS a2\n" +
                "    FROM\n" +
                "        hossettle\n" +
                "    LEFT JOIN dispenseddrug ON dispenseddrug.medicalNum = hossettle.medicalNum\n" +
                "    LEFT JOIN drug ON drug.drugID = dispenseddrug.drugID\n" +
                "    GROUP BY hossettle.medicalNum\n" +
                "    ORDER BY hossettle.medicalNum) p2\n" +
                "WHERE\n" +
                "    p1.medicalNum = hossettle.medicalNum\n" +
                "        AND p2.medicalNum = hossettle.medicalNum  and inpatient.flag!=4" +
                ") p\n" +
                "set\n" +
                "    cost = p.a + p.b+p.c*50,\n" +
                "    medicalCost = p.a,\n" +
                "    drugCost = p.b,\n" +
                "    overplusCost = p.a + p.b +p.c*50- paidCost,\n" +
                "    balance = paidCost - (p.a + p.b+p.c*50)\n" +
                "where p.medicalNum=hossettle.medicalNum\n" +
                "and hossettle.medicalNum=?";
        // System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            col = ps.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int updateCost() {
        conn = ConnectionDB.getConnection();
        int col = 0;
        String sql = "update hossettle, \n" +
                "(SELECT \n" +
                "    hossettle.medicalNum,\n" +
                "    p1.a1 as a,\n" +
                "    p2.a2 as b,\n" +
                "datediff(now(),iFNULL(payDate, inptime))+ IF(ISNULL(payDate), 1, 0) as c\n" +
                "FROM\n" +
                "    hossettle join inpatient on hossettle.medicalNum=inpatient.medicalNum,\n" +
                "    (SELECT \n" +
                "        hossettle.medicalNum, IFNULL(SUM(chargeAmount), 0.0) AS a1\n" +
                "    FROM\n" +
                "        hossettle\n" +
                "    LEFT JOIN paymanager ON paymanager.medicalNum = hossettle.medicalNum\n" +
                "    GROUP BY hossettle.medicalNum) p1,\n" +
                "    (SELECT \n" +
                "        hossettle.medicalNum,\n" +
                "            IFNULL(SUM(drug.sellingPrice * dispenseddrug.totalQuantity), 0.0) AS a2\n" +
                "    FROM\n" +
                "        hossettle\n" +
                "    LEFT JOIN dispenseddrug ON dispenseddrug.medicalNum = hossettle.medicalNum\n" +
                "    LEFT JOIN drug ON drug.drugID = dispenseddrug.drugID\n" +
                "    GROUP BY hossettle.medicalNum\n" +
                "    ORDER BY hossettle.medicalNum) p2\n" +
                "WHERE\n" +
                "    p1.medicalNum = hossettle.medicalNum\n" +
                "        AND p2.medicalNum = hossettle.medicalNum  and inpatient.flag!=4" +
                ") p\n" +
                "set\n" +
                "    cost = p.a + p.b+p.c*50,\n" +
                "    medicalCost = p.a,\n" +
                "    drugCost = p.b,\n" +
                "    overplusCost = p.a + p.b +p.c*50- paidCost,\n" +
                "    balance = paidCost - (p.a + p.b+p.c*50)\n" +
                "where p.medicalNum=hossettle.medicalNum\n";
        try {
            ps = conn.prepareStatement(sql);
            col = ps.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public List<HosSettle> findAll() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT hossettle.ID,\n" +
                "    hossettle.medicalNum,\n" +
                "    hossettle.flag,\n" +
                "    hossettle.deposit," +
                "registerName\n" +
                "FROM his.hossettle join register on hossettle.medicalNum=register.medicalNum";
        List<HosSettle> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HosSettle hosSettle = new HosSettle();
                hosSettle.setId(rs.getInt("id"));
                hosSettle.setMedicalNum(rs.getInt("medicalNum"));
                hosSettle.setFlag(rs.getInt("flag"));
                hosSettle.setDeposit(rs.getDouble("deposit"));
                hosSettle.setrName(rs.getString("registerName"));
                list.add(hosSettle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int pay(int id, java.util.Date date) {

        conn = ConnectionDB.getConnection();
        String sql = "update hossettle set flag=1,paidCost=cost,overplusCost=0,balance=0 ,payDate=? " +
                "where medicalNum=?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(date.getTime()));
            ps.setInt(2, id);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int payCash(int id, java.util.Date date, double cash) {
        conn = ConnectionDB.getConnection();
        String sql = "update hossettle set paidCost=cost,overplusCost=0,balance=? ,payDate=? " +
                "where medicalNum=?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, cash);
            ps.setTimestamp(2, new Timestamp(date.getTime()));
            ps.setInt(3, id);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public List<HosSettle> findAll(HosSettle hosSettle1, Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT hossettle.ID,\n" +
                "    hossettle.medicalNum,\n" +
                "    hossettle.flag,\n" +
                "    hossettle.deposit,hossettle.balance," +
                "registerName\n" +
                "FROM his.hossettle join register on hossettle.medicalNum=register.medicalNum where 1=1 ";
        if (hosSettle1.getMedicalNum() != 0) {
            sql += " and hossettle.medicalNum =? ";
        }
        if (StringUtils.isNotBlank(hosSettle1.getrName())) {
            sql += " and register.registerName like \"%\" ? \"%\" ";
        }
        sql += " order by hossettle.medicalNum desc limit ?,?";
        List<HosSettle> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (hosSettle1.getMedicalNum() != 0) {
                ps.setInt(index++, hosSettle1.getMedicalNum());
            }
            if (StringUtils.isNotBlank(hosSettle1.getrName())) {
                ps.setString(index++, StringUtils.trim(hosSettle1.getrName()));
            }
            ps.setInt(index++, page.getOffset());
            ps.setInt(index++, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                HosSettle hosSettle = new HosSettle();
                hosSettle.setId(rs.getInt("id"));
                hosSettle.setMedicalNum(rs.getInt("medicalNum"));
                hosSettle.setFlag(rs.getInt("flag"));
                hosSettle.setDeposit(rs.getDouble("deposit"));
                hosSettle.setrName(rs.getString("registerName"));
                hosSettle.setBalance(rs.getDouble("balance"));
                list.add(hosSettle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int allCount(HosSettle hosSettle) {
        conn = ConnectionDB.getConnection();
        String sql = "select  count(ID) from hossettle where 1=1 ";
        if (hosSettle.getMedicalNum() != 0) {
            sql += " and medicalNum =? ";
        }
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (hosSettle.getMedicalNum() != 0) {
                ps.setInt(index++, hosSettle.getMedicalNum());
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
    public List<HosSettle> findAllCash() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT hossettle.ID,\n" +
                "    hossettle.medicalNum,\n" +
                "    hossettle.flag,\n" +
                "    hossettle.deposit,hossettle.balance," +
                "registerName\n" +
                "FROM his.hossettle join register on hossettle.medicalNum=register.medicalNum where deposit+balance<0 ";
        sql += " order by hossettle.medicalNum desc";
        List<HosSettle> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HosSettle hosSettle = new HosSettle();
                hosSettle.setId(rs.getInt("id"));
                hosSettle.setMedicalNum(rs.getInt("medicalNum"));
                hosSettle.setFlag(rs.getInt("flag"));
                hosSettle.setDeposit(rs.getDouble("deposit"));
                hosSettle.setrName(rs.getString("registerName"));
                hosSettle.setBalance(rs.getDouble("balance"));
                list.add(hosSettle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }
}
