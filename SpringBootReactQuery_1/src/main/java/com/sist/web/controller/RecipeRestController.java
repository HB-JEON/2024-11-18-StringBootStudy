package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.entitiy.*;
import com.sist.web.service.*;

@RestController
@CrossOrigin(origins = "*")
public class RecipeRestController {
	@Autowired
	private RecipeService rService;
	// const {isLoading, error} = useQuery() => 4 => tanStack-Query : 기능 추가
	//												  오픈 소스 그룹
	/*
	   일반 => 권장 : javaScript / TypeScript (가독성)
	   Redux => TanStack-Query => ThymeLeaf (Git Action, Docker) => CI / CD
	 */
	@GetMapping("/recipe/list/{page}")
	public ResponseEntity<Map> food_list(@PathVariable("page") int page)
	{
		Map map = new HashMap();
		
		try
		{
			int rowSize = 12;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			List<RecipeVO> list = rService.recipeListData(start, end);
			int totalpage = rService.recipeTotalPage();
			
			final int BLOCK = 10;
			int startPage = ((page -1) / BLOCK * BLOCK) +1;
			int endPage = ((page -1) / BLOCK * BLOCK) +BLOCK;
			if(endPage > totalpage)
				endPage = totalpage;
			
			map.put("fList", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			
		}catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// 정상 수행 시 보내주기 => 200
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	@GetMapping("/recipe/detail/{no}")
	public ResponseEntity<Map> recipe_detail(@PathVariable("no") int no)
	{
		Map map = new HashMap();
		try
		{
			map = rService.recipeDetail(no);
			
		}catch (Exception e) 
		{
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/recipe/find/{page}/{title}")
	public ResponseEntity<Map> recipe_find(@PathVariable("page") int page, @PathVariable("title") String title)
	{
		Map map = new HashMap();
		try
		{
			map = rService.recipeFindData(page, title);
		}catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
