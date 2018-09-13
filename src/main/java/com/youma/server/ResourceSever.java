package com.youma.server;

import com.youma.util.Page;
import com.youma.vo.Resources;

import java.util.List;

/**
 * @author 陈泽群
 */
public interface ResourceSever {
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
    /**
     * 全部资源条数
     * @return
     */
    public int allResourcesCount();

    /**
     * 分页查询资源信息
     * @param page 分页
     * @return
     */
    public List<Resources> findAllResources(Page page);
}
