/**
 * Copyright (C),2015-2018
 * FileNmae: HosServer
 * Author:   Administrator
 * Date:     2018/9/19 9:37
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server;

import com.youma.vo.HosSettle;
import com.youma.vo.Inpatient;

import java.util.List;

/**
 *HosServer
 *住院结算
 *@author 陈泽群
 *@date 2018/9/19 9:37
 */
public interface HosServer{
    /**
     * 住院结算信息添加操作
     *
     * @param hosSettle 住院结算信息
     * @return 影响行数
     */
    public int hosSettleAdd(HosSettle hosSettle);
    /**
     * 根据住院信息自动添加结算表
     *
     * @param inpatient 住院结算信息
     * @return 影响行数
     */
    public int hosSettleAdd(Inpatient inpatient);

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
    /**
     * 查询所有住院结算信息操作
     *
     * @return List<HosSettle> 住院结算信息集合
     */
    public List<HosSettle> findAll();
    /**
     * 计算指定病历号的全部花费
     *
     * @param id 病历号
     * @return 修改行数
     */
    public int updateCost(int id);
}
