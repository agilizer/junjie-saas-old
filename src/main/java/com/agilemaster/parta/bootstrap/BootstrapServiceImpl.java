package com.agilemaster.parta.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.dao.UserDao;
import com.agilemaster.parta.repository.RoleRepository;
import com.agilemaster.parta.service.UserService;

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
