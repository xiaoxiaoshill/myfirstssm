package com.ssm.dao;

import java.util.List;

import com.ssm.bean.songlist;

public interface songlistMapper {
    boolean add(songlist s);
    boolean deletebyid(Integer listid);
    boolean updata(songlist s);
    List<songlist> selectall();
}