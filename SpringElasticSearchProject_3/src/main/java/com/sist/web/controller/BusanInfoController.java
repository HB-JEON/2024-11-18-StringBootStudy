package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.*;
import com.sist.web.entity.*;
@Controller
public class BusanInfoController {
   @Autowired
   private BusanInfoService bService;
   
   @GetMapping("/info/list")
   public String info_list(@RequestParam(name="page",required = false) String page,Model model)
   {
	   if(page==null)
		   page="1";
	   List<BusanInfoEntity> list=bService.getBusanInfoAll(Integer.parseInt(page));
	   int[] data=bService.getPageDatas(Integer.parseInt(page));
	   
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", data[0]);
	   model.addAttribute("totalpage", data[1]);
	   model.addAttribute("startPage", data[2]);
	   model.addAttribute("endPage", data[3]);
	   model.addAttribute("main_html", "info/list");
	   return "main";
   }
   @RequestMapping("/info/find")
   public String info_find(@RequestParam(name="title",required = false) String title,Model model)
   {
	   if(title==null)
		   title="부산";
	   List<BusanInfoEntity> list=bService.busanInfoFindData(title);
	   
	   model.addAttribute("main_html", "info/find");
	   return "main";
   }
}