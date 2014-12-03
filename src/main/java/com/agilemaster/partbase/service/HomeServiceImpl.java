package com.agilemaster.partbase.service;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.junjie.commons.utils.JunjieConstants;
import com.junjie.commons.utils.JunjieStaticMethod;

@Service
public class HomeServiceImpl implements HomeService{
	private static final Logger log = LoggerFactory
			.getLogger(HomeServiceImpl.class);
	@Autowired
	ShareService shareService;
	@Autowired
	private UserService userService;
	@Override
	public Map<String, Object> index() {
		log.info("-------------------------->@Vaule test cloudUrl {}",shareService.cloudUrl());
		Map<String,Object> result = JunjieStaticMethod.genResult();
		Subject subject = SecurityUtils.getSubject();
		Object principal = subject.getPrincipal();
		result.put(JunjieConstants.CLOUD_URL, shareService.cloudUrl());
		if(principal==null){
			log.info("--->principal   is null  session.id:"+subject.getSession().getId() );
		}else{
			String username = (String) principal;
			Session session  = subject.getSession();
			log.info("--->principal   is not null  session.id:"+subject.getSession().getId() );
	    	log.info("dataSourceKey-->{}",session.getAttribute(JunjieConstants.DATA_SOURCE_KEY));
	    	result.put(JunjieConstants.SUCCESS, true);
	    	result.put(JunjieConstants.DATA, userService.findByUsernameMap(username));
		}
		return result;
	}

}
