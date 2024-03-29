package com.agilemaster.partbase.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table()
public class EventProgress  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6305564874707686962L;
	public static final String ID_NAME="EventProgress";
	@Id
	@Column
	private Long id; // 编号
	@Column
	private Long version; 
	@Column
	private Long eventId;
	@ManyToOne()
	private User user;
	@Column
	private Short progress = 0;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar finishDate;
	
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public short getProgress() {
		return progress;
	}
	public void setProgress(short progress) {
		this.progress = progress;
	}
	public Calendar getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Calendar finishDate) {
		this.finishDate = finishDate;
	}
}
