package com.sist.web.controller;

// 서버 => 클라이언트 통신
/*
	서버 = 클라이언트와 동일한 언어
	--------------------------- 언어가 다른경우  
	파이썬 === 장고 - XML, JSON => 자바스크립트
	스프링 === 자바 - 모바일(코틀린) => JSON => 자바스크립트
	C# === Asp.net - 
 */
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.service.*;

@RestController
@CrossOrigin(origins = "*")
public class BusanInfoRestController {
	@Autowired
	private BusanInfoService bService;
	
	@GetMapping("/main_react")
	public Map main_react()
	{
		List<BusanInfoEntity> list1 = bService.getBusanInfoData(1);
		List<BusanInfoEntity> list2 = bService.getBusanInfoData(2);
		List<BusanInfoEntity> list3 = bService.getBusanInfoData(3);
		
		Map map = new HashMap();
		map.put("list1", list1);
		map.put("list2", list2);
		map.put("list3", list3);
		
		return map; // JSON
	}
	@GetMapping("/info/list_react")
	public Map info_list(@RequestParam("page") int page)
	{
		List<BusanInfoEntity> list=bService.getBusanInfoAll(page);
		int[] data=bService.getPageDatas(page);
		
		Map map = new HashMap();
		map.put("list", list);
		map.put("pages", data);
		   
		return map;
	}
}
