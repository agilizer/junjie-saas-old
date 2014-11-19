package com.agilemaster.parta.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.agilemaster.parta.entity.Event;

public interface EventService {
	Map<String,Object> create(Event event,HttpServletRequest request);
}
