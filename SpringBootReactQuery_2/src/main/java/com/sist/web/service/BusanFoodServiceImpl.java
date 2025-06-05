package com.sist.web.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.entity.*;
import com.sist.web.repository.*;


@Service
public class BusanFoodServiceImpl implements BusanFoodService {

	@Autowired
	private BusanFoodRepository bDao;
	
	@Autowired
	private BusanInfoRepository iDao;
	
	@Override
	public Map busanFoodListData(int page) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		
		int rowSize = 12;
		int start = (rowSize*page) - (rowSize-1);
		int end = rowSize*page;
		
		List<BusanFoodVO> list = bDao.busanFoodListData(start, end);
		int totalpage = getTotlaPage();
		
		final int BLOCK = 10;
		int startPage = ((page-1) / BLOCK * BLOCK) +1;
		int endPage = ((page-1) / BLOCK * BLOCK) +BLOCK;
		if(endPage > totalpage)
			endPage = totalpage;
		
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}

	@Override
	public BusanFoodEntity busanFoodDetailData(int fno) {
		// TODO Auto-generated method stub
		return bDao.findByFno(fno);
	}

	@Override
	public int BusanFoodTotalPage() {
		// TODO Auto-generated method stub
		return getTotlaPage();
	}
	
	public int getTotlaPage()
	{
		int count = (int)bDao.count();
		int totalpage = (int)(Math.ceil(count/12.0));
		return totalpage;
	}

	@Override
	public Map busanInfoListData(int page) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		
		int rowSize = 12;
		int start = (rowSize*page) - (rowSize-1);
		int end = rowSize*page;
		
		List<BusanInfoVO> list = iDao.busanInfoListData(start, end);
		int count = (int)iDao.count();
		int totalpage = (int)(Math.ceil(count/12.0));
		
		final int BLOCK = 10;
		int startPage = ((page-1) / BLOCK * BLOCK) +1;
		int endPage = ((page-1) / BLOCK * BLOCK) +BLOCK;
		if(endPage > totalpage)
			endPage = totalpage;
		
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}

	@Override
	public BusanInfoEntity busanInfoDetailData(int no) {
		// TODO Auto-generated method stub
		return iDao.findByNo(no);
	}
}
