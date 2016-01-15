package com.junjie.commons.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring-redis-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
	public  final Logger log = LoggerFactory
			.getLogger(this.getClass());
}
