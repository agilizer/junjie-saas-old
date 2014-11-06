package com.junjie.commons.test.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junjie.commons.utils.JunjieMessageSource;


@ContextConfiguration(locations = "classpath:spring-messagesource-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JunjieMessageSourceTest {
	@Autowired
	JunjieMessageSource junjieMessageSource;
	@Test
	public void test(){
		assertEquals("test.message",junjieMessageSource.getMessage("test.message"));
		assertEquals("我是中文",junjieMessageSource.getMessage("message.china"));
		System.out.println(junjieMessageSource.getMessage("message.china"));
	}

}
