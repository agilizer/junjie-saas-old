package com.agilemaster.parta.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author asdtiang
 *
 */
@Entity
@Table()
class EventNotify   implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3929584517660139597L;
	public static final String ID_NAME="EventNotify";
	@Id
	@Column
	private Long id; // 编号
	@OneToOne()
    private Event event;
	@Column
    private EventNotifyType eventNotifyType;
    /**
     * json value
     */
	@Column(length=5000)
    private String notifyValue;
	
    public static enum EventNotifyType{
        ONCE,
        EVERY_DAY,
        EVERY_WEEK
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public EventNotifyType getEventNotifyType() {
		return eventNotifyType;
	}

	public void setEventNotifyType(EventNotifyType eventNotifyType) {
		this.eventNotifyType = eventNotifyType;
	}

	public String getNotifyValue() {
		return notifyValue;
	}

	public void setNotifyValue(String notifyValue) {
		this.notifyValue = notifyValue;
	}
    
    

}

