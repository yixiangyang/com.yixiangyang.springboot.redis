package com.yixiangyang.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yixiangyang.springboot.model.User;

public interface UserMapper {
	
	@Select("SELECT id id,name name,age age,email email,phone phone,create_time createTime,update_time updateTime FROM test_user WHERE id = #{id}")
	public User getUserById(@Param("id")String id);
}
