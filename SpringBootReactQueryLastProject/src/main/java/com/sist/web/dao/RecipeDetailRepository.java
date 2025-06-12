package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.RecipeDetailEntity;

@Repository
public interface RecipeDetailRepository extends JpaRepository<RecipeDetailEntity, Integer> {
	@Query(value = "SELECT no, poster, title, chef, chef_poster, chef_profile, info1, info2, info3, "
			+ "content, TO_CHAR(foodmake) as foodmake, TO_CHAR(data) as data, likecount "
			+ "FROM recipedetail WHERE no = :no", nativeQuery = true)
	public RecipeDetailEntity recipeDetailData(@Param("no") int no);
}
