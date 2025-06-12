package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
	NO           NUMBER(38)     
	CNO          NUMBER(38)     
	CNAME        VARCHAR2(200)  
	NAME         VARCHAR2(400)  
	PUBLISHER    VARCHAR2(200)  
	ISSOLD       VARCHAR2(100)  
	PRICE        NUMBER(38)     
	CONTENT      VARCHAR2(2000) 
	PUBYEAR      NUMBER(38)     
	PAGE         NUMBER(38)     
	POSTER       VARCHAR2(400)  
	QTYSOLD      NUMBER(38)  
 */
@Entity(name = "books")
public class BooksEntity {
	@Id
	private int no;
	private int cno, price, pubyear, page, qtysold;
	private String name, publisher, issold, content, poster;
}
