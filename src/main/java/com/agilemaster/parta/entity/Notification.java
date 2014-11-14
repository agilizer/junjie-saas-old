package com.agilemaster.parta.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User: asdtiang Date: 13-8-31
 */
@Entity
@Table()
public class Notification  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6655519680580291680L;
	@Id
	@Column
	@GeneratedValue
	private Long id; // 编号
	/**
	 * notify Message or other Info Id
	 */
	@Column
	private Long showId;
	/**
	 * notify content
	 */
	@Column(columnDefinition = "LongText")
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateCreated;
	@ManyToOne()
	private User user;
	@Column
	private NotifyType notifyType;
	@Column
	private Boolean read = false;

	public static enum NotifyType {
		MESSAGE, MESSAGE_READ, MESSAGE_RECEIVE, MESSAGE_REPLY, EVENT_CHANGE, DOCUMENT
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public NotifyType getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(NotifyType notifyType) {
		this.notifyType = notifyType;
	}

	public boolean isRead() {
		return read;
	}
	public boolean getRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	
}
