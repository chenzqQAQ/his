package com.youma.dao;

import com.youma.vo.Department;

import java.util.List;

/**
 * 科室管理
 *
 * @author 陈泽群
 */
public interface DepartmentDao {
    /**
     * 添加科室
     *
     * @param department 科室实体类
     * @return int 影响行数
     */
    public int departmentAdd(Department department);

    /**
     * 修改科室信息
     *
     * @param department 科室实体类
     * @return int 影响行数
     */
    public int updateDepartment(Department department);

    /**
     * 删除科室信息
     *
     * @param id 科室id
     * @return int 影响行数
     */
    public int delDepartment(int id);

    /**
     * 获取所有科室信息
     *
     * @return List<Department> 科室集合
     */
    public List<Department> findAllDepartment();

    /**
     * 查询指定科室信息
     *
     * @param id 科室id
     * @return 科室实体类
     */
    public Department findDepartment(int id);
}
