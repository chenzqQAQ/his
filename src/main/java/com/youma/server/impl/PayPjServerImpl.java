/**
 * Copyright (C),2015-2018
 * FileNmae: PayPjServerImpl
 * Author:   Administrator
 * Date:     2018/9/14 9:32
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.PayProjectDao;
import com.youma.dao.impl.PayProjectDaoImpl;
import com.youma.server.PayPjServer;
import com.youma.vo.PayProject;

import java.util.List;

/**
 *PayPjServerImpl
 *TODO(描述类的作用)
 *@author 陈泽群
 *@date 2018/9/14 9:32
 */
public class PayPjServerImpl implements PayPjServer {
    private PayProjectDao payProjectDao=new PayProjectDaoImpl();
    @Override
    public int payProjectAdd(PayProject payProject) {
        return payProjectDao.payProjectAdd(payProject);
    }

    @Override
    public int updatePayProject(PayProject payProject) {
        return payProjectDao.updatePayProject(payProject);
    }

    @Override
    public int delPayProject(int id) {
        return payProjectDao.delPayProject(id);
    }

    @Override
    public List<PayProject> findAllPayProject() {
        return payProjectDao.findAllPayProject();
    }

    @Override
    public PayProject findPayProject(int id) {
        return payProjectDao.findPayProject(id);
    }
}
