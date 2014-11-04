package com.agilemaster.cloud.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table()
public class SystemLog  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1069216618102158725L;
	@Id
	@Column
	@GeneratedValue
	private Long id; // 编号
	@Column(columnDefinition = "LongText")
	private String logJson = "";
	@Column
	private int total = 0;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateCreated;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogJson() {
		return logJson;
	}
	public void setLogJson(String logJson) {
		this.logJson = logJson;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
