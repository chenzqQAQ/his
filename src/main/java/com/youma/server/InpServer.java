package com.youma.server;

import com.youma.util.Page;
import com.youma.vo.Inpatient;

import java.util.List;

/**
 * @author 陈泽群
 */
public interface InpServer {
    /**
     * 添加住院信息
     *
     * @param inpatient 住院信息类
     * @return int 影响行数
     */
    public int inpatientAdd(Inpatient inpatient);

    /**
     * 修改住院信息
     *
     * @param inpatient 住院信息类
     * @return int 影响行数
     */
    public int updateInpatient(Inpatient inpatient);

    /**
     * 删除住院信息
     *
     * @param id 住院信息id
     * @return int 影响行数
     */
    public int delInpatient(int id);

    /**
     * 获取所有住院信息信息
     *
     * @return List<Inpatient> 住院信息集合
     */
    public List<Inpatient> findAllInpatient();

    /**
     * 查询指定住院信息信息
     *
     * @param id 住院信息id
     * @return 住院信息实体类
     */
    public Inpatient findInpatient(int id);
    /**
     * 全部住院信息条数
     * @return 全部住院信息条数
     */
    public int inpCount(Inpatient inpatient);

    /**
     * 分页查询住院信息
     * @return
     */
    public List<Inpatient> findInp(Inpatient inpatient,Page page);
}
