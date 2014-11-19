package com.agilemaster.parta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.dao.BuildProjectDao;
import com.agilemaster.parta.entity.BuildProject;

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
