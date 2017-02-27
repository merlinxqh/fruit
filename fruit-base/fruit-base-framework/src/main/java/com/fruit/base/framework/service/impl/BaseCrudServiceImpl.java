package com.fruit.base.framework.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.fruit.base.framework.dao.CrudMapper;
import com.fruit.base.framework.exception.ServiceException;
import com.fruit.base.framework.mybatis.page.Page;
import com.fruit.base.framework.service.BaseCrudService;

/**
 * 
 */
public abstract class BaseCrudServiceImpl implements BaseCrudService {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private CrudMapper mapper;
	
	@PostConstruct
	private void initConfig() {
		this.mapper = init();
	}
	
	public abstract CrudMapper init();
	
	
	@Override
	@Transactional
	public <T> int deleteById(T entity) throws ServiceException {
		try {
			return mapper.deleteByPrimarayKeyForModel(entity);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}

	@Override
	@Transactional
	public <T> int add(T entity) throws ServiceException {
		try {
			return mapper.insertSelective(entity);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}

	@Override
	public <T> T findById(T entity) throws ServiceException {
		try {
			return (T) mapper.selectByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(String id) throws ServiceException {
		try {
			return (T) mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}
	
	@Override
	public <T> List<T> findByBiz(Map<String, Object> params) throws ServiceException {
		try {
			return mapper.selectByParams(params);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}

	@Override
	@Transactional
	public <T> int modifyById(T entity) throws ServiceException {
		try {
			return mapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}

	@Override
	public int findCount(Map<String,Object> params) throws ServiceException {
		try {
			return mapper.selectCount(params);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}

	@Override
	public <T> List<T> findByPage(Page page, String orderByField,
			String orderBy,Map<String,Object> params) throws ServiceException {
		try {
			return mapper.selectByPage(page, orderByField, orderBy, params);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}
	
	@Override
	public <T> List<T> findByPage(Page page, String orderByField,
			String orderBy,Map<String,Object> params, Boolean simpleCountSql) throws ServiceException {
		try {
			return mapper.selectByPage(page, orderByField, orderBy, params, simpleCountSql);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}
}
