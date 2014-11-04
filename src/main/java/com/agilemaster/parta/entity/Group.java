/*
 *  Nimble, an extensive application base for Grails
 *  Copyright (C) 2010 Bradley Beddoes
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.agilemaster.parta.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Represents a grouping of users in a Nimble based appication
 *
 * @author Bradley Beddoes
 */
@Entity
@Table()
public class Group  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1292881854964260695L;
	@Id
	@Column
	@GeneratedValue
    private Long id; //编号
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String realm;
	@Column
	private boolean external = false;
	@Column
	private boolean protect = false;
	@Temporal(TemporalType.DATE)
	private java.util.Calendar dateCreated;
	@Temporal(TemporalType.DATE)
	private java.util.Calendar lastUpdated;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Role> roles;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<User> users;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Resource> resources;
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
	public String getRealm() {
		return realm;
	}
	public void setRealm(String realm) {
		this.realm = realm;
	}
	public boolean isExternal() {
		return external;
	}
	public void setExternal(boolean external) {
		this.external = external;
	}
	public boolean isProtect() {
		return protect;
	}
	public void setProtect(boolean protect) {
		this.protect = protect;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<User> getUsers() {
		return users;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public java.util.Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(java.util.Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	public java.util.Calendar getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(java.util.Calendar lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}
