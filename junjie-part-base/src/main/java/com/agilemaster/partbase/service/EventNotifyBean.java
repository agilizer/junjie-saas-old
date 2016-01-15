package com.agilemaster.partbase.service;

import java.io.Serializable;

public class EventNotifyBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3081988737281870814L;
	private Long eventId;// dataSourceKey +eventId,   1-1
	private String datasourceKey;
	private String smsTemplate;
	private String serverUrl;
	private int workStartHour;
    private int workStopHour;
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getDatasourceKey() {
		return datasourceKey;
	}
	public void setDatasourceKey(String datasourceKey) {
		this.datasourceKey = datasourceKey;
	}
	public String getSmsTemplate() {
		return smsTemplate;
	}
	public void setSmsTemplate(String smsTemplate) {
		this.smsTemplate = smsTemplate;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public int getWorkStartHour() {
		return workStartHour;
	}
	public void setWorkStartHour(int workStartHour) {
		this.workStartHour = workStartHour;
	}
	public int getWorkStopHour() {
		return workStopHour;
	}
	public void setWorkStopHour(int workStopHour) {
		this.workStopHour = workStopHour;
	}
	public String genJobId(){
		return datasourceKey+"-"+eventId;
	}
	
	
}
