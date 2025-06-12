package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="recipedetail")
@Data
public class RecipeDetailEntity {
	@Id
	private int no;
	private int likecount;
	private String poster, title, chef, chef_poster, chef_profile, 
				info1, info2, info3, content, foodmake, data;
}
