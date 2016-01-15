package com.junjie.commons.quartz;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public interface QuartzService {
	/**
	 * 添加job
	 * @param job
	 * @param trigger
	 */
	void addJob(JobDetail jobDetail,Trigger trigger);
	/**
	 * 
	 * @param job
	 * @param trigger
	 */
	void updateJob(JobDetail jobDetail,Trigger trigger);
	
	void deleteJob(String name,String group);

}
