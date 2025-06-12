package com.sist.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.BooksRepository;
import com.sist.web.entity.BooksEntity;
import com.sist.web.vo.BooksVO;

@Service
public class BooksServiceImpl implements BooksService {
	
	@Autowired
	private BooksRepository bDao;
	
	@Override
	public List<BooksVO> booksMainData() {
		// TODO Auto-generated method stub
		return bDao.booksMainData();
	}

	@Override
	public Map booksListData(int cno, int page) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		
		int rowSize = 12;
		int start = (rowSize * page) - (rowSize - 1);
		int end = rowSize * page;
		List<BooksVO> list = bDao.booksListData(cno, start, end);
		int count = (int)bDao.count();
		int totalpage = (int)(Math.ceil(count / 12.0));
		
		final int BLOCK = 10;
		int startPage = ((page - 1) / BLOCK * BLOCK) +1;
		int endPage = ((page - 1) /BLOCK * BLOCK) +BLOCK;
		
		if(endPage > totalpage)
			endPage = totalpage;
		
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}

}
