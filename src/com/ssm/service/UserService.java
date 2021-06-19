package com.ssm.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.bean.user;

@Repository
public interface UserService {
	user login(user u);
	List<user> selectAll();
	Boolean register(user u);
	Boolean updatebyid(user u);
	user selectByPrimaryKey(Integer userid);
	user selectbyusername(String username);
}
