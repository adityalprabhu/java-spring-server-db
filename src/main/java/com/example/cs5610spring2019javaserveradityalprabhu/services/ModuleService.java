package com.example.cs5610spring2019javaserveradityalprabhu.services;

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
import com.example.cs5610spring2019javaserveradityalprabhu.model.Module;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.CourseRepository;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")
public class ModuleService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	ModuleRepository moduleRepository;


	@PostMapping("/api/courses/{cid}/modules")
	public Module createModule(
			@PathVariable("cid") Integer courseId,
			@RequestBody Module module,
			HttpSession session) {

		try {
			Course course = courseRepository.findById(courseId).get();
			module.setCourse(course);
			return moduleRepository.save(module);
		}catch(Exception e) {
			return null;
		}
	}


	@GetMapping("/api/courses/{cid}/modules")
	public List<Module> findAllModules(@PathVariable("cid") Integer courseId) {

		Course course;
		try{
			course = courseRepository.findById(courseId).get();
			return course.getModules();
		}catch(Exception e) {
			return null;
		}
	}

	@GetMapping("/api/modules/{mid}")
	public Module findModuleById(@PathVariable("mid") Integer moduleId) {


		Module module;
		try {
			module = moduleRepository.findById(moduleId).get();
			return module;
		}catch(Exception e) {
			return null;
		}
	}

	@PutMapping("/api/modules/{mid}")
	public Module updateModule(
			@PathVariable("mid") Integer moduleId,
			@RequestBody Module module) {

		Module updatedModule;
		try {
			updatedModule = moduleRepository.findById(moduleId).get();
			updatedModule.setTitle(module.getTitle());
			return moduleRepository.save(updatedModule);
		}catch(Exception e) {
			return null;
		}
	}

	@DeleteMapping("/api/modules/{mid}")
	public Module deleteModule(@PathVariable("mid") Integer moduleId) {

		Module module;
		try {
			module = moduleRepository.findById(moduleId).get();
			moduleRepository.deleteById(moduleId);
			return module;
		}catch(Exception e) {
			return null;
		}
	}
}
