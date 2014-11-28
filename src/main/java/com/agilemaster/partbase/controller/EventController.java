package com.agilemaster.partbase.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.service.EventService;
import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.utils.JunjieStaticMethod;

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
		Event event = new Event();
		Map<String,Object> map = eventService.create(event, request);
		return map;
	}
	
	@RequiresPermissions("event:show")
	@RequestMapping("/api/v1/event/show")
	public Map<String,Object> show(Long id) {
		Map<String,Object> map = eventService.show(id);
		return map;
	}
	@RequiresPermissions("event:edit")
	@RequestMapping("/api/v1/event/edit")
	public Map<String,Object> edit(Long id,HttpServletRequest request) {
		Map<String,Object> map = eventService.edit(id,request);
		return map;
	}
	@RequiresPermissions("event:edit")
	@RequestMapping("/api/v1/event/del")
	public Map<String,Object> del(Long id) {
		Map<String,Object> map = eventService.del(id);
		return map;
	}
	@RequiresPermissions("event:edit")
	@RequestMapping("/api/v1/event/list")
	public JdbcPage list(HttpServletRequest request) {
		int max = JunjieStaticMethod.genIntValue(request, "max");
		int offset = JunjieStaticMethod.genIntValue(request, "offset");
		Long startTime = JunjieStaticMethod.genLongValue(request, "startTime");
		Long endTime = JunjieStaticMethod.genLongValue(request, "endTime");
		JdbcPage jdbcPage = eventService.list(startTime, endTime, max, offset);
		return jdbcPage;
	}
	

}
