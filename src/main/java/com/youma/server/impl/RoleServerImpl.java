/**
 * Copyright (C),2015-2018
 * FileNmae: RoleServerImpl
 * Author:   Administrator
 * Date:     2018/9/18 11:05
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.RoleDao;
import com.youma.dao.impl.RoleDaoImpl;
import com.youma.server.RoleServer;
import com.youma.util.Page;
import com.youma.vo.Role;

import java.util.List;

/**
 * RoleServerImpl
 * TODO(描述类的作用)
 *
 * @author 陈泽群
 * @date 2018/9/18 11:05
 */
public class RoleServerImpl implements RoleServer {
    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public int roleAdd(Role role) {
        return roleDao.roleAdd(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public int delRole(int id) {
        return roleDao.delRole(id);
    }

    @Override
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    public Role findRole(int id) {
        return roleDao.findRole(id);
    }

    @Override
    public int delRes(int id) {
        return roleDao.delRes(id);
    }

    @Override
    public int addRes(Role role) {
        return roleDao.addRes(role);
    }

    @Override
    public int findRole(String name) {
        return roleDao.findRole(name);
    }

    @Override
    public Role findRes(int id) {
       Role role=roleDao.findRes(id);
       role.setStatus(roleDao.findStatus(id));
       return role;
    }

    @Override
    public List<Role> findAllRole(Role role,Page page) {
        return roleDao.findAllRole(role,page);
    }

    @Override
    public int roleCount(Role role) {
        return roleDao.roleCount(role);
    }
}
