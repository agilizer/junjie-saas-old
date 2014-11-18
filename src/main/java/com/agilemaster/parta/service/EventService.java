package com.agilemaster.parta.service;

import javax.servlet.http.HttpServletRequest;

import com.agilemaster.parta.entity.Event;

public interface EventService {
	Event create(Event event,HttpServletRequest request);
}
