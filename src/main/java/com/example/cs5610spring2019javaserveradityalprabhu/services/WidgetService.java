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

import com.example.cs5610spring2019javaserveradityalprabhu.model.HeadingWidget;
import com.example.cs5610spring2019javaserveradityalprabhu.model.Topic;
import com.example.cs5610spring2019javaserveradityalprabhu.model.Widget;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.HeadingWidgetRepository;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.TopicRepository;
import com.example.cs5610spring2019javaserveradityalprabhu.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")
public class WidgetService {

	@Autowired
	WidgetRepository widgetRepository;

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	HeadingWidgetRepository headingWidgetRepository;


	@PostMapping("/api/topic/{tid}/widget")
	public Widget createWidget(
			@PathVariable("tid") Integer topicId,
			@RequestBody Widget widget) {


		try {
			Topic topic = topicRepository.findById(topicId).get();
			HeadingWidget hwidget = new HeadingWidget();
			hwidget.setTopic(topic);
			hwidget.setTitle(widget.getTitle());
			hwidget.setType("HEADING");
			hwidget.setText("New heading");
			hwidget.setSize(2);
			return headingWidgetRepository.save(hwidget);

		}catch(Exception e) {
			return null;
		}
	}


	@GetMapping("/api/topic/{tid}/widget")
	public List<Widget> findAllWidgets(
			@PathVariable("tid") Integer topicId) {

		try {
			Topic topic = topicRepository.findById(topicId).get();
			return topic.getWidgets();

		}catch(Exception e) {
			return null;
		}

	}


	@GetMapping("/api/widget/{wid}")
	public Widget findWidgetById(
			@PathVariable("wid") Integer widgetId) {

		try {
			Widget widget = widgetRepository.findById(widgetId).get();
			return widget;

		}catch(Exception e) {
			return null;
		}
	}


	@PutMapping("/api/widget/{wid}")
	public Widget updateWidget(
			@PathVariable("wid") Integer widgetId,
			@RequestBody Widget widget) {

		try {
			String type = widget.getType();

			Widget new_widget = widgetRepository.findById(widgetId).get();
			new_widget.setType(type);
			new_widget.setTitle(widget.getTitle());
			new_widget.setText(widget.getText());
			new_widget.setSize(widget.getSize());
			new_widget.setUrl(widget.getUrl());
			new_widget.setPText(widget.getPText());
			new_widget.setLText(widget.getLText());
//			new_widget.setListType(widget.getListType());
			widgetRepository.save(new_widget);

			switch(type) {
			case "HEADING":
				widgetRepository.changeWidgetDType("HeadingWidget", widget.getId());
				break;

			case "IMAGE":
				widgetRepository.changeWidgetDType("ImageWidget", widgetId);
				break;
				
			case "PARAGRAPH":
				widgetRepository.changeWidgetDType("ParagraphWidget", widgetId);
				break;
				
			case "LIST":
				widgetRepository.changeWidgetDType("ListWidget", widgetId);
				break;
				
			default:
				break;
			}

			return new_widget;

		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


	@DeleteMapping("/api/widget/{wid}")
	public Widget deleteWidget(@PathVariable("wid") Integer widgetId) {

		try {
			Widget widget = widgetRepository.findById(widgetId).get();
			widgetRepository.deleteById(widgetId);
			return widget;

		}catch(Exception e) {
			return null;
		}
	}
}
