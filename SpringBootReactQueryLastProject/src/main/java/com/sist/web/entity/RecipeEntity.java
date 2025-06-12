package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
	NO         NOT NULL NUMBER         
	TITLE      NOT NULL VARCHAR2(4000) 
	POSTER     NOT NULL VARCHAR2(260)  
	CHEF       NOT NULL VARCHAR2(500)  
	LINK                VARCHAR2(300)  
	HIT                 NUMBER         
	LIKECOUNT           NUMBER         
	REPLYCOUNT          NUMBER   
 */
@Entity(name = "recipe")
@Data
public class RecipeEntity {
	@Id
	private int no;
	private int hit, likecount, replycount;
	private String title, poster, chef, link;
}
