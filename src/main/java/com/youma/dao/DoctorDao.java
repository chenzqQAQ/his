package com.youma.dao;

import com.youma.util.Page;
import com.youma.vo.Doctor;

import java.util.List;

/**
 * @author 陈泽群
 */
public interface DoctorDao {
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
     *
     * @param id
     * @return
     */
    public List<Doctor> findDoctorByDep(int id);

    /**
     * 查询全部医生条数
     *
     * @return
     */
    public int allDoctorCount();

    /**
     * 分页查询全部医生信息
     *
     * @param page 分页信息
     * @return
     */
    public List<Doctor> findAllDoctor(Page page);

    /**
     * 查询某科室医生条数
     *
     * @param depName 科室名字
     * @return
     */
    public int allDoctorCount(String depName);

    /**
     * 分页查询某科室全部医生信息
     *
     * @param depName 科室名字
     * @return
     */
    public List<Doctor> findAllDoctor(String depName,Page page);

}
