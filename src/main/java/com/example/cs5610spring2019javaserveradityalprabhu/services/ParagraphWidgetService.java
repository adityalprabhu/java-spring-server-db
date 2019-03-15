package com.example.cs5610spring2019javaserveradityalprabhu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610spring2019javaserveradityalprabhu.model.ParagraphWidget;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.ParagraphWidgetRepository;

	@RestController
	@CrossOrigin(origins = "*", allowCredentials ="true")
	public class ParagraphWidgetService {

		@Autowired
		ParagraphWidgetRepository paragraphWidgetRepository;


		@GetMapping("/api/paragraph/widget/{wid}")
		public ParagraphWidget findWidgetById(
				@PathVariable("wid") Integer widgetId) {

			try {
				ParagraphWidget widget = paragraphWidgetRepository.findById(widgetId).get();
				return widget;

			}catch(Exception e) {
				return null;
			}
		}


		@PutMapping("/api/paragraph/widget/{wid}")
		public ParagraphWidget updateWidget(
				@PathVariable("wid") Integer widgetId,
				@RequestBody ParagraphWidget widget) {

			try {
				ParagraphWidget new_widget = paragraphWidgetRepository.findById(widgetId).get();
				new_widget.setTitle(widget.getTitle());
				new_widget.setType(widget.getType());
				new_widget.setPText(widget.getPText());
				paragraphWidgetRepository.save(new_widget);
				return new_widget;

			}catch(Exception e) {
				return null;
			}
		}


		@DeleteMapping("/api/paragraph/widget/{wid}")
		public ParagraphWidget deleteWidget(@PathVariable("wid") Integer widgetId) {

			try {
				ParagraphWidget widget = paragraphWidgetRepository.findById(widgetId).get();
				paragraphWidgetRepository.deleteById(widgetId);
				return widget;

			}catch(Exception e) {
				return null;
			}
		}
	}
