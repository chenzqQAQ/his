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
import com.youma.util.Page;
import com.youma.vo.Users;
import com.youma.server.UsersServer;

import java.util.List;

/**
 * @author 陈泽群
 */
public class UsersServerImpl implements UsersServer {
    private UsersDao usersDao = new UsersDaoImpl();

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
    public int allUsersCount() {
        return usersDao.allUsersCount();
    }

    @Override
    public List<Users> findAllUsers(Page page) {
        return usersDao.findAllUsers(page);
    }

    @Override
    public Users findUsers(int id) {
        return usersDao.findUsers(id);
    }

    @Override
    public int findUsers(String userName) {
        return usersDao.findUsers(userName);
    }

    @Override
    public int delUsers(int[] args) {
        return usersDao.delUsers(args);
    }

    @Override
    public Users findUsers(Users user) {
        return usersDao.findUsers(user);
    }
}
