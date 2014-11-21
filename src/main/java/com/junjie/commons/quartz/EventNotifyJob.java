package com.junjie.commons.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.junjie.commons.sms.SmsService;
import com.junjie.commons.utils.JunjieConstants;
public class EventNotifyJob implements Job{
	
	private static Logger log = LoggerFactory.getLogger(EventNotifyJob.class);
    /**
     * <p>
     * Empty constructor for job initilization
     * </p>
     * <p>
     * Quartz requires a public empty constructor so that the
     * scheduler can instantiate the class whenever it needs.
     * </p>
     */
    public EventNotifyJob() {
    }

    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     * </p>
     * @throws JobExecutionException
     *             if there is an exception while executing the job.
     */
    public void execute(JobExecutionContext context)
    throws JobExecutionException {
    	JobDetail jobDetail = context.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        JobDataMap data = jobDetail.getJobDataMap();
        EventNotifyBean notifyBean = (EventNotifyBean)data.get(JunjieConstants.EVENT_NOTIFY_BEAN);
        SmsService smsService = (SmsService)data.get(JunjieConstants.EVENT_SMS_SERVICE);
        String content = notifyBean.getContent();
        String phoneNumber = "";
        String username = "";
        String tempContent = "";
        for(int i=0;i<notifyBean.getUsername().size();i++){
        	phoneNumber = notifyBean.getMobileNumbers().get(i);
        	tempContent = content.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_RECEIVE_USERNAME.getValue(),username);
        	smsService.sendSMS(phoneNumber, tempContent);
        }
    }

}
