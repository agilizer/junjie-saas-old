package com.agilemaster.parta.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MessageRecipient
 * A domain class describes the data object and it's mapping to the database
 */
@Entity
@Table()
class MessageRecipient  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1585709436093601846L;
	public static final String ID_NAME="MessageRecipient";
	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	@Id
	@Column
    private Long id; //编号
	
	@OneToOne()
    private User recipient;
	@Column
    private MessageStatus status = MessageStatus.UNREAD;
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
    @Temporal(TemporalType.TIMESTAMP)
    private	Calendar	readDate;

    public static enum MessageStatus{
        UNREAD,
        READ,
        DELETED
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public Calendar getReadDate() {
		return readDate;
	}

	public void setReadDate(Calendar readDate) {
		this.readDate = readDate;
	}
    
}


