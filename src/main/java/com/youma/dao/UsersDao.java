package com.youma.dao;

import com.youma.util.Page;
import com.youma.vo.Users;

import java.util.List;


/**
 * 用户表数据库操作接口
 *
 * @author Administrator
 */
public interface UsersDao {
    /**
     * 添加用户
     *
     * @param user 用户实体类
     * @return int 影响行数
     */
    public int userAdd(Users user);

    /**
     * 修改用户信息
     *
     * @param user 用户实体类
     * @return int 影响行数
     */
    public int updateUsers(Users user);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return int 影响行数
     */
    public int delUsers(int id);

    /**
     * 获取所有用户信息
     *
     * @return List<Users> 用户集合
     */
    public List<Users> findAllUsers();
    /**
     * 获取所有用户信息条数
     *
     * @return int
     */
    public int allUsersCount();
    /**
     * 获取所有用户信息
     *
     * @return List<Users> 用户集合
     */
    public List<Users> findAllUsers(Page page);

    /**
     * 查询指定用户信息
     *
     * @param id 用户id
     * @return 用户实体类
     */
    public Users findUsers(int id);

    /**
     * 查询是否有用户名
     *
     * @param userName 用户名字
     * @return 用户实体类
     */
    public int findUsers(String userName);

}
