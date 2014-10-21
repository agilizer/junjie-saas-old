package com.junjie.commons.test.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring-camel-test-db-server.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DBTestServer {
	public  final Logger log = LoggerFactory
			.getLogger(this.getClass());
	@Test
	public void test(){
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
