package com.example.cs5610spring2019javaserveradityalprabhu.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610spring2019javaserveradityalprabhu.model.Course;
import com.example.cs5610spring2019javaserveradityalprabhu.model.Lesson;
import com.example.cs5610spring2019javaserveradityalprabhu.model.Module;
import com.example.cs5610spring2019javaserveradityalprabhu.model.Topic;
import com.example.cs5610spring2019javaserveradityalprabhu.model.User;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.CourseRepository;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")

public class CourseService {
	//	static CourseService myInstance = null;
	//	User john = new User( "john", "password", "John", "Doe",  "8473843434", "john@email.com", "Student", "1990-12-01");
	User john = new User();

	Course webdev = new Course(123, "WebDev", john);
	Course datamining   = new Course(234, "DataMining", john);

	static List<Course> courses = new ArrayList<Course>();

	List<Module> modules = new ArrayList<Module>();
	List<Lesson> lessons = new ArrayList<Lesson>();
	List<Topic> topics = new ArrayList<Topic>();

	Module module1 = new Module(123, "Module 1");
	Module module2 = new Module(234, "Module 2");

	Lesson lesson1 = new Lesson(122345, "Lesson 1");
	Lesson lesson2 = new Lesson(233232, "Lesson 2");

	Topic topic1 = new Topic (121212, "Topic 1");



	{
		if(courses.size() < 1) {

			topics.add(topic1);

			lesson1.setTopics(topics);
			lesson2.setTopics(topics);

			lessons.add(lesson1);
			lessons.add(lesson2);

			module1.setLessons(lessons);
			module2.setLessons(lessons);

			modules.add(module1);
			modules.add(module2);

			//			webdev.setModules(modules);
			//			datamining.setModules(modules);

			courses.add(webdev);
			courses.add(datamining);
		}
	}

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	UserRepository userRepository;


	@PostMapping("/api/courses")
	public Course createCourse
	(@RequestBody Course course, HttpSession session) {

		User currentUser = (User) session.getAttribute("currentUser");
		course.setFaculty(currentUser);
		Course new_course = courseRepository.save(course); 
		return new_course;
	}
	
	@PostMapping("/api/user/{uid}/courses")
	public Course createCourseForUser
	(@PathVariable("uid") Integer userId, @RequestBody Course course, HttpSession session) {

//		User currentUser = (User) session.getAttribute("currentUser");
		course.setFaculty(userRepository.findById(userId).get());
		Course new_course = courseRepository.save(course); 
		return new_course;
	}


	@GetMapping("/api/courses")
	public List<Course> findAllCourses(HttpSession session){
		User currentUser = (User) session.getAttribute("currentUser");

		if(currentUser == null) {
			return null;
		}	

		return userRepository.findById(currentUser.getId()).get().getAuthoredCourses();
 
	}
	
	@GetMapping("/api/user/{uid}/courses")
	public List<Course> findAllCoursesForUser(@PathVariable("uid") Integer userId, HttpSession session){
//		User currentUser = (User) session.getAttribute("currentUser");
//
//		if(currentUser == null) {
//			return null;
//		}	

		return userRepository.findById(userId).get().getAuthoredCourses();

	}


	@GetMapping("/api/courses/{cid}")
	public Course findCourseById(@PathVariable("cid") Integer courseId){

		Course course;

		try {
			course = courseRepository.findById(courseId).get();
			return course;	
		}catch(Exception e) {
			return null;
		}
	}


	@PutMapping("/api/courses/{cid}")
	public Course updateCourse(@PathVariable("cid") Integer courseId, @RequestBody Course course){

		Course updatedCourse;
		try {
			updatedCourse = courseRepository.findById(courseId).get();
			updatedCourse.setTitle(course.getTitle());
			courseRepository.save(updatedCourse);
			return updatedCourse;
		}catch(Exception e) {
			return null;
		}	
	}


	@DeleteMapping("/api/courses/{cid}")
	public Course deleteCourse(@PathVariable("cid") Integer courseId) {
		Course course;
		try {
			course = courseRepository.findById(courseId).get();
			courseRepository.deleteById(courseId);
			return course;
		}catch(Exception e) {
			return null;
		}
	}
}
