package com.sist.web.controller;

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

import com.sist.web.entity.*;
import com.sist.web.service.*;
import com.sist.web.vo.CommentVO;

@RestController
@CrossOrigin(origins = "*")
public class CommentResController {
	@Autowired
	private CommentService cService;
	
	@GetMapping("/member/login/{id}/{pwd}")
	public ResponseEntity<Map> memberLogin(@PathVariable("id") String id, @PathVariable("pwd") String pwd)
	{
		Map map = new HashMap();
		
		try {
			int count = cService.idCount(id);
			if(count == 0)
			{
				map.put("msg", "NOID");
			} 
			else 
			{
				MemberEntity vo = cService.memberDetailData(id);
				if(pwd.equals(vo.getPwd()))
				{
					map.put("msg", "OK");
					map.put("name", vo.getName());
					map.put("id", vo.getId());
				}
				else 
				{
					map.put("msg", "NOPWD");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	// 댓글
	// 작성 / 수정 / 삭제
	@PostMapping("/comment/insert")
	public ResponseEntity<Map> comment_insert(@RequestBody CommentEntity vo)
	{
		Map map = new HashMap();
		
		try
		{
			List<CommentVO> list = cService.commentInsert(vo);
			map.put("comments", list);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/comment/delete/{no}/{fno}")
	public ResponseEntity<Map> comment_delete(@PathVariable("no") int no,@PathVariable("fno") int fno)
	{
		Map map = new HashMap();
		
		try
		{
			List<CommentVO> list = cService.commentDelete(no, fno);
			map.put("comments", list);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PutMapping("/comment/update")
	public ResponseEntity<Map> comment_update(@RequestBody CommentEntity vo)
	{
		Map map = new HashMap();
		
		try
		{
			System.out.println(vo);
			List<CommentVO> list = cService.commentUpdate(vo.getNo(), vo.getMsg());
			map.put("comments", list);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
