package com.youma.server;

import com.youma.vo.Department;

import java.util.List;

/**
 * @author 陈泽群
 */
public interface DepServer {
    /**
     * 添加科室信息
     *
     * @param department
     * @return
     */
    public int departmentAdd(Department department);

    /**
     * 更新科室信息
     *
     * @param department
     * @return
     */
    public int updateDepartment(Department department);

    /**
     * 删除科室信息
     *
     * @param id 医生id
     * @return
     */
    public int delDepartment(int id);

    /**
     * 查询全部科室信息
     *
     * @return
     */
    public List<Department> findAllDepartment();

    /**
     * 查询指定科室信息
     *
     * @param id
     * @return
     */
    public Department findDepartment(int id);
}
