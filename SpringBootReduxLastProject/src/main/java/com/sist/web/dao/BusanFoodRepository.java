package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BusanFoodEntity;
import com.sist.web.vo.BusanFoodVO;

import java.util.*;

@Repository
public interface BusanFoodRepository extends JpaRepository<BusanFoodEntity, Integer> {
	@Query(value = "SELECT fno, poster, name, hit, jjimcount, likecount, score, type, theme, content "
			+ "FROM busan_food ORDER BY fno ASC LIMIT 0, 9", nativeQuery = true)
	public List<BusanFoodVO> busanMainData();
	
	@Query(value = "SELECT fno, poster, name, hit, jjimcount, likecount, score, type, theme, content "
			+ "FROM busan_food ORDER BY fno ASC", nativeQuery = true)
	public List<BusanFoodVO> busanListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM busan_food", nativeQuery = true)
	public int busanFoodTotalPage();
}
