package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import com.sist.web.vo.*;
import com.sist.web.entity.*;
import com.sist.web.service.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class MainRestController {
	@Autowired
	private CocktailBarService cbService;
	
	@GetMapping("/main")
	public ResponseEntity<Map> main_data()
	{
		Map map = new HashMap();
		
		try
		{
			List<CocktailBarVO> list = cbService.barMainData();
			map.put("list", list);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
