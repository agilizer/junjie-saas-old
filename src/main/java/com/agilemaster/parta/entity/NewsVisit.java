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
 * NewsVisit A domain class describes the data object and it's mapping to the
 * database
 */
@Entity
@Table()
public class NewsVisit  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3558305186284463352L;
	public static final String ID_NAME="NewsVisit";
	@Id
	@Column
	private Long id; // 编号
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateCreated;
	@ManyToOne()
	private User visitor;
	@ManyToOne()
	private News news;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	public User getVisitor() {
		return visitor;
	}
	public void setVisitor(User visitor) {
		this.visitor = visitor;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	

}
