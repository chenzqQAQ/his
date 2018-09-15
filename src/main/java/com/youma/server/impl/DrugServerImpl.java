/**
 * Copyright (C),2015-2018
 * FileNmae: DrugServerImpl
 * Author:   Administrator
 * Date:     2018/8/3017:23
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.DrugDao;
import com.youma.dao.impl.DrugDaoImpl;
import com.youma.server.DrugServer;
import com.youma.util.Page;
import com.youma.vo.Drug;

import java.util.List;

/**
 * @author 陈泽群
 */
public class DrugServerImpl implements DrugServer {
    DrugDao drugDao = new DrugDaoImpl();

    @Override
    public int drugAdd(Drug drug) {
        return drugDao.drugAdd(drug);
    }

    @Override
    public int updateDrug(Drug drug) {
        return drugDao.updateDrug(drug);
    }

    @Override
    public int delDrug(int id) {
        return drugDao.delDrug(id);
    }

    @Override
    public List<Drug> findAllDrug() {
        return drugDao.findAllDrug();
    }

    @Override
    public Drug findDrug(String id) {
        return drugDao.findDrug(id);
    }

    @Override
    public List<Drug> findTypeDrug(Drug drug) {
        if (null != drug.getDrugName() && "" != drug.getDrugName()) {
            // System.out.println(drug.getDrugName());
            // System.out.println(drug.getDrugType());
            return drugDao.findNameDrug(drug);
        }
        // System.out.println(drug.getDrugType());
        return drugDao.findTypeDrug(drug.getDrugType());
    }

    @Override
    public int drugCount(Drug drug) {
        return drugDao.drugCount(drug);
    }

    @Override
    public List<Drug> allDrug(Drug drug, Page page) {
        return drugDao.allDrug(drug, page);
    }

}
