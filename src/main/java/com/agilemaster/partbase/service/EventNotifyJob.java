package com.agilemaster.partbase.service;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.junjie.commons.sms.SmsService;
import com.junjie.commons.utils.JunjieConstants;

public class EventNotifyJob implements Job {

	private static Logger log = LoggerFactory.getLogger(EventNotifyJob.class);

	/**
	 * <p>
	 * Empty constructor for job initilization
	 * </p>
	 * <p>
	 * Quartz requires a public empty constructor so that the scheduler can
	 * instantiate the class whenever it needs.
	 * </p>
	 */
	public EventNotifyJob() {
	}

	/**
	 * <p>
	 * Called by the <code>{@link org.quartz.Scheduler}</code> when a
	 * <code>{@link org.quartz.Trigger}</code> fires that is associated with the
	 * <code>Job</code>.
	 * </p>
	 * 
	 * @throws JobExecutionException
	 *             if there is an exception while executing the job.
	 */
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// 获取JobExecutionContext中的service对象
		SchedulerContext schCtx;
		JobDetail jobDetail = context.getJobDetail();
		JobKey jobKey = jobDetail.getKey();
		try {
			schCtx = context.getScheduler().getContext();
			// 获取Spring中的上下文
			ApplicationContext appCtx = (ApplicationContext) schCtx
					.get("applicationContext");
			EventNotifyJobService eventNotifyJobService = (EventNotifyJobService) appCtx.getBean("eventNotifyJobService");
			log.info("execute job name:" + jobKey.getName() + " group:"+ jobKey.getGroup());
			JobDataMap data = jobDetail.getJobDataMap();
			EventNotifyBean notifyBean = (EventNotifyBean) data.get(JunjieConstants.EVENT_NOTIFY_BEAN);
			eventNotifyJobService.sendNotify(notifyBean);
		} catch (SchedulerException e) {
			log.error(" jobKey execute error {}" ,jobKey,e);
		}
	}

}
