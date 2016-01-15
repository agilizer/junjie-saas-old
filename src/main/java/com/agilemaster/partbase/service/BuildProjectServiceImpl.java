package com.agilemaster.partbase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.dao.BuildProjectDao;
import com.agilemaster.partbase.entity.BuildProject;

@Service
public class BuildProjectServiceImpl implements BuildProjectService{

	@Autowired
	BuildProjectDao buildPorejctDao;
	@Override
	public List list() {
		return buildPorejctDao.list();
	}

	@Override
	public BuildProject findById(Long id) {
		return buildPorejctDao.findById(id);
	}

}
