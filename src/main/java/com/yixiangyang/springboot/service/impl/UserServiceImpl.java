package com.yixiangyang.springboot.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yixiangyang.springboot.mapper.UserMapper;
import com.yixiangyang.springboot.model.User;
import com.yixiangyang.springboot.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;

	@Override
	public User getUserInfo(String id) {
		User user = userMapper.getUserById(id);
		return user;
	}

}
