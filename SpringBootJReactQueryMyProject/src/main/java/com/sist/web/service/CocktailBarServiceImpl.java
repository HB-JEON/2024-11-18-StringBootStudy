package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.CocktailBarRepository;
import com.sist.web.vo.CocktailBarVO;

@Service
public class CocktailBarServiceImpl implements CocktailBarService {
	@Autowired
	private CocktailBarRepository cbDao;

	@Override
	public List<CocktailBarVO> barMainData() {
		// TODO Auto-generated method stub
		return cbDao.barMainData();
	}
}
