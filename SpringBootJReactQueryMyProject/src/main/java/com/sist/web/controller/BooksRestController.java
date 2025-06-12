package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.BooksService;
import com.sist.web.vo.BooksVO;

@RestController
@CrossOrigin(origins = "*")
public class BooksRestController {
	@Autowired
	private BooksService bService;
	
	@GetMapping("/books/list/{cno}/{page}")
	public ResponseEntity<Map> main_data(@PathVariable("cno") int cno, @PathVariable("page") int page)
	{
		Map map = new HashMap();
		try
		{
			map = bService.booksListData(cno, page);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
