package com.junjie.commons.test.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.junjie.commons.sms.SmsService;
import com.junjie.commons.utils.JunjieConstants;

public class EventNotifyTestJob implements Job {

	private static Logger log = LoggerFactory
			.getLogger(EventNotifyTestJob.class);

	/**
	 * <p>
	 * Empty constructor for job initilization
	 * </p>
	 * <p>
	 * Quartz requires a public empty constructor so that the scheduler can
	 * instantiate the class whenever it needs.
	 * </p>
	 */
	public EventNotifyTestJob() {
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
		JobDetail jobDetail = context.getJobDetail();
		try {
			// 获取JobExecutionContext中的service对象
			SchedulerContext schCtx = context.getScheduler().getContext();
			// 获取Spring中的上下文
			ApplicationContext appCtx = (ApplicationContext) schCtx.get("applicationContext");
			SmsService smsService = (SmsService) appCtx.getBean("smsService");
			JobKey jobKey = jobDetail.getKey();
			JobDataMap data = jobDetail.getJobDataMap();
			EventNotifyTestBean notifyBean = (EventNotifyTestBean) data
					.get(JunjieConstants.EVENT_NOTIFY_BEAN);
			String content = notifyBean.getContent();
			String phoneNumber = "";
			String username = "";
			String tempContent = "";
			for (int i = 0; i < notifyBean.getUsernames().length; i++) {
				username = notifyBean.getUsernames()[i];
				phoneNumber = notifyBean.getMobileNumbers()[i];
				tempContent = content.replaceAll(
						JunjieConstants.smsTemplateVar.SMS_T_RECEIVE_USERNAME
								.getValue(), username);
				smsService.sendSMS(phoneNumber, tempContent);
			}
		} catch (SchedulerException e1) {
			// TODO 尚未处理异常
			e1.printStackTrace();
		}

	}

}
