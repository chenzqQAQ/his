/**
 * Copyright (C),2015-2018
 * FileNmae: PayProjectDaoImpl
 * Author:   Administrator
 * Date:     2018/8/3114:58
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.PayProjectDao;
import com.youma.util.ConnectionDB;
import com.youma.vo.PayProject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈泽群
 */
public class PayProjectDaoImpl extends BaseDao implements PayProjectDao {
    @Override
    public int payProjectAdd(PayProject payProject) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO  payproject \n" +
                "( ID ,\n" +
                " projectName ,\n" +
                " amount )\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?);\n";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, payProject.getId());
            ps.setObject(2, payProject.getProjectName());
            ps.setObject(3, payProject.getAmount());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int updatePayProject(PayProject payProject) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE  payproject \n" +
                "SET\n" +
                " ID  = ?,\n" +
                " projectName  = ?,\n" +
                " amount  = ?\n" +
                "WHERE  ID  = ?;\n";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, payProject.getId());
            ps.setObject(2, payProject.getProjectName());
            ps.setObject(3, payProject.getAmount());
            ps.setObject(4, payProject.getId());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int delPayProject(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "DELETE FROM payproject\n" +
                "WHERE ID=?;";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public List<PayProject> findAllPayProject() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT  payproject . ID ,\n" +
                "     payproject . projectName ,\n" +
                "     payproject . amount \n" +
                "FROM  his . payproject";
        List<PayProject> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PayProject payProject = new PayProject();
                payProject.setId(rs.getInt("ID"));
                payProject.setProjectName(rs.getString("projectName"));
                payProject.setAmount(rs.getDouble("amount"));
                list.add(payProject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PayProject findPayProject(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT   ID ,\n" +
                "      projectName ,\n" +
                "      amount \n" +
                "FROM  payproject  " +
                "Where ID=?";
        PayProject payProject = new PayProject();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                payProject.setId(rs.getInt("ID"));
                payProject.setProjectName(rs.getString("projectName"));
                payProject.setAmount(rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payProject;
    }
}
