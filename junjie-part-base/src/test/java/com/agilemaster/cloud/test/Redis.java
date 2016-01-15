package com.agilemaster.cloud.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring-jredis.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class Redis {

	@Autowired
	RedisTemplate redisTemplate;
	@Test
	public void test() {
		redisTemplate.opsForHash().put("aaaa", "foo", "bar");
		System.out.println(redisTemplate.opsForHash().get("aaaa", "foo"));
	}

}
