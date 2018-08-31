package com.youma.dao;

import com.youma.vo.PayProject;

import java.util.List;

/**
 * 收费项目管理
 *
 * @author 陈泽群
 */
public interface PayProjectDao {
    /**
     * 添加收费项目
     *
     * @param payProject 收费项目实体类
     * @return int 影响行数
     */
    public int payProjectAdd(PayProject payProject);

    /**
     * 修改收费项目信息
     *
     * @param payProject 收费项目实体类
     * @return int 影响行数
     */
    public int updatePayProject(PayProject payProject);

    /**
     * 删除收费项目信息
     *
     * @param id 收费项目id
     * @return int 影响行数
     */
    public int delPayProject(int id);

    /**
     * 获取所有收费项目信息
     *
     * @return List<Users> 收费项目集合
     */
    public List<PayProject> findAllPayProject();

    /**
     * 查询指定用户信息
     *
     * @param id 收费项目id
     * @return 收费项目实体类
     */
    public PayProject findPayProject(int id);
}
