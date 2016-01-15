package com.agilemaster.partbase.service;

import java.util.List;

import com.agilemaster.partbase.entity.BuildProject;

public interface BuildProjectService {
	List list();
	BuildProject findById(Long id);
}
