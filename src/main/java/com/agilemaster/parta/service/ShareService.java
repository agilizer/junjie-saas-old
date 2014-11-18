package com.agilemaster.parta.service;

import java.util.Map;


public interface ShareService {
	
	void save(Object domain);
	void update(Object object);
	Map<String,Object> findById(Class<?>  clazz,int id);
	String cloudUrl();
	Long saveAutoGenId(Object domain);
}
