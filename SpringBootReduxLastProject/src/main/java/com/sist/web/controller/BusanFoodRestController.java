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
	    int startPage = ((page - 1) / BLOCK) * BLOCK + 1;
	    int endPage = startPage + BLOCK - 1;
	    if (endPage > totalpage)
	        endPage = totalpage;

	    map.put("list", list);
	    map.put("curpage", page);
	    map.put("totalpage", totalpage);
	    map.put("startPage", startPage);
	    map.put("endPage", endPage);

	    return map;
	}
	
	@GetMapping("/food/detail_react")
	public BusanFoodEntity food_detail(@RequestParam("fno") int fno)
	{
		BusanFoodEntity vo = bService.busanDetailData(fno);
		
		return vo;
	}
	
	@GetMapping("/info/list_react")
	public Map info_list(@RequestParam("cno") int cno, @RequestParam("page") int page)
	{
		Map map = new HashMap();
	    int rowSize = 12;
	    int start = (page - 1) * rowSize;
	    List<BusanInfoEntity> list = bService.busanInfoListData(cno, start);
	    int totalpage = bService.busanInfoTotalPage(cno);
	    
	    map.put("list", list);
	    map.put("curpage", page);
	    map.put("totalpage", totalpage);
	    
	    return map;
	}
	
	@GetMapping("/info/detail_react")
	public Map info_detail(@RequestParam("no") int no)
	{
		Map map = new HashMap();
		BusanInfoEntity vo = bService.busanInfoDetailData(no);
		String addr = vo.getAddress();
		addr = addr.substring(addr.indexOf(" ") +1);
		String addr1 = addr.trim();
		addr1 = addr1.substring(addr1.indexOf(" ") +1);
		String addr2 = addr1.trim();
		addr2 = addr2.substring(0, addr2.indexOf(" "));
		
		map.put("vo", vo);
		map.put("addr", addr2);
		
		return map;
	}
}
