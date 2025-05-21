package com.sist.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sist.web.dao.BusanInfoRepository;
import com.sist.web.vo.BusanInfoEntity;

@Service
public class BusanInfoServiceImpl implements BusanInfoService {
	
	@Autowired
	private BusanInfoRepository bDao;
	
	@Override
	public List<BusanInfoEntity> busanAllData(int page) {
		// TODO Auto-generated method stub
		int rowSize = 12;
		Pageable pg = PageRequest.of(page-1, rowSize,Sort.by(Sort.Direction.ASC, "id"));
		
		Page<BusanInfoEntity> pList = bDao.findAll(pg);
		List<BusanInfoEntity> list = new ArrayList<BusanInfoEntity>();
		if(pList != null && pList.hasContent())
		{
			list = pList.getContent();
		}
		return list;
	}
	
	@Override
	public int[] getPageData(int page) {
		// TODO Auto-generated method stub
		int[] datas = new int[4];
		int count = (int)bDao.count();
		int totalpage = (int)(Math.ceil(count/12.0));
		final int BLOCK = 10;
		int startPage = ((page-1) / BLOCK*BLOCK)+1;
		int endPage = ((page-1) / BLOCK*BLOCK)+BLOCK;
		if(endPage > totalpage)
			endPage = totalpage;
		datas[0] = page;
		datas[1] = totalpage;
		datas[2] = startPage;
		datas[3] = endPage;
		return datas;
	}

	@Override
	public List<BusanInfoEntity> findByTitleContaining(String title) {
		// TODO Auto-generated method stub
		return bDao.findByTitleContaining(title);
	}

	@Override
	public List<BusanInfoEntity> busanFindData(String title) {
		// TODO Auto-generated method stub
		return bDao.busanFindData(title);
	}

	@Override
	public List<BusanInfoEntity> findByCno(Integer cno) {
		// TODO Auto-generated method stub
		return bDao.findByCno(cno);
	}

	@Override
	public List<BusanInfoEntity> busanCnoData(Integer cno) {
		// TODO Auto-generated method stub
		return bDao.busanCnoData(cno);
	}

	@Override
	public BusanInfoEntity findById(String id) {
		// TODO Auto-generated method stub
//		BusanInfoEntity b = bDao.findById(id).orElse(new BusanInfoEntity());
		return bDao.findById(id);
	}

	@Override
	public BusanInfoEntity busanDetailData(Integer id) {
		// TODO Auto-generated method stub
		return bDao.busanDetailData(id);
	}

	

}
