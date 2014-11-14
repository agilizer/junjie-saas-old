package com.agilemaster.parta.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Payment
 * A domain class describes the data object and it's mapping to the database
 */
@Entity
@Table()
public class Payment  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4283480344335827617L;
	@Id
	@Column
	@GeneratedValue
    private Long id; //编号
	@ManyToOne()
    private BuildProject buildProject;
	@ManyToOne()
    private  User author;
	@ManyToOne()
    private ContractDocument contract;
	@Column
    private  float amount  ;     /* 付款额，永远小于产值 */
	@Column
    private  float outputValue; /*产值*/
	@Temporal(TemporalType.TIMESTAMP)
    private  Calendar paymentDate;
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCreated;
    @Column
    private String reason;
    @Column
    private boolean hasContract;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BuildProject getBuildProject() {
		return buildProject;
	}
	public void setBuildProject(BuildProject buildProject) {
		this.buildProject = buildProject;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public ContractDocument getContract() {
		return contract;
	}
	public void setContract(ContractDocument contract) {
		this.contract = contract;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getOutputValue() {
		return outputValue;
	}
	public void setOutputValue(float outputValue) {
		this.outputValue = outputValue;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean getHasContract() {
		return hasContract;
	}
	public boolean isHasContract() {
		return hasContract;
	}
	public void setHasContract(boolean hasContract) {
		this.hasContract = hasContract;
	}
	public Calendar getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
