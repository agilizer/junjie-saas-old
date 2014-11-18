package com.agilemaster.parta.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table()
public class DocumentReceipt  implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1030889301091105825L;
	public static final String ID_NAME="DocumentReceipt";
	@Id
	@Column
	private Long id; // 编号
	@Column
	private Long version; 
	@OneToOne()
	private Document document;
	@Column
	private String receiptContent = "";
	@ManyToMany()
	private List<User> receiptUsers;
	@ManyToMany()
	private List<User> returnUsers;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public String getReceiptContent() {
		return receiptContent;
	}
	public void setReceiptContent(String receiptContent) {
		this.receiptContent = receiptContent;
	}
	public List<User> getReceiptUsers() {
		return receiptUsers;
	}
	public void setReceiptUsers(List<User> receiptUsers) {
		this.receiptUsers = receiptUsers;
	}
	public List<User> getReturnUsers() {
		return returnUsers;
	}
	public void setReturnUsers(List<User> returnUsers) {
		this.returnUsers = returnUsers;
	}
}
