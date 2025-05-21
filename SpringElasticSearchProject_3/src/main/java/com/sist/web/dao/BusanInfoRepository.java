package com.sist.web.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BusanInfoEntity;
import java.util.*;
@Repository
public interface BusanInfoRepository extends ElasticsearchRepository<BusanInfoEntity, Integer>{
   // SELECT * FROM table_name , INSERT / UPDATE / DELETE 
   // WHERE (조건문) findDistinctByAge... => 메소드 패턴  lessthen < greaterthen > 
   // <= : lessthenEqual  >= greaterthenEqual
   public List<BusanInfoEntity> findByCno(@Param("cno") Integer cno);
   public List<BusanInfoEntity> findByTitleContaining(@Param("title") String title);
   /*
    *    A%    findByTitleStartsWith
    *    %A%   findByTitleContaining
    *    %A    findByTitleEndsWith
    */
}