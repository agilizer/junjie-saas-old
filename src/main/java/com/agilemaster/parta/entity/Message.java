package com.agilemaster.parta.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Message
 * A domain class describes the data object and it's mapping to the database
 */
@Entity
@Table()
public class Message implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8472963415090220860L;
	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	@Id
	@Column
	@GeneratedValue
    private Long id; //编号
	@Column
    private String title;
	@Column(columnDefinition = "LongText")
    private String body;
	@ManyToOne()
    private User sender;
	@ManyToOne()
    private    Message mainMessage;   /*designed for replied messages and forward messages, nullable true*/
	@Column
    private boolean isSend = true ;   /*if false-->draftBox*/
	@Column
    private boolean isDel=false;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar	dateCreated;
    /**
     * search for username,searchName = all about users name
     */
	@Column
	private String searchName;
	@OneToMany()
	private List<MessageRecipient> recipients;
	@ManyToMany()
	private List<MessageTag> tags;
	@OneToMany()
	private List<FileInfo> fileInfos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public Message getMainMessage() {
		return mainMessage;
	}
	public void setMainMessage(Message mainMessage) {
		this.mainMessage = mainMessage;
	}
	public boolean isSend() {
		return isSend;
	}
	public void setSend(boolean isSend) {
		this.isSend = isSend;
	}
	public boolean isDel() {
		return isDel;
	}
	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}
	public Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public List<MessageRecipient> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<MessageRecipient> recipients) {
		this.recipients = recipients;
	}
	public List<MessageTag> getTags() {
		return tags;
	}
	public void setTags(List<MessageTag> tags) {
		this.tags = tags;
	}
	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}
}
