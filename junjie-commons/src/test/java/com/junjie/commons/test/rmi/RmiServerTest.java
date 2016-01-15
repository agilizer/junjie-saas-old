package com.junjie.commons.test.rmi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring-camel-test-rmi.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RmiServerTest  {
	
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
