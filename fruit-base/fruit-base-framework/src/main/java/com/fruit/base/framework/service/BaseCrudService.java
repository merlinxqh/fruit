package com.fruit.base.framework.service;

import java.util.List;
import java.util.Map;

import com.fruit.base.framework.exception.ServiceException;
import com.fruit.base.framework.mybatis.page.Page;

/**
 * crud对应Service接口
 * 
 * @author zhugj
 * @version 2015年5月25日
 */
public interface BaseCrudService {
	
	public <T> int deleteById(T modelType) throws ServiceException;

	public <T> int add(T modelType) throws ServiceException;
	
	public <T> T findById(T modelType) throws ServiceException;
	public <T> T findById(String id) throws ServiceException;
	
	/**
	 * 根据参数查询
	 * 
	 * @param modelType 固定参数
	 * @param params    页面其他参数
	 * @return
	 * @throws ServiceException
	 */
	public <T> List<T> findByBiz(Map<String, Object> params) throws ServiceException;

	/**
	 * 根据id修改实体
	 * 
	 * @param modelType
	 * @return
	 * @throws ServiceException
	 */
	public <T> int modifyById(T modelType) throws ServiceException;

	/**
	 * 根据参数查询总记录数
	 * 
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public int findCount(Map<String, Object> params) throws ServiceException;

	/**
	 * 根据参数查询列表
	 * 
	 * @param page
	 * @param orderByField
	 * @param orderBy
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public <T> List<T> findByPage(Page page, String orderByField,
			String orderBy, Map<String, Object> params) throws ServiceException;
	
	/**
	 * 根据参数查询列表
	 * 
	 * simpleCountSql值为true表示使用简单的count(1)提高性能
	 * 值为false表示不执行count说语句，在业务代码里自于处理。
	 * 
	 * @param page
	 * @param orderByField
	 * @param orderBy
	 * @param params
	 * @param simpleCountSql
	 * @return
	 * @throws ServiceException
	 */
	public <T> List<T> findByPage(Page page, String orderByField,
			String orderBy, Map<String, Object> params, Boolean simpleCountSql) throws ServiceException;

}
