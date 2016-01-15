package com.agilemaster.partbase.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.agilemaster.partbase.entity.DocumentDir;

public interface DocumentDirService {
	
	DocumentDir  create(DocumentDir documentDir,HttpServletRequest request);
}
