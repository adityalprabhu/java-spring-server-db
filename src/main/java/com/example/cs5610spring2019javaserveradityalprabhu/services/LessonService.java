package com.example.cs5610spring2019javaserveradityalprabhu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610spring2019javaserveradityalprabhu.model.Lesson;
import com.example.cs5610spring2019javaserveradityalprabhu.model.Module;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.LessonRepository;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")
public class LessonService {

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	LessonRepository lessonRepository;

	
	@PostMapping("/api/module/{mid}/lesson")
	public Lesson createLesson(
			@PathVariable("mid") Integer moduleId,
			@RequestBody Lesson lesson) {

		Module module;

		try {
			module = moduleRepository.findById(moduleId).get(); 
			lesson.setModule(module);
			return lessonRepository.save(lesson);
		}catch(Exception e) {
			return null;
		}
	}

	@GetMapping("/api/module/{mid}/lesson")
	public List<Lesson> findAllLessons(
			@PathVariable("mid") Integer moduleId) {

		Module module;

		try {
			module = moduleRepository.findById(moduleId).get();
			return module.getLessons();
		}catch(Exception e) {
			return null;
		}
	}


	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(
			@PathVariable("lid") Integer lessonId) {

		try {
			Lesson lesson = lessonRepository.findById(lessonId).get();
			return lesson;
		}catch(Exception e) {
			return null;
		}
	}

	@PutMapping("/api/lesson/{lid}")
	public Lesson updateLesson(
			@PathVariable("lid") Integer lessonId,
			@RequestBody Lesson lesson) {

		try {
			Lesson updatedLesson = lessonRepository.findById(lessonId).get();
			updatedLesson.setTitle(lesson.getTitle());
			return lessonRepository.save(updatedLesson);
		}catch(Exception e) {
			return null;
		}
	}

	@DeleteMapping("/api/lesson/{lid}")
	public Lesson deleteLesson(@PathVariable("lid") Integer lessonId) {

		try {
			Lesson lesson = lessonRepository.findById(lessonId).get();
			lessonRepository.deleteById(lessonId);
			return lesson;
		}catch(Exception e) {
			return null;
		}
	}
}
