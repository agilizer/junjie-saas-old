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
@Entity
@Table()
public class NewsType  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -552607323029274279L;
	@Id
	@Column
	@GeneratedValue
	private Long id; // 编号
	@Column
    private String name;
    @Column(columnDefinition = "LongText")
    private String description;
    @ManyToOne()
    private User author;
    /**
     * climb news use
     */
    @Column
    private String tagName;
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar 	lastUpdated;
	@Temporal(TemporalType.TIMESTAMP)
    private  Calendar	dateCreated;
	@Column
    private Boolean isOutside=false;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Calendar getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Calendar lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	public boolean isOutside() {
		return isOutside;
	}
	public Boolean getIsOutside() {
		return isOutside;
	}
	public void setIsOutside(Boolean isOutside) {
		this.isOutside = isOutside;
	}
	public void setOutside(boolean isOutside) {
		this.isOutside = isOutside;
	}
	
	
}
