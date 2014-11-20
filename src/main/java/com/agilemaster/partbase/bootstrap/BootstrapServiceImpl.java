package com.agilemaster.partbase.bootstrap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.dao.UserDao;
import com.agilemaster.partbase.entity.BuildProject;
import com.agilemaster.partbase.entity.EventType;
import com.agilemaster.partbase.entity.Resource;
import com.agilemaster.partbase.entity.Role;
import com.agilemaster.partbase.repository.RoleRepository;
import com.agilemaster.partbase.service.UserService;
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
