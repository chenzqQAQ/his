/**
 * Copyright (C),2015-2018
 * FileNmae: InpServerImpl
 * Author:   Administrator
 * Date:     2018/9/139:42
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.InpatientDao;
import com.youma.dao.impl.InpatientDaoImpl;
import com.youma.server.InpServer;
import com.youma.util.Page;
import com.youma.vo.Inpatient;

import java.util.List;

/**
 * @author 陈泽群
 */
public class InpServerImpl implements InpServer {
    private InpatientDao inpatientDao = new InpatientDaoImpl();

    @Override
    public int inpatientAdd(Inpatient inpatient) {
        return inpatientDao.inpatientAdd(inpatient);
    }

    @Override
    public int updateInpatient(Inpatient inpatient) {
        return inpatientDao.updateInpatient(inpatient);
    }

    @Override
    public int delInpatient(int id) {
        return inpatientDao.delInpatient(id);
    }

    @Override
    public List<Inpatient> findAllInpatient() {
        return inpatientDao.findAllInpatient();
    }

    @Override
    public Inpatient findInpatient(int id) {
        return inpatientDao.findInpatient(id);
    }

    @Override
    public int inpCount() {
        return inpatientDao.inpCount();
    }

    @Override
    public List<Inpatient> findInp(Page page) {
        return inpatientDao.findInp(page);
    }
}
