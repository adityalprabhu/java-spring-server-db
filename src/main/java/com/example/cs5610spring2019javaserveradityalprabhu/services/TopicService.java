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
import com.example.cs5610spring2019javaserveradityalprabhu.model.Topic;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.LessonRepository;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")
public class TopicService {

	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	TopicRepository topicRepository;


	@PostMapping("/api/lesson/{lid}/topic")
	public Topic createTopic(
			@PathVariable("lid") Integer lessonId,
			@RequestBody Topic topic) {


		try {
			Lesson lesson = lessonRepository.findById(lessonId).get();
			topic.setLesson(lesson);
			return topicRepository.save(topic);

		}catch(Exception e) {
			return null;
		}
	}


	@GetMapping("/api/lesson/{lid}/topic")
	public List<Topic> findAllTopics(
			@PathVariable("lid") Integer lessonId) {

		try {
			Lesson lesson = lessonRepository.findById(lessonId).get();
			return lesson.getTopics();

		}catch(Exception e) {
			return null;
		}

	}


	@GetMapping("/api/topic/{tid}")
	public Topic findTopicById(
			@PathVariable("tid") Integer topicId) {

		try {
			Topic topic = topicRepository.findById(topicId).get();
			return topic;

		}catch(Exception e) {
			return null;
		}
	}


	@PutMapping("/api/topic/{tid}")
	public Topic updateTopic(
			@PathVariable("tid") Integer topicId,
			@RequestBody Topic topic) {

		try {
			Topic new_topic = topicRepository.findById(topicId).get();
			new_topic.setTitle(topic.getTitle());
			topicRepository.save(new_topic);
			return new_topic;

		}catch(Exception e) {
			return null;
		}
	}


	@DeleteMapping("/api/topic/{tid}")
	public Topic deleteTopic(@PathVariable("tid") Integer topicId) {

		try {
			Topic topic = topicRepository.findById(topicId).get();
			topicRepository.deleteById(topicId);
			return topic;

		}catch(Exception e) {
			return null;
		}
	}
}
