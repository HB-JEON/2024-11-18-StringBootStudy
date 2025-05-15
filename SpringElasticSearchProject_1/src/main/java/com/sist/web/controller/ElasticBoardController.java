package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.dao.*;

@Controller
public class ElasticBoardController {
	@Autowired
	private ElasticBoardRepository bDao;
	
	@GetMapping("/eboard/list")
	public String eboard_list(@RequestParam( value = "page", required =  false) String page, Model model)
	{
		if(page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		Pageable pg = PageRequest.of(curpage-1, rowSize, Sort.by(Sort.Direction.DESC, "id"));
		// 전체 데이터 읽기 => findAll => SELECT ~
		Page<ElasticBoard> pList = bDao.findAll(pg);
		// Page => List 변환
		List<ElasticBoard> list = new ArrayList<ElasticBoard>();
		if(pList !=null && pList.hasContent())
		{
			list = pList.getContent(); // Page => List 변환
		}
		for(ElasticBoard eb : list)
		{
			String s = eb.getRegdate();
			int a = s.indexOf("T");
			if(a > 0)
			{
				s = s.substring(0, s.indexOf("T"));
				eb.setRegdate(s);
			}
		}
		
//		ElasticBoard eb = list.get(0);
//		String s = eb.getRegdate();
//		s = s.substring(0, s.indexOf("T"));
//		list.get(0).setRegdate(s);
		
		// 총 페이지 구하기
		int count = (int)bDao.count();
		int totalpage = (int)(Math.ceil(count/10.0));

		// HTML 전송
		/*
		    findAll() : SELECT * FROM board
		    findById(int id) : SELECT * FROM board WHERE id=?
		    count() : SELECT COUNT(*) FROM board
		    save() : INSERT, UPDATE
		    delete() : DELETE
		 */
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		return "eboard/list";
	}
	@GetMapping("/eboard/insert")
	public String board_insert()
	{
		return "eboard/insert";
	}
	@PostMapping("/eboard/insert_ok")
	public String eboard_insert_ok(ElasticBoard vo)
	{
		vo.setHit(0);
		vo.setId(idMaxData());
		vo.setRegdate(new SimpleDateFormat("yyy-MM-dd").format(new Date()));
		bDao.save(vo);
		return "redirect:/eboard/list";
	}
	public int idMaxData()
	{
		int max = 0;
		try {
			int rowSize = 10;
			int start = 0;
			Pageable pg = PageRequest.of(start, rowSize, Sort.by(Sort.Direction.DESC, "id"));
			// 전체 데이터 읽기 => findAll => SELECT ~
			Page<ElasticBoard> pList = bDao.findAll(pg);
			// Page => List 변환
			List<ElasticBoard> list = new ArrayList<ElasticBoard>();
			if(pList !=null && pList.hasContent())
			{
				list = pList.getContent(); // Page => List 변환
				max = list.get(0).getId();
			}
		} catch(Exception e) {
			max = 0;
		}
		return max + 1;
	}
}
