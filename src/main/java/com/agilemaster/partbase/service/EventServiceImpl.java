package com.agilemaster.partbase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.dao.BuildProjectDao;
import com.agilemaster.partbase.dao.EventDao;
import com.agilemaster.partbase.entity.BuildProject;
import com.agilemaster.partbase.entity.Event;
import com.agilemaster.partbase.entity.User;
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
	@Override
	public Map<String,Object>  create(Event event, HttpServletRequest request) {
		Map<String,Object> createResult = JunjieStaticMethod.genResult();
		User masterUser  = userService.findByUserId(Long.parseLong(request.getParameter("masterUser.id")));
		User author = userService.currentUser();
		if(masterUser!=null){
			String buildProjectIdStr = request.getParameter("buildProject.id");
			if(buildProjectIdStr!=null){
				BuildProject buildProject = buildProjectDao.findById(Long.parseLong(buildProjectIdStr));
				if(buildProject!=null){
					event.setBuildProject(buildProject);
					String[] participantsIdArray =request.getParameterValues("participants");
					List<User> participants = new ArrayList<User>();
					for(String temp:participantsIdArray){
						User user  = userService.findByUserId(Long.parseLong(temp));
						if(null!=user){
							participants.add(user);
						}
						log.info("participants-->{}",temp);
					}
					event.setParticipants(participants);
					event.setMasterUser(masterUser);
					event.setAuthor(author);
					event  = eventDao.save(event);
					createResult.put(JunjieConstants.DATA, event);
					boolean sendSms = JunjieStaticMethod.genBooleanValue(request, "sendSms");
					if(sendSms){
						sendSms(event);
					}
					boolean isNotify = JunjieStaticMethod.genBooleanValue(request, "isNotify");
					if(isNotify){
						notify(event,request);
					}
					log.info("participants length-->{}",participantsIdArray.length);
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
	private void sendSms(Event event){
		
	}
	private void notify(Event event,HttpServletRequest request){
		
	}

}
