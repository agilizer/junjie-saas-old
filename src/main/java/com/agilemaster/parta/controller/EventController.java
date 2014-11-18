package com.agilemaster.parta.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.parta.entity.Event;
import com.agilemaster.parta.util.WebParamsConvert;
import com.agilemaster.parta.web.bind.annotation.FormModel;

/**
 * 
 * @author abel.lee 2014年11月17日 下午4:48:34
 */
@RestController
public class EventController {
	@RequiresPermissions("event:create")
	@RequestMapping("/api/v1/event/create")
	public Event create(HttpServletRequest request) {
		Event event  = WebParamsConvert.convertMap(Event.class, request.getParameterMap(), null);
		return event;
	}

}
