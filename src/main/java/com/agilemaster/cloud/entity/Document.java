package com.agilemaster.cloud.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Document A domain class describes the data object and it's mapping to the
 * database
 */
@Entity
@Table()
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1474592345341730169L;
	/* Default (injected) attributes of GORM */
	// Long id
	// Long version
	@Id
	@Column
	@GeneratedValue
	private Long id; // 编号
	@Column
	private String description;
	@ManyToOne()
	private User author;
	@ManyToOne()
	private BuildProject buildProject;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdated;
	@OneToOne()
	private FileInfo fileInfo;
	@Column
	private boolean excelEdit;
	@ManyToOne()
	private DocumentDir documentDir;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private int showCount = 0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public BuildProject getBuildProject() {
		return buildProject;
	}
	public void setBuildProject(BuildProject buildProject) {
		this.buildProject = buildProject;
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
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	public boolean isExcelEdit() {
		return excelEdit;
	}
	public void setExcelEdit(boolean excelEdit) {
		this.excelEdit = excelEdit;
	}
	public DocumentDir getDocumentDir() {
		return documentDir;
	}
	public void setDocumentDir(DocumentDir documentDir) {
		this.documentDir = documentDir;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}
	
    
}
