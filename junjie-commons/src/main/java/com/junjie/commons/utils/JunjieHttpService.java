package com.junjie.commons.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface JunjieHttpService {
	
	JSONObject requestJson(String url,Map<String,String> params);
	JSONObject requestJson(String url,String requestParams);
}
