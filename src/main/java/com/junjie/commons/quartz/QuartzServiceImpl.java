package com.junjie.commons.quartz;

import java.io.File;
import java.io.FileNotFoundException;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class QuartzServiceImpl implements QuartzService{
	private static final Logger log = LoggerFactory.getLogger(QuartzServiceImpl.class);
	private static Scheduler scheduler;

	public void init() {
		SchedulerFactory sf = null ;
		try {
			File quartzConfigFile = ResourceUtils.getFile("classpath:quartz.properties");
			String filePath = quartzConfigFile.getAbsolutePath();
			log.info("init quartz form file {}", filePath);
			sf = new StdSchedulerFactory(filePath);
		} catch (SchedulerException e) {
			log.error("start evetnNotifyJobService scheduler", e);
		} catch (FileNotFoundException e) {
			log.warn("quartz file not found user default-------------->",e);
			sf = new StdSchedulerFactory();
		}
		try {
			scheduler = sf.getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			log.error("start  scheduler error", e);
		}
	}
	public void destroy(){
		try {
			scheduler.shutdown(false);
		} catch (SchedulerException e) {
			log.error("stop  scheduler error", e);
		}
	}

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
}
