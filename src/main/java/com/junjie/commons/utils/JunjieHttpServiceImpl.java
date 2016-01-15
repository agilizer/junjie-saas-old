package com.junjie.commons.utils;

import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class JunjieHttpServiceImpl implements JunjieHttpService {
	private static final Logger log = LoggerFactory
			.getLogger(JunjieHttpServiceImpl.class);
	@Autowired
	private ProducerTemplate producerTemplate;
	private int retryTimes = 3;

	@Override
	public JSONObject requestJson(final String url, final String requestParams) {
		int responseCode = 0;
		int requestTime = 3;
		String resultJson = "";
		String camelUrl = url.replace("http", "http4");
		while (requestTime > 0) {
			Exchange exchange = producerTemplate.send(camelUrl, new Processor() {
				public void process(Exchange exchange) throws Exception {
					Message in = exchange.getIn();
					in.setHeader(Exchange.HTTP_QUERY, requestParams);
					log.info("requestJson url {}{}", url, requestParams);
					in.setHeader(Exchange.HTTP_METHOD,
							org.apache.camel.component.http4.HttpMethods.POST);
					in.setHeader(Exchange.CONTENT_ENCODING, "utf-8");
				}
			});
			Message out = exchange.getOut();
			responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE,
					Integer.class);
			if (responseCode == HttpStatus.SC_OK) {
				InputStream stream = (InputStream) out.getBody();
				resultJson = JunjieStaticMethod.getStreamString(stream);
				requestTime = 0;
				break;
			}
			if (responseCode == HttpStatus.SC_NOT_FOUND) {
				if (responseCode == HttpStatus.SC_NOT_FOUND) {
					log.warn("requestJson url: {}  not found!!!!", url);
				}
				requestTime = 0;
				break;
			}
			requestTime--;
		}
		JSONObject object = null;
		if (!resultJson.equals("")) {
			object = JSON.parseObject(resultJson);
		} else {
			log.warn("requestJson url: {}  reponse code {}!!!!", url,
					responseCode);
		}
		return object;
	}
	private String genRequestParamsFormMap(Map<String, String> params) {
		String queryString = "";
		if (params != null) {
			queryString = "a=a";
			for (Entry<String, String> entry : params.entrySet()) {
				queryString = "&" + entry.getKey() + "=" + entry.getValue();
			}
			queryString = queryString.replace("a=a&", "");
		}
		return queryString;
	}

	@Override
	public JSONObject requestJson(final String url,
			final Map<String, String> params) {
		String requestParams = genRequestParamsFormMap(params);
		return requestJson(url,requestParams);
	}

	public ProducerTemplate getProducerTemplate() {
		return producerTemplate;
	}

	public void setProducerTemplate(ProducerTemplate producerTemplate) {
		this.producerTemplate = producerTemplate;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

}
