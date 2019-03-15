package com.example.cs5610spring2019javaserveradityalprabhu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "imageWidgets")
public class ImageWidget extends Widget{

	@Column(name = "imgUrl")
	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
