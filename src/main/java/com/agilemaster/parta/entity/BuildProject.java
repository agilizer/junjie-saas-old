package com.agilemaster.parta.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table()
public class BuildProject   implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5492391385688697311L;
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; //编号
	@Column
    private User author;
	@Column
    private String name;
	@Column
    private  String description;
	@Column
    private   String code;
	@Temporal(TemporalType.TIMESTAMP)
    private  java.util.Calendar dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
    private  java.util.Calendar endDate;
	@Temporal(TemporalType.TIMESTAMP)
    private  java.util.Calendar actualEndDate;
	@Temporal(TemporalType.TIMESTAMP)
    private   java.util.Calendar startDate;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public java.util.Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(java.util.Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	public java.util.Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(java.util.Calendar endDate) {
		this.endDate = endDate;
	}
	public java.util.Calendar getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(java.util.Calendar actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	public java.util.Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Calendar startDate) {
		this.startDate = startDate;
	}
	
	
	

}
