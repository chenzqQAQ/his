/**
 * Copyright (C),2015-2018
 * FileNmae: UsersDaoImpl
 * Author:   Administrator
 * Date:     2018/8/2815:14
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import com.youma.dao.UsersDao;
import com.youma.util.ConnectionDB;
import com.youma.util.Page;
import com.youma.vo.Users;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
public class UsersDaoImpl extends BaseDao implements UsersDao {
    @Override
    public int userAdd(Users user) {
        conn = ConnectionDB.getConnection();
        String sql = "insert into users(userName,userPassword,modifyTime,roleID,flag,realName,email) values(?,?,?,?,?,?,?)";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getUserName());
            ps.setObject(2, user.getUserPassword());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ps.setObject(3, sdf.format(new Date()));
            ps.setObject(4, user.getRoleID());
            ps.setObject(5, user.getFlag());
            ps.setObject(6, user.getRealName());
            ps.setObject(7, user.getEmail());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int updateUsers(Users user) {
        conn = ConnectionDB.getConnection();
        String sql = "update users set username=?,userPassword=?,modifyTime=?,roleID=?," +
                "flag=?,realName=?,email=?\n" +
                "where userID=?";
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getUserName());
            ps.setObject(2, user.getUserPassword());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ps.setObject(3, sdf.format(new Date()));
            ps.setObject(4, user.getRoleID());
            ps.setObject(5, user.getFlag());
            ps.setObject(6, user.getRealName());
            ps.setObject(7, user.getEmail());
            ps.setObject(8, user.getUserID());
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int delUsers(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "delete from users\n" +
                "where userID=?";
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
    public List<Users> findAllUsers() {
        conn = ConnectionDB.getConnection();
        String sql = "select userID,userName,userPassword,modifyTime,roleID,flag,realName,email from users";
        List<Users> list = new ArrayList<>();
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            col = rs.getMetaData().getColumnCount();
            while (rs.next()) {

                Users users = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                users.setFlag(rs.getInt("flag"));
                users.setRealName(rs.getString("realName"));
                users.setEmail(rs.getString("email"));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int allUsersCount(Users users) {
        conn = ConnectionDB.getConnection();
        String sql = "select count(userID) from users where 1 = 1";
        if (users.getUserName() != null && !users.getUserName().isEmpty()) {
            sql += " and userName like ?";
        }
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (users.getUserName() != null && !users.getUserName().isEmpty()) {
                ps.setString(index++, "%"+users.getUserName()+"%");
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
    public List<Users> findAllUsers(Users users1, Page page) {
        conn = ConnectionDB.getConnection();
        String sql = "select userID,userName,roleName,realName from users\n" +
                "join  role on role.roleID=users.roleID where  1=1 \n";
        if (users1.getUserName() != null && !users1.getUserName().isEmpty()) {
            sql += " and userName like \"%\" ? \"%\" ";
        }
        sql += " limit ?,?";
        System.out.println(sql);
        List<Users> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (users1.getUserName() != null && !users1.getUserName().isEmpty()) {
                ps.setString(index++,users1.getUserName());
            }
            ps.setInt(index++, page.getOffset());
            ps.setInt(index++, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {

                Users users = new Users();
                users.setUserID(rs.getInt("userID"));
                users.setUserName(rs.getString("userName"));
                users.setRealName(rs.getString("realName"));
                users.setRoleName(rs.getString("roleName"));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Users findUsers(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "select userID,userName,userPassword,modifyTime,roleID,flag,realName,email from users where userID=?";
        Users user = new Users();
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.setModifyTime(sdf.format(rs.getDate("modifyTime")));
                user.setUserID(rs.getInt("userID"));
                user.setRoleID(rs.getInt("roleID"));
                user.setFlag(rs.getInt("flag"));
                user.setRealName(rs.getString("realName"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int findUsers(String userName) {
        conn = ConnectionDB.getConnection();
        String sql = "select userName from users where userName=?";
        Users user = new Users();
        int col = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            rs.last();
            col = rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int delUsers(int[] args) {
        conn = ConnectionDB.getConnection();
        String sql = "delete from users where userID in ( ?";
        for (int i = 1; i < args.length; i++) {
            sql += ", ?";
        }
        sql += ")";
        int col = 0;
        System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setInt(i + 1, args[i]);
            }
            col = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public Users findUsers(Users user) {
        conn = ConnectionDB.getConnection();
        String sql = "select roleID,realName from users where userName=? and userPassword=?";
        Users users = new Users();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getUserPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                users.setRoleID(rs.getInt("roleID"));
                users.setRealName(rs.getString("realName"));
            } else {
                users = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return users;
    }
}
