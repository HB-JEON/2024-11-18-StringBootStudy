package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.*;
import com.sist.web.entity.*;

// Cookie
@RestController
@CrossOrigin(origins = "*")
public class FoodRestController {
	@Autowired
	private BusanFoodService bService;
	
	@GetMapping("/food/list/{page}")
	public Map food_list(@PathVariable("page") int page) 
	{
		Map map = bService.busanFoodListData(page);
		return map;
	}
	
	@GetMapping("/info/list/{page}")
	public Map info_list(@PathVariable("page") int page)
	{
		Map map = bService.busanInfoListData(page);
		return map;
	}
	
	@GetMapping("/food/detail/{fno}")
	public ResponseEntity<BusanFoodEntity> food_detail(@PathVariable("fno") int fno)
	{
		BusanFoodEntity vo = new BusanFoodEntity();
		try 
		{
			vo = bService.busanFoodDetailData(fno);
		}catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
}
