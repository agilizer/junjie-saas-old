package com.agilemaster.parta.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.entity.Event;
import com.agilemaster.parta.service.ShareService;
import com.junjie.commons.db.client.JunjieJdbcOptions;

@Service
public class EventDaoImpl implements EventDao{

	@Autowired
	private ShareService shareService;
	@Autowired
	private JunjieJdbcOptions junjieJdbcOptions;
	@Override
	public Event save(Event event) {
		Long id = shareService.saveAutoGenId(event);
		event.setId(id);
		return event;
	}

}
