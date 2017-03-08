package com.fruit.es.service.impl;


import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import com.fruit.es.service.BasicService;

public abstract class BasicServiceImpl<T> implements BasicService<T> {
	@Autowired
	protected ElasticsearchTemplate elasticsearchTemplate;
	
	//每个继承的接口都必须实现初始化的方法
	protected abstract void init();
	
	@Override
	public boolean checkAndCreate(Class<?> clazz) {
		if(!elasticsearchTemplate.indexExists(clazz)) {
			elasticsearchTemplate.createIndex(clazz);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String persistObj(T t, String id) {
		IndexQuery index = new IndexQueryBuilder().withId(id).withObject(t).build();
		checkAndCreate(t.getClass());
		elasticsearchTemplate.putMapping(t.getClass());
		String result = elasticsearchTemplate.index(index);
		return result;
	}
	
	@Override
	public T findObjectById(Class<T> clazz, String idName, String id) {
		Criteria criteria = Criteria.where(idName).is(id);
		CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
		
		T t = elasticsearchTemplate.queryForObject(criteriaQuery, clazz);
		return t;
	}
	
	@Override
	public List<T> findObjects(Class<T> clazz, String key, String value) {
		Criteria criteria = Criteria.where(key).is(value);
		CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
		List<T> ts = elasticsearchTemplate.queryForList(criteriaQuery, clazz);
		return ts;
	}
	
	@Override
	public void removeObjById(Class<?> clazz, String id) {
		elasticsearchTemplate.delete(clazz, id);
	}
	
	@Override
	public void removeIndex(String indexName) {
		if(elasticsearchTemplate.indexExists(indexName))
			elasticsearchTemplate.deleteIndex(indexName);
	}
	
	@Override
	public void clearData(Class<?> clazz) {
		DeleteQuery deleteQuery = new DeleteQuery();
		
		QueryBuilder qb = QueryBuilders.matchAllQuery();
		deleteQuery.setQuery(qb);
		deleteQuery.setIndex(clazz.getAnnotation(Document.class).indexName());
		deleteQuery.setType(clazz.getAnnotation(Document.class).type());
		
		elasticsearchTemplate.delete(deleteQuery);
	}
}
