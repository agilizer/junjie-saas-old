package com.agilemaster.cloud.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.agilemaster.cloud.util.DateUtil;
@Service
public class SmsServiceImpl implements SmsService {
	//sms企业id
	private String cropId="TCLKJ00460";
	//sms密码
	private String passwd="85723845";
	private Log log = LogFactory.getLog(SmsServiceImpl.class);
	public int sendSMS(String phoneNumber, String content) {
		 return doSendSMS(phoneNumber,content,"");
	}
	public int sendSMS(String phoneNumber, String content, Date sendTime) {
	    return doSendSMS(phoneNumber,content,DateUtil.format(sendTime, "yyyyMMddhhmmss"));
		
	}

	public int sendSMS(String phoneNumber,String content, Calendar sendTime) {
		return doSendSMS(phoneNumber,content,DateUtil.format(sendTime.getTime(), "yyyyMMddhhmmss"));
		
	}
	private int doSendSMS(String phoneNumber, String content, String sendTime){
		URL url = null;
		int inputLine = 1;
		try {
			String send_content=URLEncoder.encode(content.replaceAll("<br/>", " "), "GBK");//发送内容
			url = new URL("http://inolink.com/WS/Send.aspx?CorpID="+cropId+"&Pwd="+passwd+"&Mobile="+phoneNumber+"&Content="+send_content+"&Cell=&SendTime="+sendTime);
			BufferedReader in = null;
			log.info("开始发送短信手机号码为 ："+phoneNumber);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Integer(in.readLine()).intValue();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			log.info("网络异常,发送短信失败！");
			inputLine=-9;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("结束发送短信返回值：  "+inputLine);
		return inputLine;
	}

	public String getCropId() {
		return cropId;
	}

	public void setCropId(String cropId) {
		this.cropId = cropId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
