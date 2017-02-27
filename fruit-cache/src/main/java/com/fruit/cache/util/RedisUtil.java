package com.fruit.cache.util;


import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.fruit.base.framework.utils.json.JsonMapper;

/**
 * RedisUtil
 */
@Component
public class RedisUtil {

	@Resource
	private RedisTemplate<Serializable, Object> redisTemplate;

	@Resource
	private RedisTemplate<Serializable, Object> incRedisTemplate;
	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0) {
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 自增操作的get方法
	 * @param key
	 * @return
	 */
	public Object incGet(final String key) {
		return incRedisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 写入缓存
	 * 自增操作的set方法
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean incSet(final String key, Object value) {
		return incSet(key, value, null);
	}
	
	/**
	 * 自增
	 * @param key
	 * @param value
	 * @return
	 */
	public Long increment(String key, long value)
	{
		return redisTemplate.opsForValue().increment(key,value);
	}
	
	/**
	 * 自减
	 * @param key
	 * @param value
	 * @return
	 */
	public Long decrease(String key, long value)
	{
		return redisTemplate.opsForValue().increment(key,-value);
	}
	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public <T>T get(final String key,Class<T> cls) {
		return JsonMapper.fromJsonString(redisTemplate.opsForValue().get(key).toString(), cls);
	}
	
	/**
	 * 
	* @Title: get 
	* @Description: 集合获取 
	* @param  key
	* @param  collectionClass
	* @param  classes
	* @param     设定文件 
	* @return T    返回类型 
	* @throws
	 */
	public <T>T get(final String key,Class<?> collectionClass,Class<?> ...classes){
		String value=get(key,String.class);
		return JsonMapper.json2Collection(value,collectionClass, classes);
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		return set(key, value, null);
	}

	/**
	 * 
	* @Title: set 
	* @Description: 写入缓存带有效期
	* @param  key
	* @param  value
	* @param  expireTime
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			if(expireTime!=null){
				redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 用于自增操作 的set 方法
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return
	 */
	public boolean incSet(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = incRedisTemplate.opsForValue();
			operations.set(key, value);
			if(expireTime!=null){
				incRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 写入缓存 （json方式存储）
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setAsJson(final String key, Object value) {
		return setAsJson(key, value, null);
	}

	/**
	 * 写入缓存（json方式存储）
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setAsJson(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, JsonMapper.toJsonString(value));
			if(expireTime!=null){
				redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 写入list 永久缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public Long pushList(final String key, Object value){
		Long res = (long) 0;
		try {
			res = redisTemplate.opsForList().leftPush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 入栈 （redis写入格式为list）
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long pushList(final String key, Object value, Long expireTime) {
		Long res = (long) 0;
		try {
			res = redisTemplate.opsForList().leftPush(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 检索
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Object> rangeList(final String key, int start, int end) {
		List<Object> list = null;
		try {
			list = redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean setNX(String key, Object value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}
	
	public Object getAndSet(String key, Object value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}
	
	/**
	 * 将键值对设定一个指定的时间timeout.
	 * @param key
	 * @param timeout 键值对缓存的时间，单位是毫秒
	 * @return 设置成功返回true，否则返回false
	 */
	public boolean tryLock(String key, long timeout) {
		boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(key, "");
		if(isSuccess) {
			redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
		}
		return isSuccess;
	}
}
