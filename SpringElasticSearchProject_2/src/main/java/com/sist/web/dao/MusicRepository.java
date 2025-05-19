package com.sist.web.dao;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;
// 빅데이터 => 빠른 검색(검색 엔진) => SM / SI (웹)
/*
	데이터베이스 ElasticSearch
	------------------------
	  table		index
	  column	field
	  row		document
	-------------------- NoSQL 기능 탑재 => 실시간 데이터
	  장점 => 클러스트(리눅스 기반)
 */
@Repository
public interface MusicRepository extends ElasticsearchRepository<Music, String> {
//	@Query("{\"query\":{\":match\":{\"title\":\"?0\"}}}")
	public List<Music> findByTitleContaining(String title);
}
