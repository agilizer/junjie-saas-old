package com.agilemaster.partbase.util;

import java.util.Map;

import com.agilemaster.partbase.entity.ConfigDomain;

public class BaseUtil {
	public static Map<String,Object> addToConfigMap(Map<String,Object> configMap, ConfigDomain configDomain) {
        if (configDomain.getValueType() == ConfigDomain.ValueType.Integer) {
            configMap.put(configDomain.getMapName(), Integer.parseInt(configDomain.getMapValue()));
        } else if(configDomain.getValueType() == ConfigDomain.ValueType.Boolean){
             if(configDomain.getMapValue()=="true"){
                 configMap.put(configDomain.getMapName(), true);
             }else{
                 configMap.put(configDomain.getMapName(), false);
             }
        } else {
            configMap.put(configDomain.getMapName(), configDomain.getMapValue());
        }
        return configMap;
    }

}
