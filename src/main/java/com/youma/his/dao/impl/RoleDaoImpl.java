/**
 * Copyright (C),2015-2018
 * FileNmae: RoleDaoImpl
 * Author:   Administrator
 * Date:     2018/8/2819:22
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.dao.impl;

import com.youma.his.dao.RoleDao;
import com.youma.his.util.ConnectionDB;
import com.youma.his.vo.Role;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends BaseDao implements RoleDao {

    @Override
    public int roleAdd(Role role) {
        conn = ConnectionDB.getConnection();
        int col = 0;
        String sql = "    INSERT INTO role\n" +
                "(roleID,\n" +
                "roleNum,\n" +
                "roleName)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?);";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, role.getRoleID());
            ps.setObject(2, role.getRoleNum());
            ps.setObject(3, role.getRoleName());
            col = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int updateRole(Role role) {
        conn = ConnectionDB.getConnection();
        int col = 0;
        String sql = "UPDATE role\n" +
                "SET\n" +
                "roleID = ?,\n" +
                "roleNum = ?,\n" +
                "roleName = ?\n" +
                "WHERE roleID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, role.getRoleID());
            ps.setObject(2, role.getRoleNum());
            ps.setObject(3, role.getRoleName());
            ps.setObject(4, role.getRoleID());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return col;
    }

    @Override
    public int delRole(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "DELETE FROM role\n" +
                "WHERE roleID= ?;";
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
    public List<Role> findAllRole() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT roleID,\n" +
                "    roleNum,\n" +
                "    roleName\n" +
                "FROM role\n";
        List<Role> list = new ArrayList<Role>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleNum(rs.getString("roleNum"));
                role.setRoleName(rs.getString("roleName"));
                list.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Role findRole(int id) {

        conn = ConnectionDB.getConnection();
        String sql = "SELECT roleID,\n" +
                "    roleNum,\n" +
                "    roleName\n" +
                "FROM role\n" +
                "WHERE roleID = ?";
        Role role = new Role();
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleNum(rs.getString("roleNum"));
                role.setRoleName(rs.getString("roleName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
