package com.sist.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sist.web.entity.TourEntity;
import com.sist.web.service.TourService;
import com.sist.web.vo.TourVO;

@RestController
@CrossOrigin(origins = "*")
public class TourRestController {
	@Autowired
	private TourService tService;
	
	@GetMapping("/tour_main_react")
	public Map main_data()
	{
		Map map = new HashMap();
		
		List<TourVO> list = tService.tourMainData();
		TourVO vo = list.get(0);
		
		List<TourVO> mList = new ArrayList<TourVO>();
		List<TourVO> lList = new ArrayList<TourVO>();
		for(int i=1; i<=4; i++)
		{
			mList.add(list.get(i));
		}
		for(int i=5; i<list.size(); i++)
		{
			lList.add(list.get(i));
		}
		
		map.put("tm", vo);
		map.put("mList", mList);
		map.put("lList", lList);
		
		return map;
	}
	
	@GetMapping("/tour/list_react")
	public Map tour_list(@RequestParam("areacode") int areacode, 
			@RequestParam("page") int page)
	{
		Map map = new HashMap();
		
		int rowSize = 12;
	    int start = (page - 1) * rowSize;
		List<TourVO> list = tService.tourListData(areacode, start);
		int totalpage = tService.tourTotalPage(areacode);
		
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
	@GetMapping("/tour/detail_react")
	public TourEntity tour_detail(@RequestParam("no") int no)
	{
		TourEntity vo = tService.tourDetailData(no);
		return vo;
	}
	
	@GetMapping("/tour/find_react")
	public Page<TourEntity> tour_find(@RequestParam("title") String title, 
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "12")int size)
	{
		return tService.searchByTitle(title, page, size);
	}
}