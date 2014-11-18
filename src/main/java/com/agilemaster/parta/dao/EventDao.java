package com.agilemaster.parta.dao;

import com.agilemaster.parta.entity.Event;

public interface EventDao {
	String ID_COUNT_NAME="event";
	Event save(Event event);
}
