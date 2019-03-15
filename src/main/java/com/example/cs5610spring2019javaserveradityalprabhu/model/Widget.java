package com.example.cs5610spring2019javaserveradityalprabhu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="widgets")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Widget {

	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	protected String title;
	private String type;
	private String text;
	private Integer size;
	private String listType;
	private String url;
	private String ptext;
	private String ltext;


	@ManyToOne()
	@JsonIgnore
	private Topic topic;
	
	public Topic getTopic() {
		return topic;
	}
	
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

//	public List<String> getListItems() {
//		return listItems;
//	}
//
//	public void setListItems(List<String> listItems) {
//		this.listItems = listItems;
//	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}
	
	public String getPText() {
		return ptext;
	}

	public void setPText(String ptext) {
		this.ptext = ptext;
	}

	public String getLText() {
		return ltext;
	}

	public void setLText(String ltext) {
		this.ltext = ltext;
	}
	
}