/**
 * Copyright (C),2015-2018
 * FileNmae: HosServerImpl
 * Author:   Administrator
 * Date:     2018/9/19 9:38
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.HosSettleDao;
import com.youma.dao.impl.HosSettleDaoImpl;
import com.youma.server.HosServer;
import com.youma.util.Page;
import com.youma.vo.HosSettle;
import com.youma.vo.Inpatient;

import java.util.Date;
import java.util.List;

/**
 * HosServerImpl
 * TODO(描述类的作用)
 *
 * @author 陈泽群
 * @date 2018/9/19 9:38
 */
public class HosServerImpl implements HosServer {
    private HosSettleDao hosSettleDao = new HosSettleDaoImpl();

    @Override
    public int hosSettleAdd(HosSettle hosSettle) {
        return hosSettleDao.hosSettleAdd(hosSettle);
    }

    @Override
    public int hosSettleAdd(Inpatient inpatient) {
        return hosSettleDao.hosSettleAdd(inpatient);
    }

    @Override
    public int updateHosSettle(HosSettle hosSettle) {
        return hosSettleDao.updateHosSettle(hosSettle);
    }

    @Override
    public int delHosSettle(int id) {
        return hosSettleDao.delHosSettle(id);
    }

    @Override
    public List<HosSettle> findAllHosSettle() {
        return hosSettleDao.findAllHosSettle();
    }

    @Override
    public HosSettle findHosSettle(int id) {
        return hosSettleDao.findHosSettle(id);
    }

    @Override
    public List<HosSettle> findAll() {
        return hosSettleDao.findAll();
    }

    @Override
    public int updateCost(int id) {
        return hosSettleDao.updateCost(id);
    }

    @Override
    public int pay(int id, Date date) {
        return hosSettleDao.pay(id, date);
    }

    @Override
    public int pay(int id, Date date, double cash) {
        return hosSettleDao.payCash(id, date, cash);
    }

    @Override
    public List<HosSettle> findAll(HosSettle hosSettle, Page page) {
        return hosSettleDao.findAll(hosSettle, page);
    }

    @Override
    public int allCount(HosSettle hosSettle) {
        return hosSettleDao.allCount(hosSettle);
    }

    @Override
    public List<HosSettle> findAllCash() {
        return hosSettleDao.findAllCash();
    }
}
