package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;
import com.sist.web.entity.*;

@RestController
@CrossOrigin(origins = "*")
public class BusanFoodRestController {
	@Autowired
	private BusanFoodService bService;
	
	@GetMapping("/main_react")
	public Map main_data()
	{
		Map map = new HashMap();
		
		List<BusanFoodVO> list = bService.busanMainData();
		BusanFoodVO vo = list.get(0);
		List<BusanFoodVO> mList = new ArrayList<BusanFoodVO>();
		List<BusanFoodVO> lList = new ArrayList<BusanFoodVO>();
		for(int i=1; i<=4; i++)
		{
			mList.add(list.get(i));
		}
		for(int i=5; i<list.size(); i++)
		{
			lList.add(list.get(i));
		}
		
		List<BusanInfoEntity> iList = bService.busanInfoMainData();
		
		map.put("mp", vo);
		map.put("mList", mList);
		map.put("lList", lList);
		map.put("iList", iList);
				
		return map;
	}
	
	@GetMapping("/food/list_react")
	public Map food_list(@RequestParam("page") int page)
	{
		Map map = new HashMap();
		int rowSize = 12;
		int start = (page - 1) * rowSize;
		List<BusanFoodVO> list = bService.busanListData(start);
		
		int totalpage = bService.busanFoodTotalPage();
		final int BLOCK = 10;
		int startPgae = ((page - 1) / BLOCK * BLOCK) - 1;
		int endPage = ((page - 1) / BLOCK * BLOCK) - BLOCK;
		if(endPage > totalpage)
			endPage = totalpage;
		
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPgae);
		map.put("endPage", endPage);
		
		return map;
	}
}
