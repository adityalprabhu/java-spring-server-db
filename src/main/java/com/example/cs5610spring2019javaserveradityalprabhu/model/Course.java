package com.example.cs5610spring2019javaserveradityalprabhu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="courses")
public class Course {
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	@ManyToOne()
	@JsonIgnore
	private User faculty;
	
	@OneToMany(mappedBy="course", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Module> modules = new ArrayList<Module>();
	
	public Course() {}
	
	public Course(int id, String title, User faculty) {
		this.id = id;
		this.title = title;
		this.faculty = faculty;
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
	public User getFaculty() {
		return faculty;
	}
	public void setFaculty(User faculty) {
		this.faculty = faculty;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
		
}
