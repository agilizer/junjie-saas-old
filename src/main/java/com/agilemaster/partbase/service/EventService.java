package com.agilemaster.partbase.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.agilemaster.partbase.entity.Event;
import com.junjie.commons.db.JdbcPage;

public interface EventService {
	Map<String,Object> create(Event event,HttpServletRequest request);
	Map<String,Object> show(Long id);
	Map<String,Object> edit(Long id,HttpServletRequest request);
	Map<String,Object> del(Long id);
	JdbcPage list(long startTime,Long endTime,int max,int offset);
}
