package com.agilemaster.partbase.service;

import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.entity.EventNotify;

public interface EventNotifyJobService {
	final static String SMS_URL_PATH="/s/e";
	void sendEventSms(Event event,String action);
	void addEventNotify(EventNotify notify);

}
