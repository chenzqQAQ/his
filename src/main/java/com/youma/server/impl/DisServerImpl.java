/**
 * Copyright (C),2015-2018
 * FileNmae: DisServerImpl
 * Author:   Administrator
 * Date:     2018/9/17 11:10
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.DispensedDrugDao;
import com.youma.dao.impl.DispensedDrugDaoImpl;
import com.youma.server.DisServer;
import com.youma.util.Page;
import com.youma.vo.DispensedDrug;

import java.util.Date;
import java.util.List;

/**
 * DisServerImpl
 * TODO(描述类的作用)
 *
 * @author 陈泽群
 * @date 2018/9/17 11:10
 */
public class DisServerImpl implements DisServer {
    private DispensedDrugDao dispensedDrugDao = new DispensedDrugDaoImpl();

    @Override
    public int dispensedDrugAdd(int[] id, DispensedDrug d) {
        return dispensedDrugDao.dispensedDrugAdd(id, d);
    }

    @Override
    public int disCount(DispensedDrug d) {
        return dispensedDrugDao.disCount(d);
    }

    @Override
    public List<DispensedDrug> findAllDispensedDrug(DispensedDrug d,Page page) {
        return dispensedDrugDao.findAllDispensedDrug(d,page);
    }

    @Override
    public List<DispensedDrug> findDispensedDrug(int id) {
        return dispensedDrugDao.findDispensedDrug(id);
    }

    @Override
    public int disDrug(DispensedDrug dispensedDrug) {
        return dispensedDrugDao.disDrug(dispensedDrug);
    }

    @Override
    public int pay(int id, Date date) {
        return dispensedDrugDao.pay(id,date);
    }

}
