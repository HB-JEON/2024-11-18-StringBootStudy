package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BoardEntity;
import com.sist.web.vo.BoardUpdateVO;
import com.sist.web.vo.BoardVO;

@Repository
public interface BoardRepository  extends JpaRepository<BoardEntity, Integer> {
	@Query(value = "SELECT no, subject, name, TO_CHAR(content) as content, TO_CHAR(regdate, 'yyyy-MM-dd') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, content, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, content, regdate, hit "
			+ "FROM board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN :start AND :end", nativeQuery = true)
	public List<BoardVO> boardListData(@Param("start") Integer start, @Param("end") Integer end);
	// @Query => 입력되는 데이터가 있는 경우 => @Param
	// @Param 두개이상 => Integer
	
	@Query(value = "SELECT no, subject, name, TO_CHAR(content) as content, TO_CHAR(regdate, 'yyyy-MM-dd') as dbday, hit, rownum as num "
			+ "FROM board WHERE no = :no", nativeQuery = true)
	public BoardVO boardDetailData(@Param("no") int no);
	
	@Query(value = "SELECT NVL(MAX(no) +1, 1) FROM board", nativeQuery = true)
	public int maxNo();
	
	public BoardEntity findByNo(int no);
	
	@Query(value = "SELECT no, subject, name, TO_CHAR(content) as content "
			+ "FROM board WHERE no = :no", nativeQuery = true)
	public BoardUpdateVO boardUpdateData(@Param("no") int no);
}
