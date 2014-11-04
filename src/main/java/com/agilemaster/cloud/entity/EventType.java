package com.agilemaster.cloud.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author asdtiang
 *
 */
@Entity
@Table()
public class EventType  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1839564680021482025L;
	@Id
	@Column
	@GeneratedValue
	private Long id; // 编号
	@Column
	private String name;
	@Column
	private String description;
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
	
}
