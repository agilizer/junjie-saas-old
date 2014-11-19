package com.junjie.commons.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class JunjieStaticMethod {
	public static Map<String,Object> genResult(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(JunjieConstants.SUCCESS, false);
		return result;
	}
	public static String getStreamString(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(
						new InputStreamReader(tInputStream));
				StringBuffer tStringBuffer = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);
				}
				return tStringBuffer.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	public static boolean genBooleanValue( HttpServletRequest request,String paramName){
		boolean result = false;
		String[] valueArray = request.getParameterValues(paramName);
		if(valueArray!=null){
			result = Boolean.parseBoolean(valueArray[0]);
			if (valueArray.length == 2) {
				result = Boolean.TRUE;
			}
		}
		return result;
		 
	}
}
