package com.sist.web.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "recipedetail")
public class RecipeDetailEntity {
	@Id
	private int no;
	private String poster, title, chef, chef_poster, chef_profile, info1, info2, info3, content, foodmake, data;
}
