package com.sist.web.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.BoardEntity;
import com.sist.web.entity.BoardUpdateVO;
import com.sist.web.entity.BoardVO;
import com.sist.web.repository.BoardRepository;

// => Restful => GET(읽기) SELECT / POST(쓰기) INSERT / PUT(수정) UPDATE / DELETE(삭제)
@RestController
@CrossOrigin(origins = "*")
// AWS => 0 ~ 1023 PORT 거부 => PORT 1521 / 8080 변경
public class BoardRestController {
	
	@Autowired
	private BoardRepository bDao;
	
	@GetMapping("/board/list/{page}")
	public ResponseEntity board_list(@PathVariable("page") int page)
	{
		Map map = new HashMap();
		try
		{
			int rowSize = 10;
			int start = (rowSize*page) - (rowSize-1);
			int end = page*rowSize;
			List<BoardVO> list = bDao.boardListData(start, end);
			
			int count = (int)bDao.count();
			int totalpage = (int)(Math.ceil(count/(double)rowSize));
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			map.put("today", today);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("list", list);
		}catch(Exception e)
		{
			// 서버 에러 전송
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/board/insert")
	public Map board_insert(@RequestBody BoardEntity vo)
	{
		Map map = new HashMap();
		
		try 
		{
			vo.setHit(0);
			vo.setNo(bDao.maxNo());
			vo.setRegdate(new Date());
			BoardEntity _vo = bDao.save(vo);
			
			map.put("vo", _vo);
			map.put("msg", "yes");
		}catch(Exception e) 
		{
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
	
	@GetMapping("/board/detail/{no}")
	public ResponseEntity<BoardVO> board_detail(@PathVariable("no") int no)
	{
		BoardVO vo = null;
		try
		{
			BoardEntity b = bDao.findByNo(no);
			b.setHit(b.getHit()+1);
			bDao.save(b);
			vo = bDao.boardDetailData(no);
		}catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/delete/{no}/{pwd}")
	public ResponseEntity<Map> board_delete(@PathVariable("no") int no, @PathVariable("pwd") String pwd)
	{
		Map map = new HashMap();
		
		try
		{
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
		}catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	@GetMapping("/board/update/{no}")
	public ResponseEntity<BoardUpdateVO> board_update(@PathVariable("no") int no)
	{
		BoardUpdateVO vo = null;
		try
		{
			bDao.boardUpdateData(no);
		}catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@PutMapping("/board/update_ok")
	public ResponseEntity<Map> board_update_ok(@RequestBody BoardEntity vo)
	{
		Map map = new HashMap();
		try
		{
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
		}catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
