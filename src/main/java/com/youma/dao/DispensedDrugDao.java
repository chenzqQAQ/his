package com.youma.dao;

import com.youma.vo.DispensedDrug;

import java.util.List;

/**
 * 发药管理
 *
 * @author 陈泽群
 */
public interface DispensedDrugDao {
    /**
     * 添加发药信息
     *
     * @param dispensedDrug 发药实体类
     * @return int 影响行数
     */
    public int dispensedDrugAdd(DispensedDrug dispensedDrug);

    /**
     * 修改发药信息
     *
     * @param dispensedDrug 发药实体类
     * @return int 影响行数
     */
    public int updateDispensedDrug(DispensedDrug dispensedDrug);

    /**
     * 删除发药信息
     *
     * @param id 发药信息id
     * @return int 影响行数
     */
    public int delDispensedDrug(int id);

    /**
     * 获取所有发药信息
     *
     * @return List<DispensedDrug> 发药信息集合
     */
    public List<DispensedDrug> findAllDispensedDrug();

    /**
     * 查询指定发药信息
     *
     * @param id 发药id
     * @return 发药实体类
     */
    public DispensedDrug findDispensedDrug(int id);
}
