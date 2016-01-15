package com.agilemaster.partbase.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.dao.EventTypeDao;

@Service
public class EventTypeServiceImpl implements EventTypeService{

	@Autowired
	UserService userService;
	@Autowired
	private EventTypeDao eventTypeDao;
	@Override
	public List<Map<String, Object>> list() {
		if(userService.isAuth()){
			return eventTypeDao.list();
		}else{
			return null;
		}
	}

}
