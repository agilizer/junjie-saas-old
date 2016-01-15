package com.agilemaster.partbase.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.dao.ConfigDomainDao;
import com.agilemaster.partbase.entity.ConfigDomain;
import com.junjie.commons.db.client.DataSourceSelecter;

@Service
public class ConfigDomainServiceImpl implements ConfigDomainService{

	@Autowired
	ConfigDomainDao configDomainDao;
	@Autowired
	DataSourceSelecter dataSourceSelecter;
	private String genStoreKey(){
		return dataSourceSelecter.getCurrentDataSourceKey();
	}
	@Override
	public void update(ConfigDomain configDomain) {
		String storeKey = genStoreKey();
		configDomainDao.update(storeKey, configDomain);
	}

	@Override
	public Map<String, Object> list() {
		String storeKey = genStoreKey();
		return configDomainDao.list(storeKey);
	}

	@Override
	public List<ConfigDomain> listConfigDomain() {
		String storeKey = genStoreKey();
		return configDomainDao.listConfigDomain(storeKey);
	}

	@Override
	public int getConfigInt(String configName) {
		String storeKey = genStoreKey();
		return  configDomainDao.getConfigInt(storeKey, configName);
	}

	@Override
	public boolean getConfigBoolean(String configName) {
		String storeKey = genStoreKey();
		return  configDomainDao.getConfigBoolean(storeKey, configName);
	}

	@Override
	public String getConfigString(String configName) {
		String storeKey = genStoreKey();
		return  configDomainDao.getConfigString(storeKey, configName);
	}

}
