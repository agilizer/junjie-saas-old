package com.agilemaster.parta.bootstrap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.dao.UserDao;
import com.agilemaster.parta.entity.BuildProject;
import com.agilemaster.parta.entity.EventType;
import com.agilemaster.parta.entity.Resource;
import com.agilemaster.parta.entity.Role;
import com.agilemaster.parta.repository.RoleRepository;
import com.agilemaster.parta.service.UserService;
import com.junjie.commons.utils.JunjieCounter;

@Service
public class BootstrapServiceImpl implements BootstrapService{

	@Autowired
	UserDao userDao;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	JunjieCounter junjieCounter;
	@PostConstruct
	public void init(){
		initDbCount();
	}
	/**
	 * 初始化数据库表id,100以下做为系统自用.
	 */
	public void initDbCount(){
		incCount(Role.ID_NAME);
		incCount(EventType.ID_NAME);
		incCount(Resource.ID_NAME);
		incCount(BuildProject.ID_NAME);
		incCount(BuildProject.ID_NAME);
	}
	private void incCount(String name){
		if(junjieCounter.genUniqueLong(name)<100){
			junjieCounter.genUniqueLong(name, 100);
		}
	}
}
