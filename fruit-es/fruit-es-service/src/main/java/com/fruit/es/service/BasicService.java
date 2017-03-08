package com.fruit.es.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BasicService<T> {
	public Logger logger = LoggerFactory.getLogger(BasicService.class);
	
	/**
	 * 查询是否存在索引，不存在则创建
	 * 	如果创建了索引，那么就返回true，没创建就返回false
	 * @param clazz
	 */
	public boolean checkAndCreate(Class<?> clazz);
	
	/**
	 * 存储单个对象
	 * @param t
	 * @param id
	 * @return
	 */
	public String persistObj(T t, String id);
	
	/**
	 * 根据id查询单个对象
	 * @param clazz
	 * @param idName id名称
	 * @param id
	 * @return
	 */
	public T findObjectById(Class<T> clazz, String idName, String id);
	
	/**
	 * 根据ES键值对查找集合
	 * @param clazz
	 * @param key
	 * @param value
	 * @return
	 */
	public List<T> findObjects(Class<T> clazz, String key, String value);
	
	/**
	 * 根据id删除对象
	 * @param clazz
	 * @param goodsId
	 */
	public void removeObjById(Class<?> clazz, String id);
	
	/**
	 * 删除所有索引
	 * @param indexName
	 */
	public void removeIndex(String indexName);
	
	/**
	 * 清除索引数据
	 * @param clazz
	 */
	public void clearData(Class<?> clazz);
}
