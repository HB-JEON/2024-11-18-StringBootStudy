package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.BusanFoodRepository;
import com.sist.web.dao.BusanInfoRepository;
import com.sist.web.entity.BusanInfoEntity;
import com.sist.web.vo.BusanFoodVO;

import java.util.*;

@Service
public class BusanFoodServiceImpl implements BusanFoodService {
	@Autowired
	private BusanFoodRepository bDao;
	
	@Autowired
	private BusanInfoRepository iDao;

	@Override
	public List<BusanFoodVO> busanMainData() {
		// TODO Auto-generated method stub
		List<BusanFoodVO> list = bDao.busanMainData();
		return list;
	}

	@Override
	public List<BusanInfoEntity> busanInfoMainData() {
		// TODO Auto-generated method stub
		return iDao.busanInfoMainData();
	}

	@Override
	public List<BusanFoodVO> busanListData(int start) {
		// TODO Auto-generated method stub
		return bDao.busanListData(start);
	}

	@Override
	public int busanFoodTotalPage() {
		// TODO Auto-generated method stub
		return bDao.busanFoodTotalPage();
	}
	
}
