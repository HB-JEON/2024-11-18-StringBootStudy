package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

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
@Entity(name = "cocktailbar")
@Data
public class CocktaiBarlEntity {
	@Id
	private int bar_no;
	private int hit;
	private String name, address, phone, image, profile, time, loc, subway, rdays;
}
