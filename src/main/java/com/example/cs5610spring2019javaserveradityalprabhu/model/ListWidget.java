package com.example.cs5610spring2019javaserveradityalprabhu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "listWidgets")
public class ListWidget extends Widget{

	@Column(name = "ltext")
	private String ltext;

	public String getLText() {
		return ltext;
	}

	public void setLText(String text) {
		this.ltext = text;
	}

	
}