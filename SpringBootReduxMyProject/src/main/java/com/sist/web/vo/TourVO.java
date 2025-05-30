package com.sist.web.vo;

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

public interface TourVO {
	public int getNo();
	public String getTitle();
	public String getImg();
	public String getAddr();
	public String getOverview();
	public Integer getAreacode();
}
