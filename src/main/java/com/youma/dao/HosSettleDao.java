package com.youma.dao;

import com.youma.vo.HosSettle;

import java.util.List;

/**
 * 住院结算管理
 *
 * @author 陈泽群
 */
public interface HosSettleDao {
    /**
     * 住院结算信息添加操作
     *
     * @param hosSettle 住院结算信息
     * @return 影响行数
     */
    public int hosSettleAdd(HosSettle hosSettle);

    /**
     * 住院结算信息修改操作
     *
     * @param hosSettle 住院结算信息
     * @return 影响行数
     */
    public int updateHosSettle(HosSettle hosSettle);

    /**
     * 删除住院结算信息操作
     *
     * @param id 住院结算id
     * @return 影响行数
     */
    public int delHosSettle(int id);

    /**
     * 查询所有住院结算信息操作
     *
     * @return List<HosSettle> 住院结算信息集合
     */
    public List<HosSettle> findAllHosSettle();

    /**
     * 查询指定住院结算信息
     *
     * @param id 住院结算id
     * @return HosSettle 住院结算信息
     */
    public HosSettle findHosSettle(int id);
}
