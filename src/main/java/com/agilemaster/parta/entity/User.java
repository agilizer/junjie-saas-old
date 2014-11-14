package com.agilemaster.parta.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * store in redis
 * key is username,unique,username is phone or email;
 * @author asdtiang
 *
 */
@Entity
@Table()
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5476752836924327116L;
	@Id
	@Column
    private String userId;
	@Column
    private String username; //用户名 phone or email
	@Column	
	private String fullName;
	@Column
	private    String sex;
	@Column
	private   String email;
	@Column
	private    boolean enabled;
	@Temporal(TemporalType.TIMESTAMP)
	private    Calendar dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	private    Calendar lastUpdated;
	@Temporal(TemporalType.TIMESTAMP)
	private    Calendar expiration;
	@Column
	private    String  position;
	@Column
	private    String  fax;
	@Column
	private   String  memo;
	@OneToOne()
	private   Organization org;
	@Column
	private  boolean apart = true;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Resource> resources;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Role> roles;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Group> groups;
   
    public User() {
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public Calendar getExpiration() {
		return expiration;
	}

	public void setExpiration(Calendar expiration) {
		this.expiration = expiration;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public boolean isApart() {
		return apart;
	}
	public boolean getApart() {
		return apart;
	}

	public void setApart(boolean apart) {
		this.apart = apart;
	}
   

}
