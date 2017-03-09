package com.fruit.es.service;

import java.util.Map;

import com.fruit.base.framework.mybatis.page.Page;
import com.fruit.es.model.EsResource;

public interface EsResourceService extends BasicService<EsResource>{
	
	Page searchResource(Map<String, Object> params);

}
