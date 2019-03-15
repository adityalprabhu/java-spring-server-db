package com.example.cs5610spring2019javaserveradityalprabhu.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610spring2019javaserveradityalprabhu.model.HeadingWidget;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.HeadingWidgetRepository;


@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")
public class HeadingWidgetService {

	@Autowired
	HeadingWidgetRepository headingWidgetRepository;


	@GetMapping("/api/heading/widget/{wid}")
	public HeadingWidget findWidgetById(
			@PathVariable("wid") Integer widgetId) {

		try {
			HeadingWidget widget = headingWidgetRepository.findById(widgetId).get();
			return widget;

		}catch(Exception e) {
			return null;
		}
	}


	@PutMapping("/api/heading/widget/{wid}")
	public HeadingWidget updateWidget(
			@PathVariable("wid") Integer widgetId,
			@RequestBody HeadingWidget widget) {

		try {
			HeadingWidget new_widget = headingWidgetRepository.findById(widgetId).get();
			new_widget.setTitle(widget.getTitle());
			new_widget.setType(widget.getType());
			new_widget.setText(widget.getText());
			new_widget.setSize(widget.getSize());
			headingWidgetRepository.save(new_widget);
			return new_widget;

		}catch(Exception e) {
			return null;
		}
	}


	@DeleteMapping("/api/heading/widget/{wid}")
	public HeadingWidget deleteWidget(@PathVariable("wid") Integer widgetId) {

		try {
			HeadingWidget widget = headingWidgetRepository.findById(widgetId).get();
			headingWidgetRepository.deleteById(widgetId);
			return widget;

		}catch(Exception e) {
			return null;
		}
	}
}
