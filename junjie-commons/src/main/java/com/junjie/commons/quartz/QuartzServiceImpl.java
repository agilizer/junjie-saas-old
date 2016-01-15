package com.junjie.commons.quartz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.ResourceUtils;

public class QuartzServiceImpl implements QuartzService,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2500331552496829617L;
	private static final Logger log = LoggerFactory.getLogger(QuartzServiceImpl.class);
	private  Scheduler scheduler;


	@Override
	public void addJob(JobDetail jobDetail, Trigger trigger) {
		try {
			scheduler.scheduleJob(jobDetail, trigger)	;
		} catch (SchedulerException e) {
			log.error("",e);
		}	
	}

	@Override
	public void updateJob(JobDetail jobDetail, Trigger trigger) {
		try {
			scheduler.deleteJob(jobDetail.getKey());
			addJob(jobDetail,trigger);
		} catch (SchedulerException e) {
			log.error("",e);
		}
		
	}

	@Override
	public void deleteJob(String name, String group) {
		try {
			scheduler.deleteJob(new JobKey(name,group));
		} catch (SchedulerException e) {
			log.error("",e);
		}
	}
	public  Scheduler getScheduler() {
		return this.scheduler;
	}
	public  void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	
	
}
