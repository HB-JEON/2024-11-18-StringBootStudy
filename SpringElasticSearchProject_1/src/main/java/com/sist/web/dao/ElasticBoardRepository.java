package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.ElasticBoard;

@Repository
public interface ElasticBoardRepository extends ElasticsearchRepository<ElasticBoard, Integer>{
//	public ElasticBoard findById(int id);
}
