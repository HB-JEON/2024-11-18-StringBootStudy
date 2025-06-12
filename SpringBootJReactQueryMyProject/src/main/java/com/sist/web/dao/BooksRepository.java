package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BooksEntity;
import com.sist.web.vo.BooksVO;

import java.util.*;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, Integer> {
	@Query(value = "SELECT no, cno, price, name, poster, publisher, qtysold, content, rownum "
			+ "FROM (SELECT no, cno, price, name, poster, publisher, qtysold, content "
			+ "FROM books ORDER BY qtysold DESC) "
			+ "WHERE rownum <= 8 ", nativeQuery = true)
	public List<BooksVO> booksMainData();
	
	@Query(value = "SELECT * FROM (SELECT b.*, rownum "
			+ "FROM (SELECT no, cno, price, name, poster, publisher, qtysold, content, cname "
			+ "FROM books WHERE cno = :cno ORDER BY no ASC) b "
			+ "WHERE rownum <= :end) WHERE rownum >= :start", nativeQuery = true)
	public List<BooksVO> booksListData(@Param("cno") int cno, @Param("start") int start, @Param("end") int end);
}
