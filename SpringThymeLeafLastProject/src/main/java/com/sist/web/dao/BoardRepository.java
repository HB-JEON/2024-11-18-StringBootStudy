package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.BoardEntity;
import com.sist.web.entity.BoardVO;

import java.util.*;
import java.util.List;

/*
	Controller: 동일
	DAO / Service: 오라클(Mybatis) MySQL(JPA), ElasticSearch
	Front-End: JSP / ThymeLeaf, Vue, React
	
	@Query
	  JOIN / CRUD
 */
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
	// findAll()
	// nativeQuery 쿼리문을 변경하지 않고 그대로 실행
	// ? #{start} :start :end
	@Query(value = "SELECT no, subject, name, regdate, hit "
			+ "FROM board ORDER BY no DESC "
			+ "LIMIT :start, 10", nativeQuery = true)
//	@Query("SELECT b FROM BoardEntity b")
	public List<BoardVO> boardListData(@Param("start") int start);
	// public Page<BoardVO> findAll(Pageable pg)
	
	// 상세보기
	public BoardEntity findByNo(@Param("no") Integer no);
	
	public List<BoardEntity> findByNameContaining(@Param("name") String name);
	public List<BoardEntity> findBySubjectContaining(@Param("subject") String subject);
	public List<BoardEntity> findByContentContaining(@Param("content") String content);
	
	// JPA => JOIN 가능 / SubQuery 불가능
	// MyBatis
	// count() save(): INSERT, UPDATE, DELETE() => SELECT => WHERE
}
