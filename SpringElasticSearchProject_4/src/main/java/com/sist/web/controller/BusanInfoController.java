package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.vo.*;
import com.sist.web.service.*;

@Controller
public class BusanInfoController {
	@Autowired
	private BusanInfoService bService;
	
	@GetMapping("/")
	public String busan_inf(@RequestParam(value = "page", required = false) String page, Model model)
	{
		if(page == null)
			page = "1";
		List<BusanInfoEntity> list = bService.busanAllData(Integer.parseInt(page));
		int[] pages = bService.getPageData(Integer.parseInt(page));
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", pages[0]);
		model.addAttribute("totalpage", pages[1]);
		model.addAttribute("startPage", pages[2]);
		model.addAttribute("endPage", pages[3]);
		
		return "list";
	}
	@GetMapping("/detail")
	public String busan_detail(@RequestParam("id") int id, Model model)
	{
		BusanInfoEntity vo = bService.findById(String.valueOf(id));
//		BusanInfoEntity vo = bService.busanDetailData(id);
		model.addAttribute("vo", vo);
		return "detail";
	}
	
	@RequestMapping("/find")
	public String busan_find(Model model,
			@RequestParam(value = "title", required = false) String title) 
	{
		if(title == null)
			title = "부산광역시";
		List<BusanInfoEntity> list = bService.busanFindData(title);
		model.addAttribute("list", list);
		return "find";
	}
	
}
