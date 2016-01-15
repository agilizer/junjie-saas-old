package com.agilemaster.partbase.entity;

import java.io.Serializable;

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
public class FileInfo  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3622051961310528586L;
	public static final String ID_NAME="FileInfo";
	@Id
	@Column
	private Long id; // 编号
	@ManyToOne()
	private BuildProject buildProject;
	@ManyToOne()
	private User author;
	@Column
	private String fileKey;
	@Column
	private String description;
	@Column
	private Boolean isDel = false;
	@Column
	private Boolean isOutSide = false;
	/**
	 * doc,docx,ppt,zip etc... xls,xlsx-->excel
	 */
	 @Temporal((TemporalType.TIMESTAMP))
	private java.util.Calendar dateCreated;
	 @Temporal((TemporalType.TIMESTAMP))
	private java.util.Calendar lastUpdated;
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
	public String getFileKey() {
		return fileKey;
	}
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Boolean getIsDel() {
		return isDel;
	}
	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}
	public Boolean getIsOutSide() {
		return isOutSide;
	}
	public void setIsOutSide(Boolean isOutSide) {
		this.isOutSide = isOutSide;
	}
	
}
