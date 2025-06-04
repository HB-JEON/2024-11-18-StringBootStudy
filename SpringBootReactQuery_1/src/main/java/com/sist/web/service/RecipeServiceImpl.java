package com.sist.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.RecipeDetailRepository;
import com.sist.web.dao.RecipeRepository;
import com.sist.web.entitiy.RecipeDetailEntity;
import com.sist.web.entitiy.RecipeEntity;
import com.sist.web.entitiy.RecipeVO;
/*
	DAO : table 한개 담당
	Service : 여러개 table 모은 후 담당
			  DAO 여러개
			  DAO와 다른 기능이 있는 경우
	---------------------------------- 의존성이 낮은 프로그램
				| 처리
	=> DAO = Service = Controller
	    | DB 연동         | 브라우저로 값을 전송
 */
@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeRepository rDao;
	
	@Autowired
	private RecipeDetailRepository rdDao;
	
	@Override
	public List<RecipeVO> recipeListData(int start, int end) {
		// TODO Auto-generated method stub
		return rDao.recipeListData(start, end);
	}

//	@Override
//	public RecipeEntity recipeDetailData(int no) {
//		// TODO Auto-generated method stub
//		return rDao.findByNo(no);
//	}

	@Override
	public int recipeTotalPage() {
		// TODO Auto-generated method stub
		return rDao.recipeTotalPage();
	}

	@Override
	public Map recipeDetail(int no) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		
		RecipeDetailEntity vo = rdDao.findByNo(no);
		String[] datas = vo.getFoodmake().split("\n");
		List<String> mList = new ArrayList<String>();
		List<String> iList = new ArrayList<String>();
		
		for(String d : datas)
		{
			StringTokenizer st = new StringTokenizer(d, "^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		
		String s = vo.getData();
		s = s.replaceAll("[구매]", "");
		vo.setData(s);
		String[] arr = s.split(",");
		List<String> dList = Arrays.asList(arr);
		
		map.put("vo", vo);
		map.put("dList", dList);
		map.put("mList", mList);
		map.put("iList", iList);
		
		return map;
	}

	@Override
	public Map recipeFindData(int page, String title) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		
		int rowSize = 12;
		int start = (rowSize * page) - (rowSize - 1);
		int end = rowSize * page;
		List<RecipeVO> list = rDao.recipeFindData(start, end, title);
		int totalpage = rDao.recipeFindTotalPage(title);
		
		final int BLOCK = 10;
		int startPage = ((page -1) / BLOCK * BLOCK) +1;
		int endPage = ((page -1) / BLOCK * BLOCK) +BLOCK;
		if(endPage > totalpage)
			endPage = totalpage;
		
		map.put("rList", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}

}
