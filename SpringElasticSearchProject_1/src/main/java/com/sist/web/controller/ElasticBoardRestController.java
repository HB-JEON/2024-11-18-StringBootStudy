package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import com.sist.web.vo.*;

@RestController
public class ElasticBoardRestController {
	@Autowired
	private ElasticBoardRepository bDao;
	
	@PostMapping("/eboard/update_ok")
	public String eboard_update_ok(ElasticBoard vo)
	{
		String result = "";
		ElasticBoard dbvo = bDao.findById(vo.getId());
		if(dbvo.getPwd().equals(vo.getPwd()))
		{
			vo.setHit(dbvo.getHit());
			vo.setRegdate(dbvo.getRegdate());
			bDao.save(vo);
			result = "<script>"
					+ "location.href = \"/eboard/detail?id="+vo.getId()+"\""
					+ "</script>";
		}
		else
		{
			result = "<script>"
					+ "alert(\"비밀번호를 확인 해주세요.\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
	@PostMapping("/eboard/delete_ok")
	public String eobard_delete_ok(@RequestParam("id") int id, @RequestParam("pwd") String pwd)
	{
		String result = "";
		ElasticBoard vo = bDao.findById(id);
		if(vo.getPwd().equals(pwd))
		{
			bDao.delete(vo);
			result = "<script>"
					+ "location.href = \"/eboard/list\""
					+ "</script>";
		}
		else
		{
			result = "<script>"
					+ "alert(\"비밀번호를 확인 해주세요.\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
}
