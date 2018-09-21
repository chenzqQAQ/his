package com.youma.dao;

import com.youma.util.Page;
import com.youma.vo.PayManager;

import java.util.Date;
import java.util.List;

/**
 * @author 陈泽群
 */
public interface PayManagerDao {
    /**
     * 添加收费管理列表到数据库
     *
     * @param list
     * @return
     */
    public int addPay(List<PayManager> list);

    /**
     * 分页查询收费管理列表
     *
     * @param page
     * @return
     */
    public List<PayManager> findAll(Page page);

    /**
     * 查询收费管理列表条数
     * @return
     */
    public int count();
    /**
     * 查询某病历号的全部收费管理列表
     *
     * @param id
     * @return
     */
    public List<PayManager> findAll(int id);

    /**
     * 模糊查询收费列表信息条数
     * @param payManager
     * @return
     */
    public int allCount(PayManager payManager);

    /**
     * 模糊查询收费列表信息分页显示
     * @param payManager
     * @param page
     * @return
     */
    public  List<PayManager> findAll(PayManager payManager,Page page);

    /**
     * 付款更新日期
     * @param id 病历号
     * @param date 日期
     * @return 影响行数
     */
    public int pay(int id,Date date);
}
