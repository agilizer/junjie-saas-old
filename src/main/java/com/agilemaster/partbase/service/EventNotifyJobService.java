package com.agilemaster.partbase.service;

import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.entity.EventNotify;

public interface EventNotifyJobService {
	final static String SMS_URL_PATH="/s/e";
	void sendEventSms(Event event,String action,EventNotifyBean notifyBean);
	/**
	 * job invoke method
	 * @param notifyBean
	 */
	void sendNotify(EventNotifyBean notifyBean);
	
    
}
