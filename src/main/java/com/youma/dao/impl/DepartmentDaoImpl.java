/**
 * Copyright (C),2015-2018
 * FileNmae: DepartmentDaoImpl
 * Author:   Administrator
 * Date:     2018/8/3116:05
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.DepartmentDao;
import com.youma.util.ConnectionDB;
import com.youma.vo.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈泽群
 */
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {
    @Override
    public int departmentAdd(Department department) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO department \n" +
                "( ID ,\n" +
                " depNum ,\n" +
                " depName )\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?);\n";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, department.getId());
            ps.setString(2, department.getDepNum());
            ps.setString(3, department.getDepName());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int updateDepartment(Department department) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE department\n" +
                "SET\n" +
                "ID = ?,\n" +
                "depNum = ?,\n" +
                "depName = ?\n" +
                "WHERE ID = ?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, department.getId());
            ps.setString(2, department.getDepNum());
            ps.setString(3, department.getDepName());
            ps.setInt(4, department.getId());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int delDepartment(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "DELETE FROM department\n" +
                "WHERE ID=?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public List<Department> findAllDepartment() {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT department.ID,\n" +
                "    department.depNum,\n" +
                "    department.depName\n" +
                "FROM his.department;";
        List<Department> list = new ArrayList();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("ID"));
                department.setDepNum(rs.getString("depNum"));
                department.setDepName(rs.getString("depName"));
                list.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Department findDepartment(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT department.ID,\n" +
                "    department.depNum,\n" +
                "    department.depName\n" +
                "FROM his.department\n" +
                "WHERE department.ID=?";
        Department department = new Department();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                department.setId(rs.getInt("ID"));
                department.setDepNum(rs.getString("depNum"));
                department.setDepName(rs.getString("depName"));
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
