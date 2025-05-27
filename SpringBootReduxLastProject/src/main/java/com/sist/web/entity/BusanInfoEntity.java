package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
	no int 
	CNO int 
	TITLE text 
	POSTER text 
	ADDRESS text 
	PHONE text 
	INFO text
 */
@Entity(name = "busan_info")
@Data
public class BusanInfoEntity {
	@Id
	private int no;
	private int cno;
	private String title, poster, address, phone, info;
}
