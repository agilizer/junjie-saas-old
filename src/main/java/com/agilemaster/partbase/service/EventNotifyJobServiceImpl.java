package com.agilemaster.partbase.service;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.entity.EventNotify;
import com.agilemaster.partbase.entity.EventNotify.EventNotifyType;
import com.agilemaster.partbase.entity.User;
import com.agilemaster.partbase.util.BeanToMapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junjie.commons.db.JdbcConstants;
import com.junjie.commons.db.JunjieDbOptionBean;
import com.junjie.commons.db.client.DataSourceSelecter;
import com.junjie.commons.db.client.JunjieJdbcOptions;
import com.junjie.commons.sms.SmsService;
import com.junjie.commons.utils.DateUtil;
import com.junjie.commons.utils.JunjieConstants;
import com.junjie.commons.utils.JunjieStaticMethod;

@Service(value="eventNotifyJobService")
public class EventNotifyJobServiceImpl implements EventNotifyJobService, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7387110618718975108L;
	private static final Logger log = LoggerFactory
			.getLogger(EventServiceImpl.class);
	@Autowired
    SmsService	smsService;
	@Autowired
	ShareService shareService;
    @Autowired
	DataSourceSelecter dataSourceSelecter;
    @Autowired
    JunjieJdbcOptions junjieJdbcOptions;
    private static  Scheduler scheduler;
    private static final String JOB_NAME_START="notifyId-";
    private static final String JOB_GROUP="eventNotifyJob";
	
	@Override
	public void sendEventSms(Event event, String action,EventNotifyBean notifyBean) {
		String content = "";
        String template =  notifyBean.getSmsTemplate();
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_USERNAME.getValue(),event.getAuthor().getFullName());
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_START_DATE.getValue(),DateUtil.format(event.getStartDate(),"yyyy-MM-dd"));
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_END_DATE.getValue(),DateUtil.format(event.getEndDate(),"yyyy-MM-dd"));
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_TITLE.getValue(),JunjieStaticMethod.lenECString(event.getTitle(),12));
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_ACTION.getValue(),action);
        template = template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_URL.getValue(),notifyBean.getServerUrl()
                +SMS_URL_PATH+"/"+notifyBean.getDatasourceKey()+"/"+event.getId());
        for(User user:event.getParticipants()){
        	 content =  template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_RECEIVE_USERNAME.getValue(),user.getFullName());
 		     smsService.sendSMS(user.getUsername(),content);
        }
        User user = event.getMasterUser();
        if(user!=null){
        	 content =  template.replaceAll(JunjieConstants.smsTemplateVar.SMS_T_RECEIVE_USERNAME.getValue(),user.getFullName());
 		     smsService.sendSMS(user.getUsername(),content);
        }
	}
  public void deleteNotify(EventNotifyBean notifyBean){
	        if(notifyBean!=null){
	            deleteNotify(notifyBean.getDatasourceKey(),notifyBean.getEventId());
	        }else{
	            log.warn("notifyBean is null");
	        }
	    }
   public void deleteNotify(String dataSourceKey,Long eventNotifyId){
	        if(eventNotifyId!=null){
	            JobKey jobKey = new JobKey(JOB_NAME_START+dataSourceKey+"-"+eventNotifyId,JOB_GROUP);
	            try {
					scheduler.deleteJob(jobKey);
				} catch (SchedulerException e) {
					log.error("delete eventNotify error eventNotifyId {}",eventNotifyId,e);
				}
	        }else{
	            log.warn("eventNotify is null");
	        }
	 }
	
	public void addEventNotify(EventNotify eventNotify) {
		 if(eventNotify!=null){
	            JobDetail job = newJob(EventNotifyJob.class).withIdentity(JOB_NAME_START+eventNotify.getId(), JOB_GROUP) .build();
	            JSONObject notifyValueMap = JSON.parseObject(eventNotify.getNotifyValue());
	            Event event  =  eventNotify.getEvent();
	            Calendar now = Calendar.getInstance();
	            if(event.getEndDate().before(now)){
	                log.warn("event stop time is before now!!");
	                return;
	            }
	           if(eventNotify.getEventNotifyType().equals(EventNotifyType.EVERY_DAY)){
	        	   			int hour = notifyValueMap.getIntValue("hour");
	        	   			int minute =  notifyValueMap.getIntValue("minute");
		                    Date stopTime = new Date(eventNotify.getEvent().getEndDate().getTime().getTime()+ JunjieConstants.ONE_DATE_LONG);
		                    log.info(stopTime.toString());
		                    CronTrigger trigger = newTrigger()
		                            .withIdentity(JOB_NAME_START+eventNotify.getId(), JOB_GROUP)
		                            .startAt(new Date())
		                            .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(hour,minute))
		                            .endAt(stopTime)
		                            .build();
		                    log.info("add eventNotify(EVERY_DAY) job ok id:"+eventNotify.getId());
		                    try {
								scheduler.scheduleJob(job, trigger);
							} catch (SchedulerException e) {
								log.error("scheduler eventNotify job error eventNotify id :"+eventNotify.getId()+" event Id:{}",eventNotify.getEvent().getId(),e);
							}
	           }
	           if(eventNotify.getEventNotifyType().equals(EventNotifyType.ONCE)){
	        	   Date notifyDate = DateUtil.parse(notifyValueMap.getString("time"),"yyyy-MM-dd HH:mm:ss");
		                    if(notifyDate.getTime()>(new Date().getTime())){
		                        SimpleTrigger trigger = newTrigger()
		                                .withIdentity(JOB_NAME_START+eventNotify.getId(), JOB_GROUP)
		                                .startAt(notifyDate)
		                                .withSchedule(simpleSchedule()
		                                .withIntervalInSeconds(5)
		                                .withRepeatCount(1))
		                                .build();
		                        log.info("add eventNotify(ONCE) job ok id:"+eventNotify.getId());
		                        try {
									scheduler.scheduleJob(job, trigger);
								} catch (SchedulerException e) {
									log.error("scheduler eventNotify job error eventNotify id :"+eventNotify.getId()+" event Id:{}",eventNotify.getEvent().getId(),e);
								}
		                    }
	           }
	        }else{
	            log.warn("eventNotify is null");
	        }
	    }
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sendNotify(EventNotifyBean notifyBean) {
		if(notifyBean ==null){
			log.warn(" sendNotify EventNotifyBean is null");
			return;
		}    
	    JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		optionBean.setDbInfoKey(notifyBean.getDatasourceKey());
		Map<String, Object> headers = new HashMap<String, Object>();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("eventId", notifyBean.getEventId());
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		optionBean.setOption( JdbcConstants.QUERY_FOR_MAP);
		optionBean.setSql("select * from event where id=:eventId");
		optionBean.setParams(headers);
		Map<String,Object> queryResult = (Map<String, Object>) junjieJdbcOptions.execute(optionBean);
		Event event = BeanToMapUtil.convertMap(Event.class, queryResult);
		if(null!=event){
			optionBean.setOption( JdbcConstants.QUERY_FOR_LIST_ALL);
			optionBean.setSql("SELECT u.username,full_name FROM EVENT_PARTICIPANTS e_p,event e,user  u where e_p.event=:eventId  and e_p.PARTICIPANTS = u.id and e_p.event = e.id");
			Object searchResult =junjieJdbcOptions.execute(optionBean) ; 
			List<User> participants = new ArrayList<User>();
			if(searchResult instanceof LinkedCaseInsensitiveMap ){
				participants.add(BeanToMapUtil.convertMap(User.class, (Map<String,Object>)searchResult)); 
			}else{
				List list = (List) searchResult;
				for(Object object:list){
					participants.add(BeanToMapUtil.convertMap(User.class, (Map<String,Object>)object)); 
				}
			}
			event.setParticipants(participants);
			queryParams.clear();
			optionBean.setOption( JdbcConstants.QUERY_FOR_MAP);
			queryParams.put("userId", event.getAuthor().getId());
			optionBean.setSql("select username,full_name from user where id=:userId");
			queryResult = (Map<String, Object>) junjieJdbcOptions.execute(optionBean);
			User author = BeanToMapUtil.convertMap(User.class, queryResult);
			event.setAuthor(author);
			queryParams.put("userId", event.getMasterUser().getId());
			optionBean.setSql("select username,full_name from user where id=:userId");
			queryResult = (Map<String, Object>) junjieJdbcOptions.execute(optionBean);
			User masterUser  =  BeanToMapUtil.convertMap(User.class, queryResult);
			event.setMasterUser(masterUser);
			if(event!=null&&event.getProgress()<100){
            	Calendar calendar = Calendar.getInstance();
                int hour =  calendar.get(Calendar.HOUR_OF_DAY);
                if(hour>notifyBean.getWorkStartHour()&&hour<notifyBean.getWorkStopHour()){
                    String action =     "提醒";
                    if(calendar.after(event.getEndDate())){
                        action = "超时";
                        deleteNotify(notifyBean);
                    }
                    sendEventSms(event,action,notifyBean);
                }
            }
            if(event!=null&&event.getProgress()==100){
                deleteNotify(notifyBean);
            }
		}
	}		
}
