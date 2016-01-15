package com.agilemaster.partbase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.entity.ConfigDomain;

@Service
public class ConfigDomainDaoImpl implements ConfigDomainDao{
	private static final Logger log = LoggerFactory
			.getLogger(ConfigDomainDaoImpl.class);
	@Autowired
	RedisTemplate<String, ?> redisTemplate;
	
	private String genRedisKey(String storeKey){
		return STORE_PREFIX+"_"+storeKey;
	}
	@Override
	public void save(String storeKey, ConfigDomain configDomain) {
		String redisKey = genRedisKey(storeKey);
		redisTemplate.boundHashOps(redisKey).put(configDomain.getMapName(), configDomain);
	}

	@Override
	public void update(String storeKey, ConfigDomain configDomain) {
		save(storeKey,configDomain);		
	}

	@Override
	public Map<String, Object> list(String storeKey) {
		Map<String,Object> configMap = new HashMap<String, Object>();
		String redisKey = genRedisKey(storeKey);
		List<Object> listConfig = redisTemplate.boundHashOps(redisKey).values();
		for(Object obj:listConfig){
			addToConfigMap(configMap,(ConfigDomain) obj);
		}
		return configMap;
	}
	private static Map<String,Object> addToConfigMap(Map<String,Object> configMap, ConfigDomain configDomain) {
        if (configDomain.getValueType() == ConfigDomain.ValueType.Integer) {
            configMap.put(configDomain.getMapName(), Integer.parseInt(configDomain.getMapValue()));
        } else if(configDomain.getValueType() == ConfigDomain.ValueType.Boolean){
             if(configDomain.getMapValue()=="true"){
                 configMap.put(configDomain.getMapName(), true);
             }else{
                 configMap.put(configDomain.getMapName(), false);
             }
        } else {
            configMap.put(configDomain.getMapName(), configDomain.getMapValue());
        }
        return configMap;
    }
	private ConfigDomain getConfig(String storeKey, String configName){
		String redisKey = genRedisKey(storeKey);
		Object config = redisTemplate.boundHashOps(redisKey).get(configName);
		if(config!=null){
			return (ConfigDomain) config;
		}else{
			log.warn("warn storeKey :"+storeKey+" configName "+ configName +" is null");
			return null;
		}
	}
	@Override
	public int getConfigInt(String storeKey, String configName) {
		ConfigDomain configDomain = getConfig(storeKey,configName);
		if(configDomain!=null){
			return Integer.parseInt(configDomain.getMapValue());
		}else{
			
			return 0;
		}
	}
	@Override
	public boolean getConfigBoolean(String storeKey, String configName) {
		ConfigDomain configDomain = getConfig(storeKey,configName);
		if(configDomain!=null){
			return Boolean.parseBoolean(configDomain.getMapValue());
		}else{
			return false;
		}
	}
	@Override
	public String getConfigString(String storeKey, String configName) {
		ConfigDomain configDomain = getConfig(storeKey,configName);
		if(configDomain!=null){
			return configDomain.getMapValue();
		}else{
			return null;
		}
	}
	@Override
	public List<ConfigDomain> listConfigDomain(String storeKey) {
		String redisKey = genRedisKey(storeKey);
		List<Object> listConfig = redisTemplate.boundHashOps(redisKey).values();
		List<ConfigDomain> result = new ArrayList<ConfigDomain>();
		for(Object obj:listConfig){
			result.add((ConfigDomain) obj);
		}
		return result;
	}



}
