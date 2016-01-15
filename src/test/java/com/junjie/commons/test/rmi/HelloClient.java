/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.junjie.commons.test.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client to invoke the RMI service hosted on another JVM running on localhost.
 *
 * @version
 */
public class HelloClient {
	public final Logger log = LoggerFactory.getLogger(this.getClass());

	public HelloClient() {
		// use Main
	}

	@Test
	public void test() throws RemoteException, NotBoundException {
		System.out.println("Getting registry");
		final Registry registry = LocateRegistry
				.getRegistry("localhost", 37541);
		final AtomicLong count = new AtomicLong();
		System.out.println("Lookup service");
		final long startTime = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						HelloService hello = (HelloService) registry
								.lookup("helloServiceBean");
						for (int j = 0; j < 1000; j++) {
							hello.hello("haha");
							long c = count.incrementAndGet();
							if (c == 99900) {
								log.info("use Time:"
										+ (System.currentTimeMillis() - startTime));

							}
						}

					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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