package com.sist.web.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.BoardRepository;
import com.sist.web.entity.*;

// => Restful => GET(읽기) SELECT / POST(쓰기) INSERT / PUT(수정) UPDATE / DELETE(삭제)
@RestController
@CrossOrigin(origins = "*")
// AWS => 0 ~ 1023 PORT 거부 => PORT 1521 / 8080 변경
public class BoardRestController {
	
	@Autowired
	private BoardRepository bDao;
	
	@GetMapping("/board/list_react/{page}")
	public Map board_list(@PathVariable("page") int page)
	{
		Map map = new HashMap();
		
		int rowSize = 10;
		int start = (page - 1) * rowSize;
		List<BoardEntity> list = bDao.boardListData(start);
		
		for(BoardEntity vo : list)
		{
			String day = vo.getRegdate();
			day = day.substring(0, day.indexOf(" "));
			vo.setRegdate(day);
		}
		
		int count = (int)bDao.count();
		int totalpage = (int)(Math.ceil(count/(double)rowSize));
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		map.put("today", today);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("list", list);
				
		return map;
	}
	
	@PostMapping("/board/insert_react")
	public Map board_insert(@RequestBody BoardEntity vo)
	{
		Map map = new HashMap();
		
		try 
		{
			vo.setHit(0);
			BoardEntity _vo = bDao.save(vo);
			map.put("vo", _vo);
			map.put("msg", "yes");
		} catch(Exception e) 
		{
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
	@GetMapping("/board/detail_react/{no}")
	public BoardEntity board_detail(@PathVariable("no") int no)
	{
		BoardEntity vo = bDao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		bDao.save(vo);
		vo = bDao.findByNo(no);
		
		return vo;
	}
	@DeleteMapping("/board/delete_react/{no}/{pwd}")
	public Map board_delete(@PathVariable("no") int no, @PathVariable("pwd") String pwd)
	{
		Map map = new HashMap();
		
		BoardEntity vo = bDao.findByNo(no);
		if(pwd.equals(vo.getPwd()))
		{
			bDao.delete(vo);
			map.put("msg", "yes");
		}
		else
		{
			map.put("msg", "no");
		}
		return map;
	}
	@GetMapping("/board/update_react/{no}")
	public BoardEntity board_update(@PathVariable("no") int no)
	{
		BoardEntity vo = bDao.findByNo(no);
		
		return vo;
	}
	
	@PutMapping("/board/update_react_ok")
	public Map board_update_ok(@RequestBody BoardEntity vo)
	{
		Map map = new HashMap();
		
		BoardEntity db = bDao.findByNo(vo.getNo());
		if(vo.getPwd().equals(db.getPwd()))
		{
			vo.setNo(vo.getNo());
			vo.setHit(db.getHit());
			bDao.save(vo);
			map.put("msg", "yes");
		}
		else
		{
			map.put("msg", "no");
		}
		
		return map;
	}
}
