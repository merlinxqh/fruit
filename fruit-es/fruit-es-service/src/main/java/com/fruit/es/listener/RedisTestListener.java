package com.fruit.es.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;


/**
 * @author leo
 * redis定时调度监听器
 */
public class RedisTestListener extends KeyspaceEventMessageListener{
	
	private static Logger logger=LoggerFactory.getLogger(RedisTestListener.class);

	public RedisTestListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
		
	}

	@Override
	protected void doHandleMessage(Message message) {
		String key = new String(message.getBody());	
		logger.info("redis key is {}...",key);
	}
}
