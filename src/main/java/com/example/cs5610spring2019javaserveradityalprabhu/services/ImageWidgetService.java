package com.example.cs5610spring2019javaserveradityalprabhu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610spring2019javaserveradityalprabhu.model.ImageWidget;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.ImageWidgetRepository;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.WidgetRepository;


@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")
public class ImageWidgetService {

	@Autowired
	ImageWidgetRepository imageWidgetRepository;
	
	@Autowired
	WidgetRepository widgetRepository;


	@GetMapping("/api/image/widget/{wid}")
	public ImageWidget findWidgetById(
			@PathVariable("wid") Integer widgetId) {

		try {
			ImageWidget widget = imageWidgetRepository.findById(widgetId).get();
			return widget;

		}catch(Exception e) {
			return null;
		}
	}


	@PutMapping("/api/image/widget/{wid}")
	public ImageWidget updateWidget(
			@PathVariable("wid") Integer widgetId,
			@RequestBody ImageWidget widget) {

		try {
			ImageWidget new_widget = imageWidgetRepository.findById(widgetId).get();
			new_widget.setTitle(widget.getTitle());
			new_widget.setType(widget.getType());
			new_widget.setImgUrl(widget.getImgUrl());
			imageWidgetRepository.save(new_widget);
			return new_widget;

		}catch(Exception e) {
			return null;
		}
	}


	@DeleteMapping("/api/image/widget/{wid}")
	public ImageWidget deleteWidget(@PathVariable("wid") Integer widgetId) {

		try {
			ImageWidget widget = imageWidgetRepository.findById(widgetId).get();
			imageWidgetRepository.deleteById(widgetId);
			return widget;

		}catch(Exception e) {
			return null;
		}
	}
}
