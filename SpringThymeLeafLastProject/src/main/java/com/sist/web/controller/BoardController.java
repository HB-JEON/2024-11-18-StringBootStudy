package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@Controller
public class BoardController {
	
	@Autowired
	private BoardRepository bDao;
	
	@GetMapping("/list")
	public String board_list(@RequestParam(value = "page", required = false) String page, Model model) {
		if(page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = (curpage*rowSize)-rowSize;
		List<BoardVO> list = bDao.boardListData(curpage);
		int count = (int)bDao.count();
		int totalpage = (int)(Math.ceil(count/10.0));
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		return "list";
	}
	@GetMapping("/insert")
	public String board_insert() {
		return "insert";
	}
	@PostMapping("insert_ok")
	public String board_insert_ok(BoardEntity vo)
	{
		bDao.save(vo);
		return "redirect:/list";
	}
	@GetMapping("/detail")
	public String board_detail(@RequestParam("no") int no, Model model)
	{
		BoardEntity vo = bDao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		bDao.save(vo);
		
		vo = bDao.findByNo(no);
		model.addAttribute("vo", vo);
		return "detail";
	}
}
