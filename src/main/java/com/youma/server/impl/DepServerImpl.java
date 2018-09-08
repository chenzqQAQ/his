/**
 * Copyright (C),2015-2018
 * FileNmae: DepServerImpl
 * Author:   Administrator
 * Date:     2018/9/711:33
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.DepartmentDao;
import com.youma.dao.impl.DepartmentDaoImpl;
import com.youma.server.DepServer;
import com.youma.vo.Department;

import java.util.List;

/**
 * @author 陈泽群
 */
public class DepServerImpl implements DepServer {
    DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public int departmentAdd(Department department) {
        return 0;
    }

    @Override
    public int updateDepartment(Department department) {
        return 0;
    }

    @Override
    public int delDepartment(int id) {
        return 0;
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentDao.findAllDepartment();
    }

    @Override
    public Department findDepartment(int id) {
        return departmentDao.findDepartment(id);
    }
}
