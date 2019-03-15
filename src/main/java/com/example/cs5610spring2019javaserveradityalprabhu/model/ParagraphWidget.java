package com.example.cs5610spring2019javaserveradityalprabhu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "paragraphWidgets")
public class ParagraphWidget extends Widget{

	@Column(name = "ptext")
	private String ptext;

	public String getPText() {
		return ptext;
	}

	public void setPText(String text) {
		this.ptext = text;
	}

	
}