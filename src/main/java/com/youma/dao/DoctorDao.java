package com.youma.dao;

import com.youma.util.Page;
import com.youma.vo.Doctor;
import com.youma.vo.Register;

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
    public List<Doctor> findAllDoctor(String depName, Page page);

    /**
     * 查看医生是否存在
     *
     * @param name
     * @return
     */
    public int findDocName(String name);

    /**
     * 根据医生编号,医生姓名,科室名查询医生信息条数
     *
     * @param args
     * @return
     */
    public int allDoctorCount(String[] args);

    /**
     * 根据医生编号,医生姓名,科室名分页查询医生信息
     *
     * @param args
     * @param page
     * @return
     */
    public List<Doctor> findAllDoctor(String[] args, Page page);

    /**
     * 根据医生编号查询病患(已挂号)
     *
     * @param id 医生编号
     * @return
     */
    public List<Register> findAllReg(int id);

}
