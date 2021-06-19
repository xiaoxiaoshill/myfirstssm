package com.ssm.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.bean.songlist;

@Repository
public interface SonglistService {
	boolean add(songlist s);
    boolean deletebyid(Integer listid);
    boolean updata(songlist s);
    List<songlist> selectall();
}
