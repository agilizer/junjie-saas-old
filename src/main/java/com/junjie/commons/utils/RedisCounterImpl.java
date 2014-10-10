package com.junjie.commons.utils;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.junjie.commons.test.cache.JunjieRedisCacheTest;

@Service
public class RedisCounterImpl implements JunjieCounter {

	private static final Logger log = LoggerFactory
			.getLogger(JunjieRedisCacheTest.class);
	@Autowired
	private RedisTemplate<String, Long> redisTemplate;

	@PostConstruct
	public void init() {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Long>(
				Long.class));
		redisTemplate.setExposeConnection(true);
		redisTemplate.afterPropertiesSet();
	}

	@Override
	public String genUniqueKey(String counterName) {
		return genUniqueKey(counterName, 1l);
	}

	@Override
	public String genUniqueKey(String counterName, long incrementValue) {
		if(null==counterName||counterName.equals("")){
			log.error("counterName is vaild:"+counterName);
			return null;
		}
		long counter = redisTemplate.boundValueOps(counterName).increment(
				incrementValue)-incrementValue;
		return counterName + counter;
	}

	@Override
	public void reset(String counterName) {
		if(null==counterName||counterName.equals("")){
			log.error("counterName is vaild:"+counterName);
		}else{
			redisTemplate.boundValueOps(counterName).set(0l);
		}
		
	}

}
