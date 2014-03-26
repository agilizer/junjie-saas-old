package com.agilemaster.cloud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table()
public class CompanyContract {
	
	@Id
	@Column
	@GeneratedValue
    private Long id;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	/**
	 * 延期服务备注
	 */
	@Column(columnDefinition = "LongText")
    private String memo;
    

}
