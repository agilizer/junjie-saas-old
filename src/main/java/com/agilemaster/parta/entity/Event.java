package com.agilemaster.parta.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Event A domain class describes the data object and it's mapping to the
 * database
 */
@Entity
@Table()
public class Event   implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1968896896132149863L;
	@Id
	@Column
	@GeneratedValue
	private Long id; // 编号
	/* Default (injected) attributes of GORM */
	// Long id
	// Long version
	@ManyToOne
	private BuildProject buildProject;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private boolean isPrivate = false;
	@Column
	private boolean isMilestone = false;
	// separateReport = true ,then progresses is not null
	// separateReport = false , progresses is null participants is not null
	@Column
	private boolean separateReport = false;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar finishDate;
	@ManyToOne
	private User author;
	@ManyToOne
	private User masterUser;
	@ManyToOne
	private EventType eventType;
	/**
	 * 1,2,3,4
	 */
	@Column
	private short eventLevel = 2;
	@Column
	private String userComment;
	@Column
	private short progress = 0;
	@Column
	private boolean delay = false;
	@Column
	private short delayLevel = 0;
	/* Automatic timestamping of GORM */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdated;
	@ManyToMany()
	private List<User> participants;
	@ManyToMany()
	private List<FileInfo> fileInfos;
	@ManyToMany()
	private List<EventProgress> progresses;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public boolean isMilestone() {
		return isMilestone;
	}
	public void setMilestone(boolean isMilestone) {
		this.isMilestone = isMilestone;
	}
	public boolean isSeparateReport() {
		return separateReport;
	}
	public void setSeparateReport(boolean separateReport) {
		this.separateReport = separateReport;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public Calendar getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Calendar finishDate) {
		this.finishDate = finishDate;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public User getMasterUser() {
		return masterUser;
	}
	public void setMasterUser(User masterUser) {
		this.masterUser = masterUser;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public short getEventLevel() {
		return eventLevel;
	}
	public void setEventLevel(short eventLevel) {
		this.eventLevel = eventLevel;
	}
	public String getUserComment() {
		return userComment;
	}
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
	public short getProgress() {
		return progress;
	}
	public void setProgress(short progress) {
		this.progress = progress;
	}
	public boolean isDelay() {
		return delay;
	}
	public void setDelay(boolean delay) {
		this.delay = delay;
	}
	public short getDelayLevel() {
		return delayLevel;
	}
	public void setDelayLevel(short delayLevel) {
		this.delayLevel = delayLevel;
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
	public List<User> getParticipants() {
		return participants;
	}
	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}
	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}
	public List<EventProgress> getProgresses() {
		return progresses;
	}
	public void setProgresses(List<EventProgress> progresses) {
		this.progresses = progresses;
	}

	
}
