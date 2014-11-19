package com.agilemaster.parta.service;

import java.util.List;

import com.agilemaster.parta.entity.BuildProject;

public interface BuildProjectService {
	List list();
	BuildProject findById(Long id);
}
