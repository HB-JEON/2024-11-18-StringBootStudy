package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/*
	public int getBar_no();
	public String getName();
	public String getAddress();
	public String getImage();
	public String getPhone();
	public String getRownum();
 */

import com.sist.web.vo.CocktailBarVO;

@Repository
public interface CocktailBarRepository {
	@Query(value = "SELECT bar_no, name, address, image, phone, hit, rownum "
			+ "FROM (SELECT bar_no, name, address, image, phone, hit "
			+ "FROM cocktailbar ORDER BY hit DESC) "
			+ "WHERE rownum <= 4", nativeQuery = true)
	public List<CocktailBarVO> barMainData();
}
