package com.yixiangyang.springboot.service;


public interface RedisService {
	
	 public boolean setString(String key, String value);  
      
	 public String get(String key); 
	 
	 //public boolean setObject(String key,Object value);
	 
	 public  boolean setObject2(String key,Object value);
	 
	 public boolean del(String key);
}
