package com.yixiangyang.springboot.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yixiangyang.springboot.config.RedisConfig;
//import com.yixiangyang.springboot.config.SerializeUtil;
import com.yixiangyang.springboot.service.RedisService;
@Service
public class RedisServiceImpl implements RedisService {
	
	private static Logger logger = Logger.getLogger(RedisServiceImpl.class);
	
	@Resource
	private RedisTemplate<?, ?> redisTemplate;
	
	@Override  
    public boolean setString(final String key, final String value) {  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            @Override  
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;  
            }  
        });  
        return result;  
    }  

	@Override
	public String get(final String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {  
            @Override  
            public String doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] value =  connection.get(serializer.serialize(key));  
                
                return serializer.deserialize(value);  
            }  
        });  
        return result;
	}

//	@Override
//	public boolean setObject(String key, Object value) {
//		 boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
//	            @Override  
//	            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
//	            	 RedisSerializer<String> key1 = new StringRedisSerializer();
//	            	 SerializeUtil a = new SerializeUtil();
//	        		 connection.set(key1.serialize(key), a.serialize(value));
//	                return true;  
//	            }  
//	        });  
//	 return result;
//	}

	@Override
	public boolean setObject2(String key, Object value) {
		 boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
	            @Override  
	            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
	            	 RedisSerializer<String> key1 = new StringRedisSerializer();
	        		 connection.set(key1.serialize(key),  JSON.toJSONString(value).getBytes());
	                return true;  
	            }  
	        });
		return result;
	}

	@Override
	public boolean del(String key) {
		 boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
	            @Override  
	            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
	            	 RedisSerializer<String> key1 = new StringRedisSerializer();
	        		 connection.del(key1.serialize(key));
	                return true;  
	            }  
	        }); 
		 if(result){logger.info("删除"+key+"成功");}
		return result;
	}

}
