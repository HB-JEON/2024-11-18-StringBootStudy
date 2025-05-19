package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sist.web.vo.EBusanFood;
import java.util.*;
/*
	JPA 형식 => 메소드 제작
	SELECT DISTINCT ... WHERE lastname AND firstname
	  =  findDistinctByLastnameAndFirstName()
	
	WHERE no BETWEEN ... AND ...
	  =  findByNoBetween(int a, int b)
	
	WHERE age < ?
	  =  findByAgeLessThen(int age)
	              ---- GreatThen (age > ?)
	              ---- GreaterThenEqual (age >= ?)
	
	가격(price) 1,000원보다 작고 가격을 내림차순으로 출력
	  =  findByPriceLessThenOrderByPriceDESC()
 */
public interface EBusanFoodRepository extends ElasticsearchRepository<EBusanFood, Integer>{
	public List<EBusanFood> findByTypeContaining(String type);
	// 메소드화 => 조건이 있는 경우 OrderBy, GroupBy ...
	// JOIN => 메소드(X)
	public EBusanFood findById(String id);
	// WHERE id = ?
}
