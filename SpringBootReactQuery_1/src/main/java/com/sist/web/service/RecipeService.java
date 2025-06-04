package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.sist.web.entitiy.RecipeDetailEntity;
// cookie / session 
import com.sist.web.entitiy.RecipeEntity;
import com.sist.web.entitiy.RecipeVO;


public interface RecipeService {
	public List<RecipeVO> recipeListData(int start, int end);
//	public RecipeEntity recipeDetailData(int no);
	public int recipeTotalPage();
	public Map recipeDetail(int no);
	public Map recipeFindData(int page, String title);
//	public int recipeFindTotalPage(String title);
}
