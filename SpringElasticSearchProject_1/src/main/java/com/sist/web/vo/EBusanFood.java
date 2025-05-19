package com.sist.web.vo;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Data;

// 검색, 페이징, 데이터 읽기, 상세보기
//      ----- th:each = for / th:if / th:else
//       =>  i : ${#numbers.sequence(startPage, endPage)}
/*
Columns:
id int 
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
@Document(indexName = "busanfood")
@Data
public class EBusanFood {
	@Id
	private int id;
	private String name, type, phone, address, theme, poster, images, time, parking, content, price;
	private int score, hit, jjimcount, likecount, replycount;
	private double socre;
}
