package com.sist.web.service;

import java.awt.print.Pageable;
import java.util.*;

import org.springframework.data.domain.Page;

import com.sist.web.entity.TourEntity;
import com.sist.web.vo.TourVO;

public interface TourService {
	public List<TourVO> tourMainData();
	public List<TourVO> tourListData(int areacode, int start);
	public int tourTotalPage(int areacode);
	public TourEntity tourDetailData(int no);
	Page<TourEntity> searchByTitle(String title, int page, int size);
}
