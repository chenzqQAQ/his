package com.youma.his.dao;

import com.youma.his.vo.Role;

import java.util.List;

/**
 * 角色表操作接口
 *
 * @author Administrator
 */
public interface RoleDao {
    /**
     * 角色添加操作
     *
     * @param role 角色
     * @return 影响行数
     */
    public int roleAdd(Role role);

    /**
     * 修改角色信息操作
     *
     * @param role 角色
     * @return 影响行数
     */
    public int updateRole(Role role);

    /**
     * 删除角色操作
     *
     * @param id 角色id
     * @return 影响行数
     */
    public int delRole(int id);

    /**
     * 查询所有角色
     *
     * @return 角色集合
     */
    public List<Role> findAllRole();

    /**
     * 查找指定角色
     *
     * @param id 角色id
     * @return 指定角色信息
     */
    public Role findRole(int id);
}
