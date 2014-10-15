package com.junjie.commons.test.netty;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.junjie.commons.test.rmi.HelloService;

public class NettyTest {
	public final Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void test() throws Exception {
		CamelContext context = new DefaultCamelContext();
		RouteBuilder builder = new RouteBuilder() {
			public void configure() {
				from("netty4:tcp://localhost:5150").process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						Object obj = exchange.getIn().getBody();
						log.info("obj:-->" + obj+Thread.currentThread().getName());
						exchange.getOut().setBody("AAAAAAAAAAAAAAAAAAAAA");
					}
				});
			}
		};
		context.addRoutes(builder);
		context.start();
		final ProducerTemplate template = context.createProducerTemplate();
		log.info("result-->"+template.requestBody("netty4:tcp://localhost:5150","hahahha................."));
		final AtomicLong count = new AtomicLong();
		System.out.println("Lookup service");
		final long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10000; j++) {
					    Object result = 	template.requestBody("netty4:tcp://localhost:5150","hahahha.................");
						log.info("result-->"+result+Thread.currentThread().getName());
						long c = count.incrementAndGet();
						if (c == 99999) {
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
	}
}
