package com.youma.his.dao;

import com.youma.his.vo.Resources;

import java.util.List;

/**
 * 权限资源表操作接口
 *
 * @author Administrator
 */
public interface ResourcesDao {
    /**
     * 添加权限资源信息
     *
     * @param resources 权限资源类
     * @return int 影响行数
     */
    public int resourcesAdd(Resources resources);

    /**
     * 修改权限资源信息
     *
     * @param resources 权限资源类
     * @return int 影响行数
     */
    public int uepdateResources(Resources resources);

    /**
     * 删除权限资源信息
     *
     * @param id 权限id
     * @return int 影响行数
     */
    public int delResources(int id);

    /**
     * 获取所有权限资源信息
     *
     * @return List<Resources> 权限资源集合
     */
    public List<Resources> findAllResources();

    /**
     * 查询指定权限资源信息
     *
     * @param id 权限id
     * @return 权限资源类
     */
    public Resources findResources(int id);

}
