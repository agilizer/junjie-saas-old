package com.junjie.commons.quartz;

import java.io.Serializable;
import java.util.List;

public class EventNotifyBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3081988737281870814L;
	private String eventId;// dataSourceKey +eventId,   1-1
	private List<String> mobileNumbers;
    private List<String> username;
    private String content;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public List<String> getMobileNumbers() {
		return mobileNumbers;
	}
	public void setMobileNumbers(List<String> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
	public List<String> getUsername() {
		return username;
	}
	public void setUsername(List<String> username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
