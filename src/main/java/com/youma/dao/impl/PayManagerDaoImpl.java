/**
 * Copyright (C),2015-2018
 * FileNmae: PayManagerDaoImpl
 * Author:   Administrator
 * Date:     2018/9/14 10:47
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.PayManagerDao;
import com.youma.util.ConnectionDB;
import com.youma.util.Page;
import com.youma.vo.PayManager;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PayManagerDaoImpl
 * TODO(描述类的作用)
 *
 * @author 陈泽群
 * @date 2018/9/14 10:47
 */
public class PayManagerDaoImpl extends BaseDao implements PayManagerDao {
    @Override
    public int addPay(List<PayManager> list) {
        conn = ConnectionDB.getConnection();
        String sql = "insert paymanager(medicalNum, payID, chargeAmount) values (?,?,?)";
        for (int i = 1; i < list.size(); i++) {
            sql += "  ,(?,?,?)";
        }
        // System.out.println(sql);
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            for (int i = 0; i < list.size(); i++) {
                PayManager payManager = list.get(i);
                ps.setInt(index++, payManager.getMedicalNum());
                ps.setInt(index++, payManager.getPayId());
                ps.setDouble(index++, payManager.getChargeAmount());
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
    public List<PayManager> findAll(Page page) {
        conn = ConnectionDB.getConnection();
        List<PayManager> list = new ArrayList<>();
        String sql = "select paymanager.medicalNum as 'p',registerName,projectName,chargeAmount,payDate\n" +
                "from paymanager join register on paymanager.medicalNum=register.medicalNum join payproject" +
                " on payID=payproject.ID order by paymanager.medicalNum";
        sql += " limit ?,?";
        // System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page.getOffset());
            ps.setInt(2, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                PayManager payManager = new PayManager();
                payManager.setMedicalNum(rs.getInt("p"));
                payManager.setName(rs.getString("registerName"));
                payManager.setPayName(rs.getString("projectName"));
                payManager.setChargeAmount(rs.getDouble("chargeAmount"));
                if (rs.getDate("payDate") == null) {
                    payManager.setPayDate("未付款");
                } else {

                    payManager.setPayDate(sdf.format(rs.getTimestamp("payDate")));
                }
                list.add(payManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int count() {
        conn = ConnectionDB.getConnection();
        List<PayManager> list = new ArrayList<>();
        String sql = "select count(medicalNum) from paymanager";
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
    public List<PayManager> findAll(int id) {
        conn = ConnectionDB.getConnection();
        List<PayManager> list = new ArrayList<>();
        String sql = "select paymanager.medicalNum ,projectName,chargeAmount,registerName,payDate\n" +
                "from paymanager  join payproject" +
                " on payID=payproject.ID join register on paymanager.medicalNum=register.medicalNum " +
                "where paymanager.medicalNum=?";
        // System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                PayManager payManager = new PayManager();
                payManager.setMedicalNum(rs.getInt("medicalNum"));
                payManager.setPayName(rs.getString("projectName"));
                payManager.setChargeAmount(rs.getDouble("chargeAmount"));
                payManager.setName(rs.getString("registerName"));
                if (rs.getDate("payDate") == null) {
                    payManager.setPayDate("未付款");
                } else {
                    payManager.setPayDate(sdf.format(rs.getTimestamp("payDate")));
                }
                list.add(payManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int allCount(PayManager payManager) {
        conn = ConnectionDB.getConnection();
        String sql = "select count(paymanager.medicalNum) from paymanager join register on register.medicalNum=paymanager.medicalNum\n" +
                "where 1=1";
        if (payManager.getName() != null && !"".equals(payManager.getName())) {
            sql += " and registerName=?";
        }
        if (payManager.getMedicalNum() != 0) {
            sql += " and paymanager.medicalNum=?";
        }
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (payManager.getName() != null && !"".equals(payManager.getName())) {
                ps.setString(index++, payManager.getName());
            }
            if (payManager.getMedicalNum() != 0) {
                ps.setInt(index++, payManager.getMedicalNum());
            }
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
    public List<PayManager> findAll(PayManager payManager1, Page page) {
        conn = ConnectionDB.getConnection();
        List<PayManager> list = new ArrayList<>();
        String sql = "select paymanager.medicalNum as 'p',registerName,projectName,chargeAmount,payDate\n" +
                "from paymanager join register on paymanager.medicalNum=register.medicalNum join payproject" +
                " on payID=payproject.ID WHERE 1=1 ";
        if (payManager1.getName() != null && !"".equals(payManager1.getName())) {
            sql += " and registerName=?";
        }
        if (payManager1.getMedicalNum() != 0) {
            sql += " and paymanager.medicalNum=?";
        }
        sql += " order by paymanager.medicalNum asc,payID asc limit ?,?";
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (payManager1.getName() != null && !"".equals(payManager1.getName())) {
                ps.setString(index++, payManager1.getName());
            }
            if (payManager1.getMedicalNum() != 0) {
                ps.setInt(index++, payManager1.getMedicalNum());
            }
            ps.setInt(index++, page.getOffset());
            ps.setInt(index++, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                PayManager payManager = new PayManager();
                payManager.setMedicalNum(rs.getInt("p"));
                payManager.setName(rs.getString("registerName"));
                payManager.setPayName(rs.getString("projectName"));
                payManager.setChargeAmount(rs.getDouble("chargeAmount"));
                if (rs.getDate("payDate") == null) {
                    payManager.setPayDate("未付款");
                } else {
                    payManager.setPayDate(sdf.format(rs.getTimestamp("payDate")));
                }
                // System.out.println(payManager.toString());
                list.add(payManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int pay(int id, Date date) {
        conn = ConnectionDB.getConnection();
        String sql = "update paymanager set payDate =? where medicalNum=? and isnull(payDate)=1";
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
