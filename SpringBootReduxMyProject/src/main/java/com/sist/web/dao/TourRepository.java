package com.sist.web.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.TourEntity;
import com.sist.web.vo.TourVO;

import java.util.*;


@Repository
public interface TourRepository extends JpaRepository<TourEntity, Integer>{
	@Query(value = "SELECT no, title, addr, img FROM tour ORDER BY no ASC LIMIT 0, 9", nativeQuery = true)
	public List<TourVO> tourMainData();
	
	@Query(value = "SELECT no, title, addr, img, areacode FROM tour "
			+ "WHERE areacode = :areacode LIMIT :start, 12", nativeQuery = true)
	public List<TourVO> tourListData(@Param("areacode") int areacode, @Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM tour WHERE areacode = :areacode", nativeQuery = true)
	public int tourTotalPage(@Param("areacode") int areacode);
	
	public TourEntity findByNo(@Param("no") int no);
	
	Page<TourEntity> findByTitleContaining(String title, Pageable pageable);

}
