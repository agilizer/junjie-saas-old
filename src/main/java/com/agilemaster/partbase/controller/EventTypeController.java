package com.agilemaster.partbase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.service.EventTypeService;
import com.agilemaster.partbase.service.UserService;

@RestController
public class EventTypeController {
	@Autowired
	EventTypeService eventTypeService;
	@Autowired
	UserService userService;
	@RequestMapping("/api/v1/eventType/list")
	public List<Map<String, Object>> create() {
		return eventTypeService.list();
	}

}
