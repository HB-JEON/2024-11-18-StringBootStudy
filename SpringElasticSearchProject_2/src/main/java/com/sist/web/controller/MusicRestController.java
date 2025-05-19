package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.service.*;

@CrossOrigin(origins = "*")
@RestController
public class MusicRestController {
	@Autowired
	private MusicService mService;
	
	// Jackson
	@GetMapping("/music/music_all")
	public Map music_all(@RequestParam("page") int page)
	{
		Map map = new HashMap();
		List<Music> list = mService.getAllMusics(page);
		int totalpage = mService.getTotalPage();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		return map;
	}
	@GetMapping("/music/music_find")
	public List<Music> music_find(@RequestParam("fd") String fd)
	{
		List<Music> list = new ArrayList<Music>();
		list = mService.getMusicFind(fd);
		return list;
	}
}
