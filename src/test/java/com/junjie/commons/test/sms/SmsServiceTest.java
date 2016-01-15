package com.junjie.commons.test.sms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junjie.commons.sms.SmsService;


@ContextConfiguration(locations = "classpath:spring-test-sms.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SmsServiceTest {
	
	@Autowired
	private SmsService smsService;
	
	@Test
	public void testSend(){
		smsService.sendSMS("18190910296", "李先生 郭总发起任务 参加中国石油总医院开工仪式");
	}
	
	
	@Test
	public void testUuid() throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:9000/cloud/api/v1/sms/send.do");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("key", "2d18f4fa-fdcb-491c-a030-bb2f1154699c"));
		nvps.add(new BasicNameValuePair("phoneNumbers", "18190910296"));
		nvps.add(new BasicNameValuePair("content", "李先生 郭总发起任务 参加中国石油总医院开工仪式"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps,Consts.UTF_8));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);        
		try {
		    System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity2);
		} finally {
		    response2.close();
		}
	}
	
	

}
