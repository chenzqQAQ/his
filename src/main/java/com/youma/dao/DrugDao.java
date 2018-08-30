package com.youma.dao;

import com.youma.vo.Drug;

import java.util.List;

/**
 * @author Administrator
 */
public interface DrugDao {
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
    public Drug findDrug(int id);
}
