package com.agilemaster.parta.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * News A domain class describes the data object and it's mapping to the
 * database
 */
@Entity
@Table()
public class News implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3906344097535447783L;
	/* Default (injected) attributes of GORM */
	// Long id
	// Long version
	@Id
	@Column
	@GeneratedValue
	private Long id; // 编号
	@ManyToOne()
	private BuildProject buildProject;
	@Column
	private String title;
	@Column(columnDefinition = "LongText")
	private String content;
	@Column(length = 1000)
	private String description;
	@Column
	private Boolean head = false;
	@Column
	private Boolean outSide = false;
	@ManyToOne()
	private NewsType newsType;
	@ManyToOne()
	private User author;
	@Column
	private Integer showCount = 0;
	@Column
	private String thumbName;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdated;
	@OneToMany()
	private List<NewsVisit> visits;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getHead() {
		return head;
	}
	public void setHead(Boolean head) {
		this.head = head;
	}
	public Boolean getOutSide() {
		return outSide;
	}
	public void setOutSide(Boolean outSide) {
		this.outSide = outSide;
	}
	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Integer getShowCount() {
		return showCount;
	}
	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}
	public String getThumbName() {
		return thumbName;
	}
	public void setThumbName(String thumbName) {
		this.thumbName = thumbName;
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
	public List<NewsVisit> getVisits() {
		return visits;
	}
	public void setVisits(List<NewsVisit> visits) {
		this.visits = visits;
	}
}
