package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entitiy.*;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer>{
	@Query(value = "SELECT no, title, poster, chef, hit, likecount, num "
			+ "FROM (SELECT no, title, poster, chef, hit, likecount, rownum as num "
			+ "FROM (SELECT /* INDEX_ASC(recipe recipe_no_pk)*/ no, title, poster, chef, hit, likecount "
			+ "FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail))) "
			+ "WHERE num BETWEEN :start AND :end", nativeQuery = true)
	public List<RecipeVO> recipeListData(@Param("start") Integer start, @Param("end") Integer end);
	
	public RecipeEntity findByNo(int no);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM recipe "
			+ "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail)", nativeQuery = true)
	public int recipeTotalPage();
	
	@Query(value = "SELECT no, title, poster, chef, hit, likecount, num "
			+ "FROM (SELECT no, title, poster, chef, hit, likecount, rownum as num "
			+ "FROM (SELECT /* INDEX_ASC(recipe recipe_no_pk)*/ no, title, poster, chef, hit, likecount "
			+ "FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail) "
			+ "AND title LIKE '%'||:title||'%')) "
			+ "WHERE num BETWEEN :start AND :end", nativeQuery = true)
	public List<RecipeVO> recipeFindData(@Param("start") Integer start, @Param("end") Integer end, @Param("title") String title); 
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM recipe "
			+ "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail) "
			+ "AND title LIKE '%'||:title||'%'", nativeQuery = true)
	public int recipeFindTotalPage(@Param("title") String title);
	
	// Find
	// Chef
	// Chef의 recipe 목록
	// CRUD
}
