package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sist.web.entity.*;
import com.sist.web.service.*;
import java.util.*;
@Controller
public class MainController {
  @Autowired
  private BusanInfoService bService;
  @GetMapping("/")
  public String main_main(Model model) {
	  
	  List<BusanInfoEntity> list1=bService.getBusanInfoData(1); // 명소 
	  List<BusanInfoEntity> list2=bService.getBusanInfoData(2); // 음식
	  List<BusanInfoEntity> list3=bService.getBusanInfoData(3); // 쇼핑
	  
	  model.addAttribute("list1", list1);
	  model.addAttribute("list2", list2);
	  model.addAttribute("list3", list3);
	  
	  model.addAttribute("main_html", "main/home");
	  return "main";
  }
}