package com.junjie.commons.test.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junjie.commons.quartz.QuartzService;

@ContextConfiguration(locations = "classpath:spring-test-quartz.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class QuartzTest {
	@Autowired
	QuartzService quartzService;
	@Test
	public void testSend(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
