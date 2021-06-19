package com.ssm.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.bean.songlist;
import com.ssm.service.SonglistService;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/songlist")
public class SonglistController {
	@Resource
	private SonglistService songlistService;
	@RequestMapping("/add")
	public @ResponseBody Boolean add(songlist s){
		
		return songlistService.add(s);
	}
	public @ResponseBody Boolean deletebyid(Integer listid){
		return songlistService.deletebyid(listid);
	}
	public @ResponseBody Boolean updata(songlist s){
		return songlistService.updata(s);
	}
	public @ResponseBody List<songlist> selectall(){
		return songlistService.selectall();
	}
}
