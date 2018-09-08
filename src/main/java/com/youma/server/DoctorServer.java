/**
 * Copyright (C),2015-2018
 * FileNmae: DoctorServer
 * Author:   Administrator
 * Date:     2018/9/518:59
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server;

import com.youma.vo.Doctor;

import java.util.List;

/**
 * @author 陈泽群
 */
public interface DoctorServer {
    /**
     * 添加医生信息
     *
     * @param doctor
     * @return
     */
    public int doctorAdd(Doctor doctor);

    /**
     * 更新医生信息
     *
     * @param doctor
     * @return
     */
    public int updateDoctor(Doctor doctor);

    /**
     * 删除医生信息
     *
     * @param id 医生id
     * @return
     */
    public int delDoctor(int id);

    /**
     * 查询全部医生信息
     *
     * @return
     */
    public List<Doctor> findAllDoctor();

    /**
     * 查询指定医生信息
     *
     * @param id
     * @return
     */
    public Doctor findDoctor(int id);
    /**
     * 查询指定科室医生信息
     * @param id
     * @return
     */
    public List<Doctor> findDoctorByDep(int id);
}
