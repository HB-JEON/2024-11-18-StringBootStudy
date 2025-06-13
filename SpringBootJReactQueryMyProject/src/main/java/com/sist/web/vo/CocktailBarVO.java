package com.sist.web.vo;
/*
	BAR_NO  NOT NULL NUMBER        
	NAME    NOT NULL VARCHAR2(100) 
	ADDRESS NOT NULL VARCHAR2(120) 
	PHONE   NOT NULL VARCHAR2(14)  
	IMAGE   NOT NULL VARCHAR2(300) 
	PROFILE NOT NULL CLOB          
	HIT              NUMBER        
	TIME    NOT NULL VARCHAR2(500) 
	LOC              VARCHAR2(10)  
	SUBWAY  NOT NULL VARCHAR2(500) 
	RDAYS            VARCHAR2(200)  
*/
public interface CocktailBarVO {
	public int getBar_no();
	public int getHit();
	public String getName();
	public String getAddress();
	public String getImage();
	public String getPhone();
	public String getRownum();
}
