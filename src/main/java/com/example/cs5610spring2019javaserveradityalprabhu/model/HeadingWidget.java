package com.example.cs5610spring2019javaserveradityalprabhu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "headingWidgets")
public class HeadingWidget extends Widget{

	@Column(name = "text")
	private String text;
	
	@Column(name = "size")
	private Integer size;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	

}
