package com.sist.web.service;

import java.util.*;

import com.sist.web.vo.BooksVO;

public interface BooksService {
	public List<BooksVO> booksMainData();
	public Map booksListData(int cno, int page);
}
