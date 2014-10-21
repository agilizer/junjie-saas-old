package com.junjie.commons.test.db;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.junjie.commons.db.client.JunjieJdbcTemplate;

@ContextConfiguration(locations = "classpath:spring-camel-test-db-client.xml")
public class DBTestClientMain{
	public static void main(String args[]){
		final Logger log = LoggerFactory.getLogger(DBTestClient.class);
		ApplicationContext contex=new ClassPathXmlApplicationContext("classpath:spring-camel-test-db-client.xml"); 
		final JunjieJdbcTemplate junjieJdbcTemplate = contex.getBean(JunjieJdbcTemplate.class);
		
	}
}
