/**
 * Copyright (C),2015-2018
 * FileNmae: RegisterServerImpl
 * Author:   Administrator
 * Date:     2018/9/713:35
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.RegisterDao;
import com.youma.dao.impl.RegisterDaoImpl;
import com.youma.server.RegisterServer;
import com.youma.util.Page;
import com.youma.vo.Register;

import java.util.List;

/**
 * @author 陈泽群
 */
public class RegisterServerImpl implements RegisterServer {
    RegisterDao registerDao = new RegisterDaoImpl();

    @Override
    public int registerAdd(Register register) {
        return registerDao.registerAdd(register);
    }

    @Override
    public int updateRegister(Register register) {
        return registerDao.updateRegister(register);
    }

    @Override
    public int delRegister(int id) {
        return registerDao.delRegister(id);
    }

    @Override
    public List<Register> findAllRegister() {
        return registerDao.findAllRegister();
    }

    @Override
    public Register findRegister(int id) {
        return registerDao.findRegister(id);
    }

    @Override
    public List<Register> PageAllRegister(Page page) {
        return registerDao.PageAllRegister(page);
    }
}
