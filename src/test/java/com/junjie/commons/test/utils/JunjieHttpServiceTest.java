package com.junjie.commons.test.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.junjie.commons.utils.JunjieHttpService;

@ContextConfiguration(locations = "classpath:spring-httpservice-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JunjieHttpServiceTest {
	@Autowired
	JunjieHttpService junjieHttpService;
	
	@Test
	public void test(){
		JSONObject result = junjieHttpService.requestJson("http://gc.ditu.aliyun.com/geocoding", "a=成都市");
		System.out.println(result.get("lon"));
	}
}
