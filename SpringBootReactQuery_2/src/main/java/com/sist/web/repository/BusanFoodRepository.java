package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

import java.util.*;
/*
	Wrapper
	 => 데이터를 쉽게 사용하게 만든 클래스
	 => 제네릭스에서는 일반 데이터형 사용 불가능
	 => 데이터가 2개 이상일 경우 int => Integer
	 => JPA
	 	 장점: SQL 문장을 몰라도 사용, 소스 간결
	 	      -------------------- 메소드 규칙
	 	       WHERE 문장 지원 X
	 	       findBy 컬럼 + Option
	 	 단점: 가독성 낮음
	 	 --------------- MyBatis
	 => Controller -> service -> Repository(DAO)
	               <-         <-
	 => 분석
	 	 톰캣: web.xml -> 확장자
	 	 	  application-context.xml ...
	 	 Controller => Service => DAO => JSP
 */
@Repository
public interface BusanFoodRepository extends JpaRepository<BusanFoodEntity, Integer> {
	@Query(value = "SELECT fno, poster, name, num "
			+ "FROM (SELECT fno, poster, name, rownum as num "
			+ "FROM (SELECT fno, poster, name "
			+ "FROM busan_food ORDER BY fno)) "
			+ "WHERE num BETWEEN :start AND :end", nativeQuery = true)
	public List<BusanFoodVO> busanFoodListData(@Param("start") Integer start, @Param("end") Integer end);
	
	public BusanFoodEntity findByFno(int fno);
}
