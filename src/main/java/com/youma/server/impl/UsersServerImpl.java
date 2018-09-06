/**
 * Copyright (C),2015-2018
 * FileNmae: UsersServerImpl
 * Author:   Administrator
 * Date:     2018/8/309:56
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.UsersDao;
import com.youma.dao.impl.UsersDaoImpl;
import com.youma.vo.Users;
import com.youma.server.UsersServer;

import java.util.List;

/**
 * @author 陈泽群
 */
public class UsersServerImpl implements UsersServer {
    UsersDao usersDao = new UsersDaoImpl();

    @Override
    public int userAdd(Users user) {
        return usersDao.userAdd(user);
    }

    @Override
    public int updateUsers(Users user) {
        return usersDao.updateUsers(user);
    }

    @Override
    public int delUsers(int id) {
        return usersDao.delUsers(id);
    }

    @Override
    public List<Users> findAllUsers() {
        return usersDao.findAllUsers();
    }

    @Override
    public Users findUsers(int id) {
        return usersDao.findUsers(id);
    }

    @Override
    public int findUsers(String userName) {
        return usersDao.findUsers(userName);
    }
}
