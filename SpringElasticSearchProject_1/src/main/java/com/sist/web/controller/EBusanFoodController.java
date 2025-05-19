package com.sist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/*
	Spring-Boot - 서버 역할 => 요청 / 응답 : vue = Boot, React = Boot
	-----------------------------------
	ThymeLeaf
	---------
	 웹에서 독립적인 실행 환경을 위한 Java 템플릿
	  => JSP : 브라우저 실행 : 텍스트
	  => ThymeLeaf => HTML
	  => SSR : 서버사이드 HTML 렌더링
	           -------- NodeJS
	  => 목적 : 최대한 HTML 유지하기 위해
	  => Spring 통합
	  => JSP 보다 속도가 느리다.
	     --- 웹의 유료화를 대체 (JSP : ThymeLeaf / JAVA : Kotlin)
	  => 기본 사용법
	     ---------
	      데이터 출력
	        th:text = "${}" <td>[[${}]]</td>
	        url => @{}
	        선택 변수 => *{}
	        메세지 표현식 => #{}
	      연산자
	        산술 : + , - , * , / , %
	        비교 : < , > , <= , >= , != , ==
	        논리 : and , or , not
	        삼항 : (조건)? 값:값
	      제어문
	        th:if / th:else / th:each
	         => EL + JSTL
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.dao.*;
import com.sist.web.vo.*;
@Controller
public class EBusanFoodController {
	@Autowired
	private EBusanFoodRepository eDao;
	
	@GetMapping("/busan/list")
	public String ebusan_list(@RequestParam(value = "page", required = false) String page, Model model)
	{
		if(page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		// 페이징
		Pageable pg = PageRequest.of(curpage-1, rowSize, Sort.by(Sort.Direction.DESC, "id")); 
		// 페이지 사용
		Page<EBusanFood> pList = eDao.findAll(pg);
		// Page => List 변환
		List<EBusanFood> list = new ArrayList<EBusanFood>();
		// 데이터가 있는 경우에만 List 값 채우기
		if(pList != null && pList.hasContent())
		{
			list = pList.getContent();
		}
		
		for(EBusanFood eb : list)
		{
			String s = eb.getPoster();
			s = "https://www.menupan.com" + s;
			eb.setPoster(s);
		}
		
		// 총 페이지
		int count = (int)eDao.count();
		int totalpage = (int)(Math.ceil(count / 12.0));
		
		// BLOCK
		final int BLOCK = 10;
		int startPage = ((curpage - 1)/BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1)/BLOCK * BLOCK) + BLOCK;
		if(endPage > totalpage)
			endPage = totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "busan/list";
	}
	@RequestMapping("/busan/find")
	public String busan_find(@RequestParam(value = "type", required = false) String type, Model model)
	{
		if(type == null)
			type = "한식";
		List<EBusanFood> list = eDao.findByTypeContaining(type);
		model.addAttribute("list", list);
		return "busan/find";
	}
	@GetMapping("/busan/detail")
	public String busan_detail(@RequestParam("id") String id, Model model)
	{
		EBusanFood vo = eDao.findById(id);
		model.addAttribute("vo", vo);
		return "busan/detail";
	}
}
