package com.junjie.commons.test.utils;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.junjie.commons.test.BaseTest;
import com.junjie.commons.utils.JunjieCounter;


public class JunjieCounterTest extends BaseTest{

	@Autowired
	private JunjieCounter junjieCounter;
	@Test
	public void test() {
		String counterName = "testCounterName";
		junjieCounter.reset(counterName);
		assertEquals(counterName+0,junjieCounter.genUniqueKey(counterName));
		assertEquals(counterName+1,junjieCounter.genUniqueKey(counterName));
		junjieCounter.genUniqueKey(counterName, 5);
		assertEquals(counterName+7,junjieCounter.genUniqueKey(counterName));
	}
	@Test
	public void testMutilThread() {
		final Map<String,String>  storeCounter = new ConcurrentHashMap<String, String>();
		final String counterName = "testCounterName";
		junjieCounter.reset(counterName);
		for(int i=0;i<10;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					for(int j=0;j<10;j++){
						String counterTemp = junjieCounter.genUniqueKey(counterName);
						assertNull(storeCounter.get(counterTemp));	
						log.info(counterTemp);
						storeCounter.put(counterTemp, counterTemp);
					}
				}
				
			}).start();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
