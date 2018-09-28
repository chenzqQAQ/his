package com.youma.dao;

import com.youma.util.Page;
import com.youma.vo.Role;

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
     * 分页查询所有角色
     *
     * @return 角色集合
     */
    public List<Role> findAllRole(Role role,Page page);

    /**
     * 角色条数
     *
     * @return
     */
    public int roleCount(Role role);

    /**
     * 查找指定角色
     *
     * @param id 角色id
     * @return 指定角色信息
     */
    public Role findRole(int id);

    /**
     * 查找指定角色名称的id
     *
     * @param name 名称
     * @return 指定角色信息
     */
    public int findRole(String name);

    /**
     * 删除指定用户的全部资源权限
     *
     * @param id
     * @return
     */
    public int delRes(int id);

    /**
     * 查询指定用户的全部资源权限(有效资源)
     *
     * @param id
     * @return
     */
    public Role findRes(int id);
    /**
     * 查询指定角色状态
     *
     * @param id
     * @return
     */
    public int findStatus(int id);

    /**
     * 添加用户的资源权限
     *
     * @param role
     * @return
     */
    public int addRes(Role role);
}
