package com.sist.web.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
	fno int 
	NAME text 
	TYPE text 
	PHONE text 
	ADDRESS text 
	SCORE double 
	THEME text 
	POSTER text 
	IMAGES text 
	TIME text 
	PARKING text 
	CONTENT text 
	HIT int 
	PRICE text 
	JJIMCOUNT int 
	LIKECOUNT int 
	REPLYCOUNT int
 */
@Entity(name = "busan_food")
@Data
@DynamicUpdate
public class BusanFoodEntity {
	@Id
	private int fno;
	private String name, type, phone, address, theme, poster, images, time, parking, content, price;
	private int hit, jjimcount, likecount, replycount;
	private double score;
}
