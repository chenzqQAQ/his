/**
 * Copyright (C),2015-2018
 * FileNmae: UsersDaoImpl
 * Author:   Administrator
 * Date:     2018/8/2815:14
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.dao.impl;

import com.youma.his.dao.UsersDao;
import com.youma.his.util.ConnectionDB;
import com.youma.his.vo.Users;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDaoImpl extends BaseDao implements UsersDao {
    @Override
    public int userAdd(Users user) {
        conn=ConnectionDB.getConnection();
        String sql = "insert into users(userID,userName,userPassword,modifyTime) values(?,?,?,?)";
        int col=0;
        try {
            ps=conn.prepareStatement(sql);
            ps.setObject(1,user.getUserID());
            ps.setObject(2,user.getUserName());
            ps.setObject(3,user.getUserPassword());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ps.setObject(4,sdf.format(new Date()));
            col=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int updateUsers(Users user) {
        conn=ConnectionDB.getConnection();
        String sql="update users set username=?,userPassword=?,modifyTime=?\n" +
                "where userID=?";
        int col=0;
        try {
            ps=conn.prepareStatement(sql);
            ps.setObject(1,user.getUserName());
            ps.setObject(2,user.getUserPassword());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ps.setObject(3,sdf.format(new Date()));
            ps.setObject(4,user.getUserID());
            col=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public int delUsers(int id) {
        conn=ConnectionDB.getConnection();
        String sql="delete from users\n" +
                "where userID=?";
        int col=0;
        try {
            ps=conn.prepareStatement(sql);
            ps.setObject(1,id);
            col=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    @Override
    public List<Users> findAllUsers() {
        conn=ConnectionDB.getConnection();
        String sql = "select userID,userName,userPassword,modifyTime from users";
        List list=new ArrayList();
        int col=0;
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            col = rs.getMetaData().getColumnCount();
            while(rs.next())
            {
                Users users=new Users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Users findUsers(int id) {
        conn=ConnectionDB.getConnection();
        String sql = "select userID,userName,userPassword,modifyTime from users where userID=?";
        Users user=new Users();
        try {
            ps=conn.prepareStatement(sql);
            ps.setObject(1,id);
            rs=ps.executeQuery();
            if(rs.next()){
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.setModifyTime(sdf.format(rs.getDate("modifyTime")));
                user.setUserID(rs.getInt("userID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
