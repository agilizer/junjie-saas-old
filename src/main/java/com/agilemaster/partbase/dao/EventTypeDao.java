package com.agilemaster.partbase.dao;

import java.util.List;
import java.util.Map;

public interface EventTypeDao {
	String ID_COUNT_NAME="event_type";
    List<Map<String,Object>> list();
}
