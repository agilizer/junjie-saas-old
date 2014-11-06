package com.junjie.commons.utils;

import java.util.HashMap;
import java.util.Map;

public class JunjieStaticMethod {
	public static Map<String,Object> genResult(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(JunjieConstants.SUCCESS, false);
		return result;
	}
}
