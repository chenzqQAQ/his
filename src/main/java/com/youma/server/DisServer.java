package com.youma.server;

import com.youma.util.Page;
import com.youma.vo.DispensedDrug;

import java.util.Date;
import java.util.List;

/**
 * DisServer
 * TODO(描述类的作用)
 *
 * @author 陈泽群
 * @date 2018/9/17 11:10
 */
public interface DisServer {
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
    public int disCount(DispensedDrug d);

    /**
     * 分页查询
     *
     * @return
     */
    public List<DispensedDrug> findAllDispensedDrug(DispensedDrug d,Page page);
    /**
     * 查询指定病历号的发药信息
     *
     * @param id 发药id
     * @return 集合
     */
    public List<DispensedDrug> findDispensedDrug(int id);
    /**
     * 给用户发药
     * @param dispensedDrug
     * @return
     */
    public int disDrug(DispensedDrug dispensedDrug);

    /**
     * 付款更新日期
     * @param id 病历号
     * @param date 日期
     * @return 影响行数
     */
    public int pay(int id, Date date);
}
