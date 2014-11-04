package com.agilemaster.parta.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Organization
 * represents a *B Party* Organization
 */
@Entity
@Table()
public class Organization  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5882627034573728561L;
	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	@Id
	@Column
	@GeneratedValue
    private Long id; //编号
	@Column
    private String name;
	@Column
    private String address;
	@Column
    private String contact;
	@Column
    private  String contactTel;
	@Column
    private  String scope;
	@Column
    private String qualification;
	@Column
    private String brand;
	@Column
    private Integer capital;
	@Column
    private String memo;
	@Column
    private String telephone;
	@Column
    private  String fax;
	@Column
    private  String qq;
	@Column
    private String email;
	@Column
    private  String website;
	@Column
    private  User user;
	@Column
    private boolean onSite = false;
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private  BuildProject buildProject;
    /* Automatic timestamping of GORM */
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar	dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar	lastUpdated;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Integer getCapital() {
		return capital;
	}
	public void setCapital(Integer capital) {
		this.capital = capital;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isOnSite() {
		return onSite;
	}
	public void setOnSite(boolean onSite) {
		this.onSite = onSite;
	}
	public BuildProject getBuildProject() {
		return buildProject;
	}
	public void setBuildProject(BuildProject buildProject) {
		this.buildProject = buildProject;
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

}

