package com.junjie.commons.test.camel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelClient {
	public final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void test() throws Exception {
		System.out
				.println("Notice this client requires that the CamelServer is already running!");

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-camel-test-client.xml");

		// get the camel template for Spring template style sending of messages
		// (= producer)
		final ProducerTemplate camelTemplate = context.getBean("camelTemplate",
				ProducerTemplate.class);

		System.out.println("Invoking the multiply with 22");
		// as opposed to the CamelClientRemoting example we need to define the
		// service URI in this java code
		final AtomicLong count = new AtomicLong();
		System.out.println("Lookup service");
		final long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						int response = (Integer) camelTemplate.sendBody("jms:queue:numbers",
								ExchangePattern.InOut, 22);
					//	System.out.println("... the result is: " + response);
						long c = count.incrementAndGet();
						if (c == 999) {
							log.info("use Time:"
									+ (System.currentTimeMillis() - startTime));
						}
					}
				}
			}).start();
		}
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		System.exit(0);
	}
}
