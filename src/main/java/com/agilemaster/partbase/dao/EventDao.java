package com.agilemaster.partbase.dao;

import java.util.Calendar;
import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.entity.User;
import com.junjie.commons.db.JdbcPage;

public interface EventDao {
	String ID_COUNT_NAME="event";
	Event save(Event event);
    Event show(Long id);
    int del(Long id);
    JdbcPage list(User user,Calendar start,Calendar end,int max,int offset);
    Event update(Event event);
    int updateProgress(Long eventId,int progress);
	int updateSeparateProgress(Long progressesId,int progress) ;
}
