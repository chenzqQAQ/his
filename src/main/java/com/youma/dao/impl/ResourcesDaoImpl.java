/**
 * Copyright (C),2015-2018
 * FileNmae: ResourcesDaoImpl
 * Author:   Administrator
 * Date:     2018/8/2820:02
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.ResourcesDao;
import com.youma.util.ConnectionDB;
import com.youma.util.Page;
import com.youma.vo.Resources;
import org.apache.commons.lang3.StringUtils;

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
        String sql = "    INSERT INTO resources(\n" +
                "resName,\n" +
                "resUrl,\n" +
                "resParentID,\n" +
                "status)" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?)\n";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, resources.getResName());
            ps.setString(2, resources.getResUrl());
            ps.setInt(3, resources.getResParentId());
            ps.setInt(4, resources.getStatus());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
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
                "resParentID = ?," +
                "status=?\n" +
                "WHERE resID = ?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, resources.getResID());
            ps.setString(2, resources.getResName());
            ps.setString(3, resources.getResUrl());
            ps.setInt(4, resources.getResParentId());
            ps.setInt(5, resources.getStatus());
            ps.setInt(6, resources.getResID());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
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
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public List<Resources> findAllResources() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT resID,\n" +
                "    resName,\n" +
                "    resUrl,\n" +
                "    resParentID,\n" +
                "status \n" +
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
                resources.setStatus(rs.getInt("status"));
                list.add(resources);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public Resources findResources(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT resID,\n" +
                "    resName,\n" +
                "    resUrl,\n" +
                "    resParentID,status\n" +
                "FROM resources\n" +
                "WHERE resId = ?";
        Resources resources = new Resources();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                resources.setResID(rs.getInt("resID"));
                resources.setResName(rs.getString("resName"));
                resources.setResUrl(rs.getString("resUrl"));
                resources.setResParentId(rs.getInt("resParentId"));
                resources.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return resources;
    }

    @Override
    public int allResourcesCount(Resources resources) {
        conn = ConnectionDB.getConnection();
        String sql = "select  count(resid) as a from resources where 1=1 ";
        if (StringUtils.isNotBlank(resources.getResName())) {
            sql += " and resName like \"%\" ? \"%\" ";
        }
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (StringUtils.isNotBlank(resources.getResName())) {
                ps.setString(index++, resources.getResName());
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                col = rs.getInt("a");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public List<Resources> findAllResources(Resources resources1, Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT resID,\n" +
                "    resName,\n" +
                "    resUrl,\n" +
                "    resParentID,\n" +
                "status \n" +
                "FROM resources where 1=1 ";
        if (StringUtils.isNotBlank(resources1.getResName())) {
            sql += " and resName like \"%\" ? \"%\" ";
        }
        sql += " limit ?,?";
        List<Resources> list = new ArrayList<Resources>();
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (StringUtils.isNotBlank(resources1.getResName())) {
                ps.setString(index++, resources1.getResName());
            }
            ps.setInt(index++, page.getOffset());
            ps.setInt(index++, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                Resources resources = new Resources();
                resources.setResID(rs.getInt("resID"));
                resources.setResName(rs.getString("resName"));
                resources.setResUrl(rs.getString("resUrl"));
                resources.setResParentId(rs.getInt("resParentId"));
                resources.setStatus(rs.getInt("status"));
                list.add(resources);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }
}
