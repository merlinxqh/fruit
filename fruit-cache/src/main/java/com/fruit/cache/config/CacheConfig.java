package com.fruit.cache.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fruit.cache.util.Hessian2SerializationRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * cache
 * @author leo
 *
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.fruit.cache"})
public class CacheConfig {
	
	@Value("${redis.master.host}")
	private String masterHost;
	
	@Value("${redis.master.name}")
	private String masterName;
	
	@Value("${redis.master.port}")
	private int masterPort;
	
	//监控节点1
	@Value("${redis.sentinels.n1.host}")
	private String sentinelN1Host;
	
	@Value("${redis.sentinels.n1.port}")
	private int sentinelN1Port;
	
	//监控节点2
	@Value("${redis.sentinels.n2.host}")
	private String sentinelN2Host;
	
	@Value("${redis.sentinels.n2.port}")
	private int sentinelN2Port;
	
	@Value("${redis.pool.minIdle}")
	private int minIdle;
	
	@Value("${redis.pool.maxIdle}")
	private int maxIdle;
	
	@Value("${redis.pool.maxWaitMillis}")
	private long maxWaitMillis;
	
	@Value("${redis.pool.testOnBorrow}")
	private boolean testOnBorrow;
	
	@Value("${redis.pool.testOnReturn}")
	private boolean testOnReturn;
	
	@Value("${redis.pool.testWhileIdle}")
	private boolean testWhileIdle;
	
	@Value("${redis.timeout}")
	private int timeout;
	
	@Value("${redis.password}")
	private String redispwd;
  
	
	/**
	 * Jedis 连接池配置
	 * @return
	 */
	@Bean(name="jedisPoolConfig")
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMinIdle(minIdle);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnReturn(testOnReturn);
		config.setTestWhileIdle(testWhileIdle);
		return config;
	}
	
	/**
	 * jedis连接工厂配置
	 * @return
	 */
	@Bean(name="jedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory(){
		JedisConnectionFactory factory=new JedisConnectionFactory(redisSenitnelConfiguration(), jedisPoolConfig());
		factory.setTimeout(timeout);
		factory.setPassword(redispwd);
		return factory;
	}
	
	
	@Bean
	public RedisSentinelConfiguration redisSenitnelConfiguration(){
		RedisSentinelConfiguration sentinel=new RedisSentinelConfiguration();
		//redis主库节点
		RedisNode masterNode=new RedisNode(masterHost, masterPort);
		masterNode.setName(masterName);
		sentinel.setMaster(masterNode);
		//sentinel监听节点
		RedisNode sentinelNode1=new RedisNode(sentinelN1Host, sentinelN1Port);
		RedisNode sentinelNode2=new RedisNode(sentinelN2Host, sentinelN2Port);
		List<RedisNode> sentinels=new ArrayList<RedisNode>();
		sentinels.add(sentinelNode1);
		sentinels.add(sentinelNode2);
		sentinel.setSentinels(sentinels);
		return sentinel;
	}
	
	//------jedis模板
	@Bean(name="stringRedisSerializer")
	public StringRedisSerializer stringRedisSerializer(){
		return new StringRedisSerializer();
	}
	
	@Bean(name="redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String, Object> redisTemplate=new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(stringRedisSerializer());
		redisTemplate.setHashKeySerializer(stringRedisSerializer());
		return redisTemplate;
	}
	
	@Bean
	public Hessian2SerializationRedisSerializer hessian2Serializer(){
		Hessian2SerializationRedisSerializer serializer=new Hessian2SerializationRedisSerializer();
        return serializer;
	}
	
	@Bean(name="incRedisTemplate")
	public RedisTemplate<String, Object> incRedisTemplate(){
		RedisTemplate<String, Object> redisTemplate=new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(stringRedisSerializer());
		redisTemplate.setHashKeySerializer(stringRedisSerializer());
		redisTemplate.setDefaultSerializer(hessian2Serializer());
		return redisTemplate;
	}
}
