package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sist.web.dao.TourRepository;
import com.sist.web.entity.TourEntity;
import com.sist.web.vo.TourVO;

@Service
public class TourServiceImpl implements TourService {
	@Autowired
	private TourRepository tDao;
	
	@Override
	public List<TourVO> tourMainData() {
		// TODO Auto-generated method stub
		List<TourVO> list = tDao.tourMainData(); 
		return list;
	}

	@Override
	public List<TourVO> tourListData(int areacode, int start) {
		// TODO Auto-generated method stub
		return tDao.tourListData(areacode,start);
	}

	@Override
	public int tourTotalPage(int areacode) {
		// TODO Auto-generated method stub
		return tDao.tourTotalPage(areacode);
	}

	@Override
	public TourEntity tourDetailData(int no) {
		// TODO Auto-generated method stub
		return tDao.findByNo(no);
	}

	@Override
    public Page<TourEntity> searchByTitle(String title, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return tDao.findByTitleContaining(title, pageable);
    }
}
