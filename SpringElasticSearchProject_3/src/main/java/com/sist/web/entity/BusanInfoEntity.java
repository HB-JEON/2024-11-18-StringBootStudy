package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
/*
 *                | => JPA
 *   VO / DTO / Entity 
 *   --------- ------- 데이터베이스 연동 (컬럼외의 데이터를 추가하면 안된다)
 *      |
 *    추가 => MyBatis 
 */
@Document(indexName = "busaninfo")
/*
 *   id int 
     CNO int 
     TITLE text 
     POSTER text 
     ADDRESS text 
     PHONE text 
     INFO text
 */
@Getter
@Setter
public class BusanInfoEntity {
  @Id
  private int id;
  private int cno;
  private String title,poster,address,phone,info;
}