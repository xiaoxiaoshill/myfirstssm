package com.ssm.dao;

import java.util.List;

import com.ssm.bean.user;

public interface userMapper {
	user login(user u);
	List<user> selectAll();
	Boolean register(user u);
	Boolean updatebyid(user u);
	user selectByPrimaryKey(Integer userid);
	user selectbyusername(String username);
}