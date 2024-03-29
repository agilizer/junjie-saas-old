package com.agilemaster.partbase.entity;

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
@Entity
@Table()
public class DocumentDir  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3307963570065337240L;
	public static final String ID_NAME="DocumentDir";
	@Id
	@Column
    private Long id; //编号
	@Column
	private Long version; 
	@ManyToOne()
	private BuildProject buildProject;
	@ManyToOne()
	private User author;
	@Column
	private String name;
	@Column
	private String description;
	@ManyToOne()
	private DocumentDir parentDir;
	@Column
	private Boolean mainShow = false;
	@Column
	private Integer sequence;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdated;
	
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
	
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
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
	public DocumentDir getParentDir() {
		return parentDir;
	}
	public void setParentDir(DocumentDir parentDir) {
		this.parentDir = parentDir;
	}
	
	public Boolean getMainShow() {
		return mainShow;
	}
	public void setMainShow(Boolean mainShow) {
		this.mainShow = mainShow;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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
	
	
}
