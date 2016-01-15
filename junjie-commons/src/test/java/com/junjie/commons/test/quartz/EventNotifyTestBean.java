package com.junjie.commons.test.quartz;

import java.io.Serializable;

public class EventNotifyTestBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3081988737281870814L;
	private String eventId;// dataSourceKey +eventId,   1-1
	private String[] mobileNumbers;
    private String[] usernames;
    private String content;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getMobileNumbers() {
		return mobileNumbers;
	}
	public void setMobileNumbers(String[] mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
	public String[] getUsernames() {
		return usernames;
	}
	public void setUsernames(String[] usernames) {
		this.usernames = usernames;
	}
	
	
}
