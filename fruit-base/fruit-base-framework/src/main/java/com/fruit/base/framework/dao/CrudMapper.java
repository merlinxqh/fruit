package com.fruit.base.framework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fruit.base.framework.mybatis.page.Page;



/**
 * DAO支持类
 * @author zhugj
 * @date 2015-05-19
 * @param <T>
 */
public interface CrudMapper {
	
	public int deleteByPrimaryKey(String id);
	
	public <T> int insert(T entity);
	
	public <T> int insertSelective(T entity);
	
	public <T> T selectByPrimaryKey(T entity);
	public <T> T selectByPrimaryKey(String id);
	
	public <T> List<T> selectByParams(@Param("params") Map<String, Object> params);
	
	public <T> int updateByPrimaryKeySelective(T entity);
	
	public <T> int updateByPrimaryKey(T entity);
	
	public int selectCount(@Param("params") Map<String, Object> params);
	
	public <T> List<T> selectByPage(@Param("page") Page page,
			@Param("orderByField") String orderByField,
			@Param("orderBy") String orderBy,
			@Param("params") Map<String, Object> params);
	
	/**
	 * simpleCountSql值为true表示使用简单的count(1)提高性能
	 * 值为false表示不执行count说语句，在业务代码里自于处理。
	 * 
	 * @param page
	 * @param orderByField
	 * @param orderBy
	 * @param params
	 * @param simpleCountSql
	 * @return
	 */
	public <T> List<T> selectByPage(@Param("page") Page page,
			@Param("orderByField") String orderByField,
			@Param("orderBy") String orderBy,
			@Param("params") Map<String, Object> params, 
			@Param("simpleCountSql") Boolean simpleCountSql);
	
	public <T> int deleteByPrimarayKeyForModel(T entity);
}