package com.agilemaster.partbase.dao;

import java.util.List;
import java.util.Map;

import com.agilemaster.partbase.entity.ConfigDomain;

/**
 * confingDomain store in redis
 * @author abel.lee
 *2014年11月20日 下午2:51:35
 */
public interface ConfigDomainDao {
	/**
	 * base configDomain
	 */
	String STORE_PREFIX = "bcd";
	void save(String storeKey,ConfigDomain configDomain);
	void update(String storeKey,ConfigDomain configDomain);
	Map<String,Object> list(String storeKey);
	List<ConfigDomain> listConfigDomain(String storeKey);
     int  getConfigInt(String storeKey,String configName);
     boolean  getConfigBoolean(String storeKey,String configName);
     String  getConfigString(String storeKey,String configName);
}
