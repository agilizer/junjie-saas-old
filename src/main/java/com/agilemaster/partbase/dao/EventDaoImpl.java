package com.agilemaster.partbase.dao;

import java.util.ArrayList;
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

}
