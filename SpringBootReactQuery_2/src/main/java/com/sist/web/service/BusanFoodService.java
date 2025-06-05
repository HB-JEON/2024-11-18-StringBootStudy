package com.sist.web.service;

import java.util.*;

import com.sist.web.entity.BusanFoodEntity;
import com.sist.web.entity.BusanInfoEntity;

public interface BusanFoodService {
	public Map busanFoodListData(int page);
	public BusanFoodEntity busanFoodDetailData(int fno);
	public int BusanFoodTotalPage();
	
	public Map busanInfoListData(int page);
	public BusanInfoEntity busanInfoDetailData(int no);
}
