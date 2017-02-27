package com.fruit.cache.util;


/**
 * RedisLock
 * 
 * 实现分布式锁，及锁的等待。
 * 
 *
 * 2016年7月2日 下午5:09:24
 */
public class RedisLock {
	
	private RedisUtil redisUtil;

	/**
	 * 锁的key
	 */
	private String lockKey;

	/**
	 * 过期时间(单位为：毫秒)
	 */
	private int expireMsecs = 60 * 1000;

	/**
	 * 超时时间(单位为：毫秒)
	 */
	private int timeoutMsecs = 10 * 1000;
	
	/**
	 * 等待时间(单位为：毫秒)
	 */
	private int sleepMsecs = 100;

	/**
	 * 锁的标识
	 * 默认false表示没有获取锁，true表示获取到锁
	 */
	private boolean locked = false;

	private int parentHashCode = 0;

	public RedisLock(RedisUtil redisUtil, String lockKey) {
		this.redisUtil = redisUtil;
		this.lockKey = lockKey;
	}

	public RedisLock(RedisUtil redisUtil, String lockKey, int timeoutMsecs) {
		this(redisUtil, lockKey);
		this.timeoutMsecs = timeoutMsecs;
	}

	public RedisLock(RedisUtil redisUtil, String lockKey, int timeoutMsecs, int expireMsecs) {
		this(redisUtil, lockKey, timeoutMsecs);
		this.expireMsecs = expireMsecs;
	}
	
	public RedisLock(RedisUtil redisUtil, String lockKey, int timeoutMsecs, int expireMsecs, int sleepMsecs) {
		this(redisUtil, lockKey, timeoutMsecs, expireMsecs);
		this.sleepMsecs = sleepMsecs;
	}

	public RedisLock(String lockKey) {
		this(null, lockKey);
	}

	public RedisLock(String lockKey, int timeoutMsecs) {
		this(null, lockKey, timeoutMsecs);
	}

	public RedisLock(String lockKey, int timeoutMsecs, int expireMsecs) {
		this(null, lockKey, timeoutMsecs, expireMsecs);
	}
	
	public RedisLock(String lockKey, int timeoutMsecs, int expireMsecs, int sleepMsecs) {
		this(null, lockKey, timeoutMsecs, expireMsecs, sleepMsecs);
	}

	public String getLockKey() {
		return lockKey;
	}

	public boolean isLocked() {
		return locked;
	}

	public int getParentHashCode() {
		return parentHashCode;
	}

	public void setParentHashCode(int parentHashCode) {
		this.parentHashCode = parentHashCode;
	}

	/**
	 * 获取锁
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized boolean acquire() throws InterruptedException {
		return acquire(redisUtil);
	}

	/**
	 * 获取锁
	 * @param redisUtil
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized boolean acquire(RedisUtil redisUtil) throws InterruptedException {
		int timeout = timeoutMsecs;
		
		//循环判断超时时间
		while (timeout >= 0) {
			long expires = System.currentTimeMillis() + expireMsecs + 1;
			String expiresStr = String.valueOf(expires);

			if (redisUtil.setNX(lockKey, expiresStr)) {
				//获取锁
				locked = true;
				return true;
			}

			String currentValueStr = (String)redisUtil.get(lockKey);
			if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
				//锁过期
				String oldValueStr = (String)redisUtil.getAndSet(lockKey, expiresStr);
				if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
					//再次获取锁
					locked = true;
					return true;
				}
			}

			timeout -= sleepMsecs;
			Thread.sleep(sleepMsecs);
		}

		return false;
	}

	/**
	 * 释放锁
	 */
	public synchronized void release() {
		release(redisUtil);
	}

	/**
	 * 释放锁
	 */
	public synchronized void release(RedisUtil redisUtil) {
		if (locked) {
			redisUtil.remove(lockKey);
			locked = false;
		}
	}

}
