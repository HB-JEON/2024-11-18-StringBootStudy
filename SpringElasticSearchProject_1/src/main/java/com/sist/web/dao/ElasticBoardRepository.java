package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.ElasticBoard;
/*
	A%, %B, %C%
	findByNameStartWith(String name)
	findByNameEndWith(String name)
	findByNameContaining(String name)
 */
@Repository
public interface ElasticBoardRepository extends ElasticsearchRepository<ElasticBoard, Integer>{
	// SELECT * FROM board WHERE id=?
	public ElasticBoard findById(int id); 
	// CRUD 설정
	/*
	 	findAll() => 전체 데이터 읽기
	 	findBy컬럼명() => 상세보기
	 	save() => INSERT / UPDATE
	 	delete() => DELETE
	 	
	 	myBatis - SQL 기반
	 			  ~VO (다른 데이터 추가 가능)
	 	hibernate - 메소드 => 자동 SQL 문장을 제작
	 	            ----- 형식
	 	             단점 - JOIN (O) / SubQuery (X)
	 	             ~Entity (컬럼과 일치) a, b, c => d insert into table(a, b, c, d)
	 	      
	 	
	 */
}
