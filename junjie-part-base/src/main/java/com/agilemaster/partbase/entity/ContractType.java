package com.agilemaster.partbase.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table()
public class ContractType  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6367389784031188336L;
	public static final String ID_NAME="ContractType";
	@Id
	@Column
    private Long id; //编号
	@Column
	private Long version; 
	@Column
    private String name;
	@Column(length=1000)
    private String description;
	
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
