package com.youma.server;

import com.youma.util.Page;
import com.youma.vo.DispensedDrug;

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
    public int disCount();

    /**
     * 分页查询
     *
     * @return
     */
    public List<DispensedDrug> findAllDispensedDrug(Page page);
}
