package com.fruit.growup.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fruit.base.framework.dao.CrudMapper;
import com.fruit.base.framework.service.impl.BaseCrudServiceImpl;
import com.fruit.growup.dao.mapper.GrowUpPlanMapper;
import com.fruit.growup.service.GrowUpPlanService;

/**
 * 
 * @author 
 * @date 2017-02-28 21:30:59
 * @version 1.0.0
 * @copyright www.net.com
 */
@Service("growUpPlanService")
public class GrowUpPlanServiceImpl extends BaseCrudServiceImpl implements GrowUpPlanService {

    @Resource
    private GrowUpPlanMapper growUpPlanMapper;

    @Override
    public CrudMapper init() {
        return growUpPlanMapper;
    }
}