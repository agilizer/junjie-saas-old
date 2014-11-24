package com.agilemaster.partbase.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.service.EventService;
import com.agilemaster.partbase.util.WebParamsConvert;

/**
 * 
 * @author abel.lee 2014年11月17日 下午4:48:34
 */
@RestController
public class EventController {
	@Autowired
	EventService eventService;
	@RequiresPermissions("event:create")
	@RequestMapping("/api/v1/event/create")
	public Map<String,Object> create(HttpServletRequest request) {
		Event event  = WebParamsConvert.convertMap(Event.class, request.getParameterMap(), null);
		Map<String,Object> map = eventService.create(event, request);
		return map;
	}
	
	@RequiresPermissions("event:show")
	@RequestMapping("/api/v1/event/show")
	public Map<String,Object> show(Long id) {
		Map<String,Object> map = eventService.show(id);
		return map;
	}
	

}
