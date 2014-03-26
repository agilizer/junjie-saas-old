package com.agilemaster.cloud.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table()
public class Company {
	@Id
	@Column
	@GeneratedValue
    private Long id;
	@Column(unique=true)
    private String name;
	@Column(unique=true)
    private String authKey;
	@Column
    private String contactName;
	@Column
    private String contactTel;
	@Column
    private String comTel;
	@Column
    private String comFax;
	@Column
    private String contactEmail;
	/**
	 * 正则前台区分。
	 */
	@Column
	private String address;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private CompanyContract companyContract;
	
	
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
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getComTel() {
		return comTel;
	}
	public void setComTel(String comTel) {
		this.comTel = comTel;
	}
	public String getComFax() {
		return comFax;
	}
	public void setComFax(String comFax) {
		this.comFax = comFax;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CompanyContract getCompanyContract() {
		return companyContract;
	}
	public void setCompanyContract(CompanyContract companyContract) {
		this.companyContract = companyContract;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	
	
}
