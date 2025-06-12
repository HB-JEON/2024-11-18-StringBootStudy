package com.sist.web.entity;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "reactcomment")
@Data
public class CommentEntity {
	@Id
	private int no;
	@Column(updatable = false, insertable = true)
	private int fno;
	@Column(updatable = false, insertable = true)
	private String id;
	@Column(updatable = false, insertable = true)
	private String name;
	private String msg;
	@Column(updatable = false, insertable = true)
	private Date regdate;
}
