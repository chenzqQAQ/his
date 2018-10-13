/**
 * Copyright (C),2015-2018
 * FileNmae: DoctorServerImpl
 * Author:   Administrator
 * Date:     2018/9/519:00
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.DoctorDao;
import com.youma.dao.impl.DoctorDaoImpl;
import com.youma.server.DoctorServer;
import com.youma.util.Page;
import com.youma.vo.Doctor;
import com.youma.vo.Register;

import java.util.List;

/**
 * @author 陈泽群
 */
public class DoctorServerImpl implements DoctorServer {
    DoctorDao doctorDao = new DoctorDaoImpl();

    @Override
    public int doctorAdd(Doctor doctor) {
        return doctorDao.doctorAdd(doctor);
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        return doctorDao.updateDoctor(doctor);
    }

    @Override
    public int delDoctor(int id) {
        return doctorDao.delDoctor(id);
    }

    @Override
    public List<Doctor> findAllDoctor() {
        return doctorDao.findAllDoctor();
    }

    @Override
    public Doctor findDoctor(int id) {
        return doctorDao.findDoctor(id);
    }

    @Override
    public List<Doctor> findDoctorByDep(int id) {
        return doctorDao.findDoctorByDep(id);
    }

    @Override
    public int allDoctorCount() {
        return doctorDao.allDoctorCount();
    }

    @Override
    public List<Doctor> findAllDoctor(Page page) {
        return doctorDao.findAllDoctor(page);
    }

    @Override
    public int allDoctorCount(String depName) {
        return doctorDao.allDoctorCount(depName);
    }

    @Override
    public List<Doctor> findAllDoctor(String depName, Page page) {
        return doctorDao.findAllDoctor(depName, page);
    }

    @Override
    public int findDocName(String name) {
        return doctorDao.findDocName(name);
    }

    @Override
    public int allDoctorCount(String[] args) {
        return doctorDao.allDoctorCount(args);
    }

    @Override
    public List<Doctor> findAllDoctor(String[] args, Page page) {
        return doctorDao.findAllDoctor(args, page);
    }

    @Override
    public List<Register> findAllReg(int id) {
        return doctorDao.findAllReg(id);
    }
}
