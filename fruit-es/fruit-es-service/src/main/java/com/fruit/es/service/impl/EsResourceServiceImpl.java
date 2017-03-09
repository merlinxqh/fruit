package com.fruit.es.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fruit.base.framework.mybatis.page.Page;
import com.fruit.es.model.EsResource;
import com.fruit.es.service.EsResourceService;

@Service("esResourceService")
public class EsResourceServiceImpl extends BasicServiceImpl<EsResource> implements EsResourceService {
	
	private static Logger logger=LoggerFactory.getLogger(EsResourceServiceImpl.class);

	@PostConstruct
	protected void init() {
		checkAndCreate(EsResource.class);
	}

	@Override
	public Page searchResource(Map<String, Object> params) {
		logger.info("搜索入参:{}", params);
		String keywords = params.get("keywords") == null ? "" : (String) params.get("keywords");
		//排序字段
		String sort = StringUtils.isEmpty((String) params.get("sort")) ? null : (String) params.get("sort");
		//是否降序排序，默认升序0
		Integer desc = params.get("desc") == null ? 1 : (Integer) params.get("desc");
		//分页下标,默认从1开始
		Integer pageIndex = params.get("pageIndex") == null ? 1 : (Integer) params.get("pageIndex");
		//分页大小，默认20
		Integer pageSize = params.get("pageSize") == null ? 20 : (Integer) params.get("pageSize");
		Pageable pageable = null;
		
		if(pageIndex > 0) {//pageIndex从0开始
			pageIndex = pageIndex - 1;
		}
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		
		if(!StringUtils.isEmpty(keywords)){
			keywords=keywords.trim();
			qb.must(QueryBuilders.boolQuery()
					.should(QueryBuilders.matchQuery("name", keywords))
					.should(QueryBuilders.matchQuery("title", keywords)));
		}
//		QueryBuilders.termQuery("",xxxxx) 
		searchQuery.withQuery(qb);
		
		//查处数据总数
		Long total = elasticsearchTemplate.count(searchQuery.build(), EsResource.class);
		logger.info("total size:{}", total);
		Page lpage=new Page();
		
		lpage.setTotalCount(total.intValue());
		if(total > 0) {
			//组装分页对象
			pageable = new PageRequest(pageIndex, pageSize);
			searchQuery.withPageable(pageable);
			
			//是否排序
			if(null != sort) {
				SortBuilder sortBuilder = new FieldSortBuilder(sort);
				sortBuilder.order(desc == 1 ? SortOrder.DESC : SortOrder.ASC);
				searchQuery.withSort(sortBuilder);
			}
			org.springframework.data.domain.Page<EsResource> page = elasticsearchTemplate.queryForPage(searchQuery.build(), EsResource.class);
			lpage.setRecords(page.getContent());
		}
		lpage.setPageIndex(pageIndex + 1);
		lpage.setPageSize(pageSize);
		return lpage;
	}
}
