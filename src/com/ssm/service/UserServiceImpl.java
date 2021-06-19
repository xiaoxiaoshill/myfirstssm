package com.ssm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.bean.user;
import com.ssm.dao.userMapper;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private userMapper UserMapper;

	@Override
	public user login(user u) {
		// TODO Auto-generated method stub
		return UserMapper.login(u);
	}

	@Override
	public List<user> selectAll() {
		// TODO Auto-generated method stub
		return UserMapper.selectAll();
	}

	@Override
	public Boolean register(user u) {
		// TODO Auto-generated method stub
		return UserMapper.register(u);
	}

	@Override
	public Boolean updatebyid(user u) {
		// TODO Auto-generated method stub
		return UserMapper.updatebyid(u);
	}

	@Override
	public user selectByPrimaryKey(Integer userid) {
		// TODO Auto-generated method stub
		return UserMapper.selectByPrimaryKey(userid);
	}

	@Override
	public user selectbyusername(String username) {
		// TODO Auto-generated method stub
		return UserMapper.selectbyusername(username);
	}

	

	

	

	
	

	

	

	

	
	

}
