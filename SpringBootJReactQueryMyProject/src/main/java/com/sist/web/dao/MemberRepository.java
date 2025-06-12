package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
	// id 체크 => session : PORT
	@Query(value = "SELECT COUNT(*) FROM member "
			+ "WHERE id = :id", nativeQuery = true)
	public int idCount(@Param("id") String id);
	
	@Query(value = "SELECT * FROM member "
			+ "WHERE id = :id", nativeQuery = true)
	public MemberEntity memberDetailData(@Param("id") String id);
}
