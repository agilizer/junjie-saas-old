package com.agilemaster.partbase.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.agilemaster.partbase.entity.Event;

public interface EventService {
	Map<String,Object> create(Event event,HttpServletRequest request);
	Map<String,Object> show(Long id);
}
