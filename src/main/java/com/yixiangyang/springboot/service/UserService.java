package com.yixiangyang.springboot.service;

import com.yixiangyang.springboot.model.User;

public interface UserService {
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public User getUserInfo(String id);
}
