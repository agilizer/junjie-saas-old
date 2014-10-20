package com.junjie.commons.test.db;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junjie.commons.db.client.JunjieJdbcTemplate;
import com.junjie.commons.test.rmi.HelloService;

@ContextConfiguration(locations = "classpath:spring-camel-test-db.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DBTest {
	public  final Logger log = LoggerFactory
			.getLogger(this.getClass());
	@Autowired
	private JunjieJdbcTemplate junjieJdbcTemplate;
	@Test
	public void test(){
		Map<String,Object> insertMap = new HashMap<String,Object>();
		for(int i=0;i<1000;i++){
			junjieJdbcTemplate.update("insert into sys_organization(name,parent_id,parent_ids,available) values('总公司"+i+"', 0, '0/', true);",null);
		}
		final AtomicLong count = new AtomicLong();
		final long startTime = System.currentTimeMillis();
		log.info("before insert:"+junjieJdbcTemplate.queryForMap("select count(0) from sys_organization", null));
		for (int i = 0; i < 200; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						junjieJdbcTemplate.update("insert into sys_organization(name,parent_id,parent_ids,available) values('总公司"+j+"', 0, '0/', true);",null);
						long c = count.incrementAndGet();
						if (c == 19999) {
							log.info("use Time:"
									+ (System.currentTimeMillis() - startTime));
							log.info("after insert:"+junjieJdbcTemplate.queryForMap("select count(0) from sys_organization", null));
						}
					}
				}
			}).start();
		}
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
