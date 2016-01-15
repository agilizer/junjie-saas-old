package com.agilemaster.partbase.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * site config values,init to application scope:servletContext.configMap
 * in gsp:${application?.configMap?.mapName}
 */
@Entity
@Table()
public class ConfigDomain  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2840825329019055598L;
	public static final String ID_NAME="ConfigDomain";
	@Id
	@Column
    private Long id; //编号
	@Column
	private Long version; 
	@Column
    private String mapName;
	@Column
    private String mapValue;
	@Column
    private String description;
	@Column
    private  ValueType valueType;
	@Column
    Boolean editable=false;

 
   public static enum ValueType {
		Integer,
		String,
        Boolean
	}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}

public String getMapName() {
	return mapName;
}

public Long getVersion() {
	return version;
}


public void setVersion(Long version) {
	this.version = version;
}


public void setMapName(String mapName) {
	this.mapName = mapName;
}


public String getMapValue() {
	return mapValue;
}


public void setMapValue(String mapValue) {
	this.mapValue = mapValue;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public ValueType getValueType() {
	return valueType;
}


public void setValueType(ValueType valueType) {
	this.valueType = valueType;
}


public Boolean getEditable() {
	return editable;
}


public void setEditable(Boolean editable) {
	this.editable = editable;
}

   
   
}