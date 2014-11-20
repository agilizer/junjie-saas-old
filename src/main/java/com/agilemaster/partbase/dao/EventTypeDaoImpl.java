package com.agilemaster.partbase.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.service.ShareService;
import com.junjie.commons.db.client.JunjieJdbcOptions;
@Service
public class EventTypeDaoImpl implements EventTypeDao{

	@Autowired
	private JunjieJdbcOptions junjieJdbcOptions;
	@Autowired
	private ShareService shareService;
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> list() {
		return junjieJdbcOptions.queryForList("select id,name from event_type", null);
	}

}
