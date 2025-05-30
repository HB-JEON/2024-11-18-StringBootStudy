package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.BusanFoodRepository;
import com.sist.web.dao.BusanInfoRepository;
import com.sist.web.entity.BusanFoodEntity;
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

	@Override
	public BusanFoodEntity busanDetailData(int fno) {
		// TODO Auto-generated method stub
		return bDao.findByFno(fno);
	}

	@Override
	public List<BusanInfoEntity> busanInfoListData(int cno, int start) {
		// TODO Auto-generated method stub
		return iDao.busanInfoListData(cno, start);
	}

	@Override
	public int busanInfoTotalPage(int cno) {
		// TODO Auto-generated method stub
		return iDao.busanInfoTotalPage(cno);
	}

	@Override
	public List<BusanInfoEntity> findListData(String title) {
		// TODO Auto-generated method stub
		return iDao.findByTitleContaining(title);
	}

	@Override
	public BusanInfoEntity busanInfoDetailData(int no) {
		// TODO Auto-generated method stub
		return iDao.findByNo(no);
	}
	
}
