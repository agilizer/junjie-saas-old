package com.agilemaster.cloud.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.cloud.dao.UserDao;
import com.agilemaster.cloud.repository.RoleRepository;
import com.agilemaster.cloud.service.UserService;

@Service
public class BootstrapServiceImpl implements BootstrapService{

	@Autowired
	UserDao userDao;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	public void init(){
	}
}
