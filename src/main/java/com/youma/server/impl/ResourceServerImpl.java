/**
 * Copyright (C),2015-2018
 * FileNmae: ResourceServerImpl
 * Author:   Administrator
 * Date:     2018/9/1111:06
 * History:
 * <author> <Time> <version> <desc>
 * 陈泽群  时间    版本号  描述
 */
package com.youma.server.impl;

import com.youma.dao.ResourcesDao;
import com.youma.dao.impl.ResourcesDaoImpl;
import com.youma.server.ResourceServer;
import com.youma.util.Page;
import com.youma.vo.Resources;

import java.util.List;

/**
 * @author 陈泽群
 */
public class ResourceServerImpl implements ResourceServer {
    private ResourcesDao resourcesDao = new ResourcesDaoImpl();

    @Override
    public int resourcesAdd(Resources resources) {
        return resourcesDao.resourcesAdd(resources);
    }

    @Override
    public int uepdateResources(Resources resources) {
        return resourcesDao.uepdateResources(resources);
    }

    @Override
    public int delResources(int id) {
        return resourcesDao.delResources(id);
    }

    @Override
    public List<Resources> findAllResources() {
        return resourcesDao.findAllResources();
    }

    @Override
    public Resources findResources(int id) {
        return resourcesDao.findResources(id);
    }

    @Override
    public int allResourcesCount(Resources resources) {
        return resourcesDao.allResourcesCount(resources);
    }

    @Override
    public List<Resources> findAllResources(Resources resources,Page page) {
        return resourcesDao.findAllResources(resources,page);
    }
}
