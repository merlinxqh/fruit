package com.fruit.pms.service.impl;

import com.fruit.base.framework.dao.CrudMapper;
import com.fruit.base.framework.service.impl.BaseCrudServiceImpl;
import com.fruit.pms.dao.mapper.ResourceMapper;
import com.fruit.pms.service.ResourceService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author leo
 * @date 2017-02-27 20:42:45
 * @version 1.0.0
 * @copyright www.leo.com
 */
@Service("resourceService")
public class ResourceServiceImpl extends BaseCrudServiceImpl implements ResourceService {

    @Resource
    private ResourceMapper resourceMapper;

    @Override
    public CrudMapper init() {
        return resourceMapper;
    }
}