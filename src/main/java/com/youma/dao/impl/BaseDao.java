/**
 * Copyright (C),2015-2018
 * FileNmae: BaseDao
 * Author:   Administrator
 * Date:     2018/8/2819:25
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * 连接信息基类
 *
 * @author Administrator
 */
public class BaseDao {
    /**
     * 数据库连接
     */
    Connection conn = null;
    /**
     * 返回结果集
     */
    ResultSet rs = null;
    /**
     * 预编译sql操作
     */
    PreparedStatement ps = null;
    /**
     * 时间格式化
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 日期格式化
     */
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    public void closeAll() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
