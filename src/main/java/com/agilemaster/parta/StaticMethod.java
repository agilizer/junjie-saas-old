package com.agilemaster.parta;

import java.util.HashMap;
import java.util.Map;

public class StaticMethod {
	public static Map<String,Object> genResult(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constants.SUCCESS, false);
		return result;
	}

}
