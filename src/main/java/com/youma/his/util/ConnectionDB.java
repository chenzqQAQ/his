/**
 * Copyright (C),2015-2018
 * FileNmae: ConnectionDB
 * Author:   Administrator
 * Date:     2018/8/2814:42
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接类
 */
public class ConnectionDB {
    /**
     * 用户名
     */
    public static String NAME = "root";
    /**
     * 用户密码
     */
    public static String PASSWORD = "chenzequn253";
    /**
     * 数据库驱动
     */
    public static String DIVER= "jdbc:mysql://localhost:3306/";
    /**
     * 操作的数据库
     */
    public static String DATABASE = "his";
    public static String DRIVER="com.mysql.jdbc.Driver";
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(DIVER + DATABASE, NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
