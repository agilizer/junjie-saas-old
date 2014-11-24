package com.junjie.commons.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
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
	/**
     * 截取长度文字（charAt在0-125的长度算1，其它包括中文算2），并转义文字编码，超过文字长度的追加...
     * @param str
     * @param length
     * @return
     */
    public static String lenECString(Object obj,int length){
        if(obj==null){return "";}
        String str=obj.toString();
        int len=0;
        int strLen=str.length();
        for(int i=0;i<strLen;i++){
            int p=str.charAt(i);
            if(p>125||p<0){
                len+=2;
            }else {
                len++;
            }
            if(length<len){return str.substring(0, i)+"..";}
        }
        return str;
    }
    public static boolean isBaseObject(Object object) {
		if (object instanceof Integer || object instanceof String
				|| object instanceof Long || object instanceof Boolean ||object instanceof Byte ||object instanceof Double||object instanceof Float
				|| object instanceof Short || object instanceof Calendar) {
			return true;
		} else {
			return false;
		}
	}
    public static boolean isBaseObjectStr(String className) {
		if (className.equals("Integer")||className.equals("String")
				|| className.equalsIgnoreCase("Long")  || className.equalsIgnoreCase("Boolean")   ||className.equalsIgnoreCase("Byte") 
				||className.equalsIgnoreCase("Double")  ||className.equalsIgnoreCase("Float") 
				|| className.equalsIgnoreCase("Short")  || className.equals("Calendar")  ) {
			return true;
		} else {
			return false;
		}
	}
	
}
