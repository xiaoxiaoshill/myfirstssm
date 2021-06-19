package com.ssm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.bean.songlist;
import com.ssm.dao.songlistMapper;
@Service
@Transactional
public class SonglistServiceImpl implements SonglistService{
	@Resource
	private songlistMapper SonglistMapper;

	@Override
	public boolean add(songlist s) {
		// TODO Auto-generated method stub
		return SonglistMapper.add(s);
	}

	

	@Override
	public boolean updata(songlist s) {
		// TODO Auto-generated method stub
		return SonglistMapper.updata(s);
	}

	@Override
	public List<songlist> selectall() {
		// TODO Auto-generated method stub
		return SonglistMapper.selectall();
	}



	@Override
	public boolean deletebyid(Integer listid) {
		// TODO Auto-generated method stub
		return false;
	}
}
