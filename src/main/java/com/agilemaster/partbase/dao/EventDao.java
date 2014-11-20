package com.agilemaster.partbase.dao;

import com.agilemaster.partbase.entity.Event;

public interface EventDao {
	String ID_COUNT_NAME="event";
	Event save(Event event);
}
