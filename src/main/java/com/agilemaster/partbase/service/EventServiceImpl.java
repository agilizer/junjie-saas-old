package com.agilemaster.partbase.service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.dao.BuildProjectDao;
import com.agilemaster.partbase.dao.EventDao;
import com.agilemaster.partbase.entity.BuildProject;
import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.entity.EventNotify.EventNotifyType;
import com.agilemaster.partbase.entity.EventType;
import com.agilemaster.partbase.entity.User;
import com.alibaba.fastjson.JSON;
import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.db.client.DataSourceSelecter;
import com.junjie.commons.quartz.QuartzService;
import com.junjie.commons.utils.DateUtil;
import com.junjie.commons.utils.JunjieConstants;
import com.junjie.commons.utils.JunjieStaticMethod;

@Service
public class EventServiceImpl implements EventService{
	private static final Logger log = LoggerFactory
			.getLogger(EventServiceImpl.class);
	@Autowired
	UserService userService;
	@Autowired
	BuildProjectDao buildProjectDao;
	@Autowired
	EventDao eventDao;
	@Autowired
	EventNotifyJobService eventNotifyJobService;
	@Autowired
	DataSourceSelecter dataSourceSelecter;
	@Autowired
	ConfigDomainService configDomainService;
	@Autowired
	QuartzService quartzService;
	private static final String JOB_NAME_START="notifyId-";
    private static final String JOB_GROUP="eventNotifyJob";
	@Override
	public Map<String,Object>  create(Event event, HttpServletRequest request) {
		Map<String,Object> createResult = JunjieStaticMethod.genResult();
		User author = userService.currentUser();
		if(author!=null){
			String buildProjectIdStr = request.getParameter("buildProject.id");
			if(buildProjectIdStr!=null){
				BuildProject buildProject = buildProjectDao.findById(Long.parseLong(buildProjectIdStr));
				if(buildProject!=null){
					genProperties(event,request);
					event.setAuthor(author);
					Calendar now = Calendar.getInstance();
					event.setDateCreated(now);
					event  = eventDao.save(event);
					createResult.put(JunjieConstants.DATA, event);
					createResult.put(JunjieConstants.SUCCESS, true);
					boolean sendSms = JunjieStaticMethod.genBooleanValue(request, "sendSms");
					EventNotifyBean notifyBean =  genNotifyBean(event);
					if(sendSms){
						eventNotifyJobService.sendEventSms(event, "创建",notifyBean);
					}
					boolean isNotify = JunjieStaticMethod.genBooleanValue(request, "isNotify");
					if(isNotify){
						notify(event,request,notifyBean);
					}
				}else{
					createResult.put(JunjieConstants.ERROR_CODE, JunjieConstants.NOT_FOUND);
					createResult.put(JunjieConstants.MSG, "buildProject not found!");
				}
			}else{
				createResult.put(JunjieConstants.ERROR_CODE, JunjieConstants.NOT_FOUND);
				createResult.put(JunjieConstants.MSG, "buildProject.id is null!");
			}
		}else{
			createResult.put(JunjieConstants.ERROR_CODE, JunjieConstants.NOT_FOUND);
			createResult.put(JunjieConstants.MSG, "masterUser not found!");
		}
		return createResult;
	}
	@SuppressWarnings("unused")
	private String genNotifyValueJson(HttpServletRequest request,EventNotifyType notifyType){
	        Map<String,String> notifyValue =new HashMap<String, String>();
	        if(notifyType.equals(EventNotifyType.EVERY_DAY)){
	        	  notifyValue.put("hour",request.getParameter("hour"));
	              notifyValue.put("minute",request.getParameter("minute"));
	        } else if(notifyType.equals(EventNotifyType.ONCE)){
	        	notifyValue.put("time",request.getParameter("notifyTime"));
	        }else if(notifyType.equals(EventNotifyType.EVERY_WEEK)){
	        	 notifyValue.put("hour",request.getParameter("hour"));
	             notifyValue.put("minute",request.getParameter("minute"));
	             notifyValue.put("week",request.getParameter("week"));
	        }
	        return JSON.toJSONString(notifyValue);
	    }
	private void notify(Event event,HttpServletRequest request,EventNotifyBean notifyBean){
		  String jobId = notifyBean.genJobId();
		  JobDetail jobDetail = newJob(EventNotifyJob.class).withIdentity(JOB_NAME_START+jobId, JOB_GROUP) .build();
		  JobDataMap dataMap =  jobDetail.getJobDataMap();
		  dataMap.put(JunjieConstants.EVENT_NOTIFY_BEAN, notifyBean);
		  EventNotifyType notifyType = EventNotifyType.valueOf(request.getParameter("eventNotifyType"));
		  Calendar now = Calendar.getInstance();
		  if(event.getEndDate().before(now)){
              log.warn("event stop time is before now!!");
              return;
          }
         if(notifyType.equals(EventNotifyType.EVERY_DAY)){
      	   			int hour = Integer.parseInt(request.getParameter("hour"));
      	   			int minute =  Integer.parseInt(request.getParameter("minute"));
	                Date stopTime = new Date(event.getEndDate().getTime().getTime()+ JunjieConstants.ONE_DATE_LONG);
                    log.info(stopTime.toString());
                    CronTrigger trigger = newTrigger()
	                            .withIdentity(JOB_NAME_START+jobId, JOB_GROUP)
	                            .startAt(new Date())
	                            .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(hour,minute))
	                            .endAt(stopTime)
	                            .build();
	                    log.info("add eventNotify(EVERY_DAY) job ok id:"+jobId);
	                    quartzService.addJob(jobDetail, trigger);
         }
         if(notifyType.equals(EventNotifyType.ONCE)){
      	   Date notifyDate = DateUtil.parse(request.getParameter("notifyTime"),"yyyy-MM-dd HH:mm:ss");
            if(notifyDate.getTime()>(new Date().getTime())){
                SimpleTrigger trigger = newTrigger()
                        .withIdentity(JOB_NAME_START+jobId, JOB_GROUP)
                        .startAt(notifyDate)
                        .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .withRepeatCount(1))
                        .build();
                log.info("add eventNotify(ONCE) job ok jobId:"+jobId);
                quartzService.addJob(jobDetail, trigger);
            }
         }
	}
	@Override
	public Map<String, Object> show(Long id) {
		Map<String,Object> result = JunjieStaticMethod.genResult();
		Event event  = eventDao.show(id);
		if(event!=null){
			result.put(JunjieConstants.SUCCESS, true);
			result.put(JunjieConstants.DATA, event);
		}else{
			result.put(JunjieConstants.ERROR_CODE, JunjieConstants.NOT_FOUND);
		}
		return result;
	}
	private void genProperties(Event event,HttpServletRequest request){
		User masterUser  = userService.findByUserId(Long.parseLong(request.getParameter("masterUser.id")));
		String[] participantsIdArray =request.getParameterValues("participants");
		List<User> participants = new ArrayList<User>();
		for(String temp:participantsIdArray){
			User user  = userService.findByUserId(Long.parseLong(temp));
			if(null!=user){
				participants.add(user);
			}
			log.info("participants-->{}",temp);
		}
		Long eventTypeId = JunjieStaticMethod.genLongValue(request, "eventType.id");
		if(eventTypeId!=null){
			EventType eventType = new EventType();
			eventType.setId(eventTypeId);
			event.setEventType(eventType);
		}
		event.setTitle(request.getParameter("title"));
		event.setDescription(request.getParameter("description"));
		event.setParticipants(participants);
		event.setMasterUser(masterUser);
		Calendar now = Calendar.getInstance();
		event.setLastUpdated(now);
		event.setStartDate(DateUtil.parseForCalendar(request.getParameter("startDate"), "yyyy-MM-dd hh:mm:ss"));
		event.setEndDate(DateUtil.parseForCalendar(request.getParameter("endDate"), "yyyy-MM-dd hh:mm:ss"));
		boolean separateReport = JunjieStaticMethod.genBooleanValue(request, "separateReport");
		event.setSeparateReport(separateReport);
		boolean isPrivate = JunjieStaticMethod.genBooleanValue(request, "isPrivate");
		event.setIsPrivate(isPrivate);
		log.info("participants length-->{}",participantsIdArray.length);
	}
	private EventNotifyBean genNotifyBean(Event event){
		EventNotifyBean notifyBean = new EventNotifyBean();
		notifyBean.setDatasourceKey(dataSourceSelecter.getCurrentDataSourceKey());
		notifyBean.setServerUrl(configDomainService.getConfigString(JunjieConstants.SERVER_URL));
		notifyBean.setSmsTemplate(configDomainService.getConfigString(JunjieConstants.SMS_TEMPLATE_EVENT));
		notifyBean.setWorkStartHour(configDomainService.getConfigInt(JunjieConstants.OFFICE_START_TIME));
		notifyBean.setWorkStopHour(configDomainService.getConfigInt(JunjieConstants.OFFICE_END_TIME));
		notifyBean.setEventId(event.getId());
		return notifyBean;
		
	}
	@Override
	public Map<String, Object> edit(Long id, HttpServletRequest request) {
		Map<String,Object> result = JunjieStaticMethod.genResult();
		Event event  = eventDao.show(id);
		if(event!=null){
			genProperties(event,request);
			eventDao.update(event);
			boolean sendSms = JunjieStaticMethod.genBooleanValue(request, "sendSms");
			EventNotifyBean notifyBean =  genNotifyBean(event);
			if(sendSms){
				eventNotifyJobService.sendEventSms(event, "更新",notifyBean);
			}
			boolean isNotify = JunjieStaticMethod.genBooleanValue(request, "isNotify");
			if(isNotify){
				String jobId = notifyBean.genJobId();
				quartzService.deleteJob(JOB_NAME_START+jobId, JOB_GROUP);
				notify(event,request,notifyBean);
			}
			result.put(JunjieConstants.SUCCESS, true);
			result.put(JunjieConstants.DATA, event);
		}else{
			result.put(JunjieConstants.ERROR_CODE, JunjieConstants.NOT_FOUND);
		}
		return null;
	}
	@Override
	public Map<String, Object> del(Long id) {
		Map<String,Object> result = JunjieStaticMethod.genResult();
	    eventDao.del(id);
	    result.put(JunjieConstants.SUCCESS, true);
		return result;
	}
	@Override
	public JdbcPage list(long startTime, Long endTime,int max,int offset) {
		User user = userService.currentUser();
		Calendar start = Calendar.getInstance();
		start.setTimeInMillis(startTime);
		Calendar end = Calendar.getInstance();
		end.setTimeInMillis(endTime);
		return eventDao.list(user, start, end,max,offset);
	}

}
