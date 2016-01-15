package com.junjie.commons.test.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junjie.commons.quartz.QuartzService;
import com.junjie.commons.sms.SmsService;
import com.junjie.commons.utils.JunjieConstants;

@ContextConfiguration(locations = "classpath:spring-test-quartz.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class QuartzTest {
	@Autowired
	QuartzService quartzService;
	@Autowired
	SmsService smsService;
	@Test
	public void testSend(){
		try {
			EventNotifyTestBean notifyBean = new EventNotifyTestBean();
			notifyBean.setContent("#接收用户#,您好!郭总创建任务:明天早上8点30开会");
			String[] mobileNumers = new String[]{"18190910296"};
			String[] usernames = new String[]{"李栋梁"};
			notifyBean.setEventId("1-12");
			notifyBean.setMobileNumbers(mobileNumers);
			notifyBean.setUsernames(usernames);
			 SimpleTrigger trigger = newTrigger()
                     .withIdentity("1", "haha")
                     .startNow()
                     .withSchedule(simpleSchedule()
                     .withIntervalInSeconds(5)
                     .withRepeatCount(2))
                     .build();
			 JobDetail jobDetail = newJob(EventNotifyTestJob.class).withIdentity("1-12", "132") .build();
			 jobDetail.getJobDataMap().put(JunjieConstants.EVENT_NOTIFY_BEAN, notifyBean);
			 quartzService.addJob(jobDetail, trigger);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
