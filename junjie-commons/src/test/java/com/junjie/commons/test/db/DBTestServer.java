package com.junjie.commons.test.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.junjie.commons.db.client.JunjieJdbcTemplate;

public class DBTestServer {
	public  final Logger log = LoggerFactory
			.getLogger(this.getClass());
	public static void main(String args[]){
		final Logger log = LoggerFactory.getLogger(DBTestClient.class);
		ApplicationContext contex=new ClassPathXmlApplicationContext("classpath:spring-camel-test-db-server.xml"); 
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
