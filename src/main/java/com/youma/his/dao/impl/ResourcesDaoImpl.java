/**
 * Copyright (C),2015-2018
 * FileNmae: ResourcesDaoImpl
 * Author:   Administrator
 * Date:     2018/8/2820:02
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.dao.impl;

import com.youma.his.dao.ResourcesDao;
import com.youma.his.util.ConnectionDB;
import com.youma.his.vo.Resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ResourcesDaoImpl extends BaseDao implements ResourcesDao {
    @Override
    public int resourcesAdd(Resources resources) {
        conn = ConnectionDB.getConnection();
        String sql = "    INSERT INTO resources\n" +
                "(resID,\n" +
                "resName,\n" +
                "resUrl,\n" +
                "resParentID)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?)\n";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, resources.getResID());
            ps.setObject(2, resources.getResName());
            ps.setObject(3, resources.getResUrl());
            ps.setObject(4, resources.getResParentId());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int uepdateResources(Resources resources) {

        conn = ConnectionDB.getConnection();
        String sql = "UPDATE resources\n" +
                "SET\n" +
                "resID = ?,\n" +
                "resName = ?,\n" +
                "resUrl = ?,\n" +
                "resParentID = ?\n" +
                "WHERE resID = ?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, resources.getResID());
            ps.setObject(2, resources.getResName());
            ps.setObject(3, resources.getResUrl());
            ps.setObject(4, resources.getResParentId());
            ps.setObject(5, resources.getResID());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int delResources(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "DELETE FROM resources\n" +
                "WHERE resID=?";
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
    public List<Resources> findAllResources() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT resID,\n" +
                "    resName,\n" +
                "    resUrl,\n" +
                "    resParentID\n" +
                "FROM resources";
        List<Resources> list = new ArrayList<Resources>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Resources resources = new Resources();
                resources.setResID(rs.getInt("resID"));
                resources.setResName(rs.getString("resName"));
                resources.setResUrl(rs.getString("resUrl"));
                resources.setResParentId(rs.getInt("resParentId"));
                list.add(resources);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Resources findResources(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT resID,\n" +
                "    resName,\n" +
                "    resUrl,\n" +
                "    resParentID\n" +
                "FROM resources\n" +
                "WHERE resId = ?";
        Resources resources = new Resources();
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                resources.setResID(rs.getInt("resID"));
                resources.setResName(rs.getString("resName"));
                resources.setResUrl(rs.getString("resUrl"));
                resources.setResParentId(rs.getInt("resParentId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resources;
    }
}
