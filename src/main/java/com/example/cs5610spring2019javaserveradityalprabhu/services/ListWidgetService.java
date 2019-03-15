package com.example.cs5610spring2019javaserveradityalprabhu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610spring2019javaserveradityalprabhu.model.ListWidget;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.ListWidgetRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")
public class ListWidgetService {

	@Autowired
	ListWidgetRepository listWidgetRepository;


	@GetMapping("/api/list/widget/{wid}")
	public ListWidget findWidgetById(
			@PathVariable("wid") Integer widgetId) {

		try {
			ListWidget widget = listWidgetRepository.findById(widgetId).get();
			return widget;

		}catch(Exception e) {
			return null;
		}
	}


	@PutMapping("/api/list/widget/{wid}")
	public ListWidget updateWidget(
			@PathVariable("wid") Integer widgetId,
			@RequestBody ListWidget widget) {

		try {
			ListWidget new_widget = listWidgetRepository.findById(widgetId).get();
			new_widget.setTitle(widget.getTitle());
			new_widget.setType(widget.getType());
			new_widget.setLText(widget.getLText());
			listWidgetRepository.save(new_widget);
			return new_widget;

		}catch(Exception e) {
			return null;
		}
	}


	@DeleteMapping("/api/list/widget/{wid}")
	public ListWidget deleteWidget(@PathVariable("wid") Integer widgetId) {

		try {
			ListWidget widget = listWidgetRepository.findById(widgetId).get();
			listWidgetRepository.deleteById(widgetId);
			return widget;

		}catch(Exception e) {
			return null;
		}
	}
}
