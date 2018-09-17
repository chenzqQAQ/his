package com.youma.dao;

import com.youma.util.Page;
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

    /**
     * 给一群病患发药
     *
     * @param id 数组
     * @param d  药品信息
     * @return 增加列数
     */
    public int dispensedDrugAdd(int[] id, DispensedDrug d);

    /**
     * 全部分发药物列表条数
     *
     * @return
     */
    public int disCount();

    /**
     * 分页查询
     *
     * @return
     */
    public List<DispensedDrug> findAllDispensedDrug(Page page);
}
