/**
 * Copyright (C),2015-2018
 * FileNmae: RoleDaoImpl
 * Author:   Administrator
 * Date:     2018/8/2819:22
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.RoleDao;
import com.youma.util.ConnectionDB;
import com.youma.util.Page;
import com.youma.vo.Resources;
import com.youma.vo.Role;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends BaseDao implements RoleDao {

    @Override
    public int roleAdd(Role role) {
        conn = ConnectionDB.getConnection();
        int col = 0;
        String sql = "    INSERT INTO role\n" +
                "(" +
                "roleNum,\n" +
                "roleName,status)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,?);";
        try {
            ps = conn.prepareStatement(sql);

            ps.setObject(1, role.getRoleNum());
            ps.setObject(2, role.getRoleName());
            ps.setInt(3, role.getStatus());
            col = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return col;
    }

    @Override
    public int updateRole(Role role) {
        conn = ConnectionDB.getConnection();
        int col = 0;
        String sql = "UPDATE role\n" +
                "SET\n" +
                "roleNum = ?,\n" +
                "roleName = ?,status=?\n" +
                "WHERE roleID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, role.getRoleNum());
            ps.setObject(2, role.getRoleName());
            ps.setObject(3, role.getStatus());
            ps.setObject(4, role.getRoleID());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
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
        } finally {
            closeAll();
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
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public List<Role> findAllRole(Role role1, Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT roleID,\n" +
                "    roleNum,\n" +
                "    roleName,status\n" +
                "FROM role where 1=1 ";
        if (role1.getRoleName() != null && !role1.getRoleName().isEmpty()) {
            sql += " and roleName like \"%\" ? \"%\" ";
        }
        sql += " limit ?,?";
        List<Role> list = new ArrayList<Role>();
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (role1.getRoleName() != null && !role1.getRoleName().isEmpty()) {
                ps.setString(index++, role1.getRoleName());
            }
            ps.setInt(index++, page.getOffset());
            ps.setInt(index++, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleNum(rs.getString("roleNum"));
                role.setRoleName(rs.getString("roleName"));
                role.setStatus(rs.getInt("status"));
                list.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int roleCount(Role role) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT count(roleID)\n" +
                "FROM role where 1=1 ";
        if (role.getRoleName() != null && !role.getRoleName().isEmpty()) {
            sql += " and roleName like \"%\" ? \"%\" ";
        }
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (role.getRoleName() != null && !role.getRoleName().isEmpty()) {
                ps.setString(index++, role.getRoleName());
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
    public Role findRole(int id) {

        conn = ConnectionDB.getConnection();
        String sql = "SELECT roleID,\n" +
                "    roleNum,\n" +
                "    roleName,status\n" +
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
                role.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return role;
    }

    @Override
    public int findRole(String name) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT roleID\n" +
                "FROM role\n" +
                "WHERE roleName = ?";
        int id = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return id;
    }

    @Override
    public int delRes(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "delete from  role_resources where roleID=?";
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
    public int addRes(Role role) {
        conn = ConnectionDB.getConnection();
        String sql = "insert role_resources(roleID, resID) values(?,?)";
        List<Resources> list = role.getResources();
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                sql += " ,(?,?)";
            }
        } else {
            //没有可添加项
            return 0;
        }
        System.out.println(sql);
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            for (int i = 0; i < list.size(); i++) {
                ps.setInt(index++, role.getRoleID());
                ps.setInt(index++, list.get(i).getResID());
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
    public Role findRes(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "select resources.resid as p,resName,resUrl from role join role_resources on role.roleID=role_resources.roleID join resources on resources.resid=role_resources.resID\n" +
                "where role.roleID=? and resources.status=1";
        Role role = new Role();
        try {
            role.setRoleID(id);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<Resources> list = new ArrayList<>();
            while (rs.next()) {
                Resources resources = new Resources();
                resources.setResID(rs.getInt("p"));
                resources.setResName(rs.getString("resName"));
                resources.setResUrl(rs.getString("resUrl"));
                list.add(resources);
            }
            role.setResources(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
