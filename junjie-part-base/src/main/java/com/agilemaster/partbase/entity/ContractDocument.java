package com.agilemaster.partbase.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ContractDocument
 * A domain class describes the data object and it's mapping to the database
 */
@Entity
@Table()
public class ContractDocument  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 109136241961720877L;
	public static final String ID_NAME="ContractDocument";
	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	@Id
	@Column
    private Long id; //编号
	@Column
	private Long version; 
	@ManyToOne
    private BuildProject buildProject;
    @Column
    private String code;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String contact;
    @ManyToOne()
    private ContractType contactType;
    @ManyToOne
    private User author;
    @Column
    private double contractSum = 0d;
//    float paymentRatio
    @Column
	private  double paymentSum;
    @Temporal((TemporalType.TIMESTAMP))
	private java.util.Calendar signDate;
    @OneToOne
	private FileInfo fileInfo;
	@Column(length=5000)
	private String signProblem;
    /* Automatic timestamping of GORM */
	 @Temporal((TemporalType.TIMESTAMP))
	private java.util.Calendar dateCreated;
	 @Temporal((TemporalType.TIMESTAMP))
	private java.util.Calendar lastUpdated;
	@ManyToOne
	private Organization partyB;
	@Column
	private String partybName;/*just record partyB name ,not save partyB*/
	@OneToMany()
    private List<Payment> payments;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public ContractType getContactType() {
		return contactType;
	}
	public void setContactType(ContractType contactType) {
		this.contactType = contactType;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public double getContractSum() {
		return contractSum;
	}
	public void setContractSum(double contractSum) {
		this.contractSum = contractSum;
	}
	public double getPaymentSum() {
		return paymentSum;
	}
	public void setPaymentSum(double paymentSum) {
		this.paymentSum = paymentSum;
	}
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	public String getSignProblem() {
		return signProblem;
	}
	public void setSignProblem(String signProblem) {
		this.signProblem = signProblem;
	}
	public Organization getPartyB() {
		return partyB;
	}
	public void setPartyB(Organization partyB) {
		this.partyB = partyB;
	}
	
	public String getPartybName() {
		return partybName;
	}
	public void setPartybName(String partybName) {
		this.partybName = partybName;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	public java.util.Calendar getSignDate() {
		return signDate;
	}
	public void setSignDate(java.util.Calendar signDate) {
		this.signDate = signDate;
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
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
}
