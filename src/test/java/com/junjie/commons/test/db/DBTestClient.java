package com.junjie.commons.test.db;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junjie.commons.db.client.JunjieJdbcTemplate;


@Service
public class DBTestClient {
	final Logger log = LoggerFactory.getLogger(DBTestClient.class);
	@Autowired
	private JunjieJdbcTemplate junjieJdbcTemplate;
	private int threads = 100;
	private int singleThreadTimes = 100;
	private long mainThreadSleep = 100000;
	public void doTest(){
		Map<String,Object> insertMap = new HashMap<String,Object>();
		for(int i=0;i<1000;i++){
			junjieJdbcTemplate.update("insert into sys_organization(name,parent_id,parent_ids,available) values('总公司"+i+"', 0, '0/', true);",null);
		}
		final AtomicLong count = new AtomicLong();
		final long startTime = System.currentTimeMillis();
		log.info("before insert:"+junjieJdbcTemplate.queryForMap("select count(0) from sys_organization", null));
		for (int i = 0; i < threads; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < singleThreadTimes; j++) {
						junjieJdbcTemplate.update("insert into sys_organization(name,parent_id,parent_ids,available) values('总公司"+j+"', 0, '0/', true);",null);
						long c = count.incrementAndGet();
						if (c == (threads*singleThreadTimes-5)) {
							log.info("use Time:"
									+ (System.currentTimeMillis() - startTime));
							log.info("after insert:"+junjieJdbcTemplate.queryForMap("select count(0) from sys_organization", null));
						}
					}
				}
			}).start();
		}
		try {
			Thread.sleep(mainThreadSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
