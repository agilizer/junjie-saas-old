package com.agilemaster.partbase.entity;

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
	public static final String ID_NAME="Message";
	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	@Id
	@Column
    private Long id; //编号
	@Column
	private Long version; 
	@Column
    private String title;
	@Column(columnDefinition = "LongText")
    private String body;
	@ManyToOne()
    private User sender;
	@ManyToOne()
    private    Message mainMessage;   /*designed for replied messages and forward messages, nullable true*/
	@Column
    private Boolean send = true ;   /*if false-->draftBox*/
	@Column
    private Boolean del=false;
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
	public Boolean getSend() {
		return send;
	}
	public void setSend(Boolean send) {
		this.send = send;
	}
	public Boolean getDel() {
		return del;
	}
	public void setDel(Boolean del) {
		this.del = del;
	}
	
	
}
