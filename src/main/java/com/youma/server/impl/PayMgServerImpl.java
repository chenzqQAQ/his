/**
 * Copyright (C),2015-2018
 * FileNmae: PayMgServerImpl
 * Author:   Administrator
 * Date:     2018/9/14 10:56
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.PayManagerDao;
import com.youma.dao.impl.PayManagerDaoImpl;
import com.youma.server.PayMgServer;
import com.youma.util.Page;
import com.youma.vo.PayManager;

import java.util.Date;
import java.util.List;

/**
 * PayMgServerImpl
 * TODO(描述类的作用)
 *
 * @author 陈泽群
 * @date 2018/9/14 10:56
 */
public class PayMgServerImpl implements PayMgServer {
    private PayManagerDao payManagerDao = new PayManagerDaoImpl();

    @Override
    public int addPay(List<PayManager> list) {
        return payManagerDao.addPay(list);
    }

    @Override
    public List<PayManager> findAll(Page page) {
        return payManagerDao.findAll(page);
    }

    @Override
    public int count() {
        return payManagerDao.count();
    }

    @Override
    public List<PayManager> findAll(int id) {
        return payManagerDao.findAll(id);
    }

    @Override
    public int allCount(PayManager payManager) {
        return payManagerDao.allCount(payManager);
    }

    @Override
    public List<PayManager> findAll(PayManager payManager, Page page) {
        return payManagerDao.findAll(payManager, page);
    }

    @Override
    public int pay(int id, Date date) {
        return payManagerDao.pay(id,date);
    }
}
