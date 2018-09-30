package com.youma.server;

import com.youma.util.Page;
import com.youma.vo.Drug;

import java.util.List;

/**
 * 药品服务页面
 * @author 陈泽群
 */
public interface DrugServer {
    /**
     * 药品信息添加操作
     *
     * @param drug 药品信息
     * @return 影响行数
     */
    public int drugAdd(Drug drug);

    /**
     * 药品信息修改操作
     *
     * @param drug 药品信息
     * @return 影响行数
     */
    public int updateDrug(Drug drug);

    /**
     * 删除药品信息操作
     *
     * @param id 药品id
     * @return 影响行数
     */
    public int delDrug(int id);

    /**
     * 查询所有药品信息操作
     *
     * @return List<Drug> 药品信息集合
     */
    public List<Drug> findAllDrug();

    /**
     * 查询指定药品信息
     *
     * @param id 药品id
     * @return Drug 药品信息
     */
    public Drug findDrug(String id);

    /**
     * 查询所需要的药品信息操作(药品类型,药品名称)
     * @return List<Drug> 药品信息集合
     */
    public List<Drug> findTypeDrug(Drug drug);
    /**
     * 模糊查询药品条数(药品名称,药品类型)
     *
     * @param drug
     * @return
     */
    public int drugCount(Drug drug);

    /**
     * 模糊查询药品信息分页显示(药品名称,药品类型)
     *
     * @param drug
     * @param page
     * @return
     */
    public List<Drug> allDrug(Drug drug, Page page);
    public List<Drug> findNameDrug(String name);
}
