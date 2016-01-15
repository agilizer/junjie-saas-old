package com.agilemaster.partbase.service;

import java.util.List;
import java.util.Map;

import com.agilemaster.partbase.entity.ConfigDomain;

public interface ConfigDomainService {
	void update(ConfigDomain configDomain);
	Map<String, Object> list();
	List<ConfigDomain> listConfigDomain();
	int getConfigInt(String configName);
	boolean getConfigBoolean(String configName);
	String getConfigString(String configName);
}
