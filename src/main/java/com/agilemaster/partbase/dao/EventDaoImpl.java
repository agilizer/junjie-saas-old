package com.agilemaster.partbase.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.entity.EventProgress;
import com.agilemaster.partbase.entity.User;
import com.agilemaster.partbase.service.ShareService;
import com.agilemaster.partbase.util.BeanToMapUtil;
import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.db.client.JunjieJdbcOptions;
import com.junjie.commons.utils.JunjieCounter;

@Service
public class EventDaoImpl implements EventDao{

	@Autowired
	private ShareService shareService;
	@Autowired
	private JunjieJdbcOptions junjieJdbcOptions;
	@Autowired
	JunjieCounter junjieCounter;
	@Override
	public Event save(Event event) {
		Long id = shareService.saveAutoGenId(event);
		event.setId(id);
		// save Separate eventProgress
		if(event.getSeparateReport()){
			List<EventProgress> eventProgresses = new ArrayList<EventProgress>();
			short progressS = 0;
			for(User user:event.getParticipants()){
				EventProgress progress = new EventProgress();
				progress.setEventId(id);
				progress.setUser(user);
				progress.setVersion(1l);
				progress.setProgress(progressS);
				Long pId  = shareService.saveAutoGenId(progress);
				progress.setId(pId);
				eventProgresses.add(progress);
			}
			event.setProgresses(eventProgresses);
		}
		return event;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Event show(Long id) {
		Event event = shareService.findObjectById(Event.class, id);
		if(event!=null){
			Map<String,Object> queryParams = new HashMap<String,Object>();
			queryParams.put("userId", event.getAuthor().getId());
			Map<String,Object> queryResult = junjieJdbcOptions.queryForMap("select id,username,full_name from user where id=:userId", queryParams);
			User author  = BeanToMapUtil.convertMap(User.class, queryResult);
			event.setAuthor(author);
			queryParams.put("userId", event.getMasterUser().getId());
			queryResult = junjieJdbcOptions.queryForMap("select id,username,full_name from user where id=:userId", queryParams);
			User masterUser  = BeanToMapUtil.convertMap(User.class, queryResult);
			event.setMasterUser(masterUser);
			queryParams.clear();
			queryParams.put("eventId", event.getId());
			List<?> searchResult =junjieJdbcOptions.queryForList("SELECT u.id,u.username,full_name FROM EVENT_PARTICIPANTS e_p,event e,user  u " +
					" where e_p.event=:eventId  and e_p.PARTICIPANTS = u.id and e_p.event = e.id", queryParams) ; 
			List<User> participants = new ArrayList<User>();
			for(Object object:searchResult){
				participants.add(BeanToMapUtil.convertMap(User.class, (Map<String,Object>)object)); 
			}
			event.setParticipants(participants);
			if(event.getSeparateReport() == true){
				searchResult =junjieJdbcOptions.queryForList("SELECT p.* FROM EVENT_PROGRESSES e_p,event e,event_progress p where e.id=:eventId and e_p.EVENT= e.id e_p.PROGRESSES = p.id", queryParams) ; 
				List<EventProgress> progresses = new ArrayList<EventProgress>();
				for(Object object:searchResult){
					progresses.add(BeanToMapUtil.convertMap(EventProgress.class, (Map<String,Object>)object)); 
				}
				event.setProgresses(progresses);
			}
		}
		return event;
	}
	@Override
	public Event update(Event event) {
		del(event.getId());
		shareService.save(event);
		return null;
	}
	@Override
	public int del(Long id) {
		StringBuffer delSql = new StringBuffer();
		delSql.append("delete FROM EVENT_PARTICIPANTS where event=" +id +";");
		delSql.append("delete FROM EVENT_PROGRESSES where event=" +id +";");
		delSql.append("delete FROM EVENT_PROGRESS where event_id=" +id +";");  
		delSql.append("delete from event where id="+id+";");
		return junjieJdbcOptions.update(delSql.toString());
	}
	@Override
	public JdbcPage list(User user,Calendar start, Calendar end,int max,int offset) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("start", start);
		queryParams.put("userId", user.getId());
		queryParams.put("end", end);
		//select distinct e.* from EVENT e,EVENT_PARTICIPANTS e_p where e.AUTHOR  = 103 or ( e.id=e_p.event  and e_p.PARTICIPANTS=103 ) or e.MASTER_USER=103
		String countSql = "select count( distinct e.id) from EVENT e,EVENT_PARTICIPANTS e_p where e.START_DATE>:start and "
				+ " e.END_DATE<:end and"
				+ " (e.AUTHOR  =:userId or ( e.id=e_p.event  and e_p.PARTICIPANTS=:userId ) or e.MASTER_USER=:userId)";
		String querySql = "select distinct e.id,e.title,e.start_date,e.end_date from EVENT e,EVENT_PARTICIPANTS e_p where e.START_DATE>:start and "
				+ " e.END_DATE<:end  and"
				+ " (e.AUTHOR  =:userId or ( e.id=e_p.event  and e_p.PARTICIPANTS=:userId ) or e.MASTER_USER=:userId)";
		return junjieJdbcOptions.queryForList(querySql, countSql, queryParams, max, offset);
	}
	@Override
	public int updateProgress(Long eventId, int progress) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("eventId", eventId);
		queryParams.put("progress", progress);
		return junjieJdbcOptions.update("update event set progress=:progress where id=:eventId", queryParams);
	}
	@Override
	public int updateSeparateProgress(Long progressesId, int progress) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("progressesId", progressesId);
		queryParams.put("progress", progress);
		return junjieJdbcOptions.update("update event_progress set progress=:progress where id=:progressesId", queryParams);
	}

}
