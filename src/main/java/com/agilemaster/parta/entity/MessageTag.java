package com.agilemaster.parta.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MessageTag A domain class describes the data object and it's mapping to the
 * database
 */
@Entity
@Table()
public class MessageTag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1378563417251161038L;
	public static final String ID_NAME="MessageTag";
	@Id
	@Column
	private Long id; // 编号
	/* Default (injected) attributes of GORM */
	// Long id
	// Long version
	@Column
	private String name;
	@Column
	private String label;
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
