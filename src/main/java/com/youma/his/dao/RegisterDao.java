/**
 * Copyright (C),2015-2018
 * FileNmae: RegisterDao
 * Author:   Administrator
 * Date:     2018/8/2816:35
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.his.dao;

import com.youma.his.vo.Register;

import java.util.List;

/**
 * 挂号信息表数据库操作接口
 * @author Administrator
 */
public interface RegisterDao {
    /**
     * 挂号信息添加操作
     * @param register 挂号信息
     * @return int 影响行数
     */
    public int registerAdd(Register register);

    /**
     * 挂号信息修改操作
     * @param register 挂号信息
     * @return int 影响行数
     */
    public int updateRegister(Register register);

    /**
     * 挂号信息删除操作
     * @param id 病历号
     * @return int 影响的行数
     */
    public int delRegister(int id);

    /**
     * 查询所有挂号信息操作
     * @return List<Register> 挂号信息集合
     */
    public List<Register> findAllRegister();

    /**
     * 查询指定病历号的挂号信息
     * @param id 病历号
     * @return 指定病历号的挂号信息
     */
    public Register findRegister(int id);
}
