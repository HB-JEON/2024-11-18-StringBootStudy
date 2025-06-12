package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.*;
/*
	ID       NOT NULL VARCHAR2(20)  
	PWD      NOT NULL VARCHAR2(10)  
	NAME     NOT NULL VARCHAR2(50)  
	SEX               CHAR(6)       
	EMAIL             VARCHAR2(100) 
	BIRTHDAY NOT NULL DATE          
	ADDRESS  NOT NULL VARCHAR2(100) 
	REGDATE           DATE       
 */
@Entity(name = "member")
@Data
public class MemberEntity {
	@Id
	private String id;
	private String pwd, name, sex, email, address;
	private Date birthday, regdate;
}
