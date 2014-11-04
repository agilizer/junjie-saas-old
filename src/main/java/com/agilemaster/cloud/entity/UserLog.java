package com.agilemaster.cloud.entity;

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
 * User: asdtiang Date: 13-10-28
 */
@Entity
@Table()
public class UserLog  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5457509564222475048L;
	@Id
	@Column
	@GeneratedValue
	private Long id; // 编号
	@ManyToOne()
	private User author;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdated;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dairyDate;
	@Column
	private String title;
	@Column(columnDefinition = "LongText")
	private String dairy; /* 给用户编辑器，自己填写 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Calendar getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Calendar lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Calendar getDairyDate() {
		return dairyDate;
	}
	public void setDairyDate(Calendar dairyDate) {
		this.dairyDate = dairyDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDairy() {
		return dairy;
	}
	public void setDairy(String dairy) {
		this.dairy = dairy;
	}

}
