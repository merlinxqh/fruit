package com.fruit.es.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fruit.es.model.EsResource;
import com.fruit.es.service.EsResourceService;

@Service("esResourceService")
public class EsResourceServiceImpl extends BasicServiceImpl<EsResource> implements EsResourceService {

	@PostConstruct
	protected void init() {
		checkAndCreate(EsResource.class);
	}
}
