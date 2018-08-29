package com.yixiangyang.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yixiangyang.springboot.model.User;
import com.yixiangyang.springboot.service.RedisService;
import com.yixiangyang.springboot.service.UserService;

//import net.sf.json.JSONObject;

@RestController
public class RedisController {
	@Resource
	private RedisService redisService;
	@Resource
	private UserService userService;
	
	 @RequestMapping(path ="/redis/set" ,method=RequestMethod.GET,produces={"application/json"})  
	 public Map<?, ?> redisSet(@RequestParam("id")String id){  
		 System.out.println(id);
		 User user = userService.getUserInfo(id);
//		 RedisSerializer<String> key = new StringRedisSerializer();
//		 key.serialize("name"+id);
//		 
//		 RedisSerializer<Object> value = new RedisObjectSerializer();
		// value.serialize(user);
		 System.out.println(user);
		 redisService.setObject2("name"+id, user);
		// boolean isOk = redisService.setString("name"+id, "");  
		 //System.out.println(isOk);
		 Map<String, String> map = new HashMap<>();
		 return map;  
	 }  
	 
	 @RequestMapping(path ="/redis/get" ,method=RequestMethod.GET,produces={"application/json"})  
	 public Object redisGet(@RequestParam("id")String id){  
		 String redis = redisService.get("name"+id);
		 if(redis == null){return redis;}
		// Object o = JSON.parseObject(redis, Map.class);
		 Object o = JSONObject.parse(redis);
		 System.out.println(o.toString()+"----"+o.getClass());
		// User user = new User((String)jsonObject.get("name"),(String) jsonObject.get("age"),(String) jsonObject.get("email"), (String)jsonObject.get("phone"));
		 //System.out.println(user.toString());
//		 Map<String,String> map = new HashMap<>();
		 return o;  
	 }  
	 
	 @RequestMapping(path ="/redis/set1" ,method=RequestMethod.GET,produces={"application/json"})  
	 public Map<?, ?> redisSet1(@RequestParam("id")String id){  
		 User user = userService.getUserInfo(id);
		 System.out.println(user);
		 redisService.setObject2("name"+id, user);
		 Map<String, String> map = new HashMap<>();
		 return map;  
	 }  
	 
	 @RequestMapping(path ="/redis/del" ,method=RequestMethod.GET,produces={"application/json"})  
	 public Map<?, ?> redisDelKey(@RequestParam("id")String id){
		 redisService.del("name"+id);
		 Map<String, String> map = new HashMap<>();
		 return map;  
	 }
}
