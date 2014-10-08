package com.junjie.test.commons.cache;

import static org.junit.Assert.*;

import org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junjie.commons.cache.JunjieRedisCache;
import com.junjie.commons.cache.JunjieRedisCacheManager;

@ContextConfiguration(locations = "classpath:spring-redis-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JunjieRedisCacheTest {
	private static final Logger log = LoggerFactory
			.getLogger(JunjieRedisCacheTest.class);
	@Autowired
	private JunjieRedisCacheManager junjieRedisCacheManager;
	
	@Test
	public void test() {
		JunjieRedisCache cache = junjieRedisCacheManager.getCache("test");
		assertNotNull(cache);
		String key="testKey",value="testValue";
		cache.put(key, value);
		assertEquals(value,cache.get(key,String.class));
		
		String keyExpire="testKeyExpire";
		cache.put(keyExpire, value,1l);
		assertEquals(value,cache.get(keyExpire,String.class));
	    try {
			Thread.sleep(1000);
			log.info("get testKeyExpire:"+cache.get(keyExpire,String.class));
			assertNull(cache.get(keyExpire,String.class));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
