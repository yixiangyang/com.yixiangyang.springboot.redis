package com.yixiangyang.springboot.config;

import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;
@Configuration
public class RedisConfig {
	private static Logger logger = Logger.getLogger(RedisConfig.class);
	
	@Bean
	@ConfigurationProperties(prefix="spring.redis")
	public JedisPoolConfig getRedisConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.redis")
	public JedisConnectionFactory getConnectionFactory(){
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisPoolConfig config = getRedisConfig();
		factory.setPoolConfig(config);
		logger.info("redis注册成功");
		return factory;
	}

	@Bean
	public RedisTemplate<?, ?> getRedisTemplate(){
		RedisTemplate<?, ?> template = new StringRedisTemplate(this.getConnectionFactory());
		return template;
	}
}
