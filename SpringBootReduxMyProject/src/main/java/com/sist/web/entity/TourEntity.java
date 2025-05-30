package com.sist.web.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
	NO int 
	CONTENT_ID int 
	CONTENT_TYPE int 
	TITLE text 
	ADDR text 
	AREACODE int 
	SIGUNGUCODE int 
	IMG text 
	CAT1 text 
	CAT2 text 
	CAT3 text 
	MAPX double 
	MAPY double 
	OVERVIEW text
 */

@Entity(name = "tour")
@Data
@DynamicUpdate
public class TourEntity {
	@Id
	private int no;
	private String title, addr, img, cat1, cat2, cat3, overview;
	private int content_id, content_type, areacode, sigungucode;
	private double mapx, mapy;
}
