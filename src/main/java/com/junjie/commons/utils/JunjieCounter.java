package com.junjie.commons.utils;


/**
 * 分布式计数器，用于生成唯一key,
 * 算法，计数器name+redis计数器值。
 * 例如：计数器name=file.计数器当前值为1,则结果为file1.
 * 基于spring4注解配置
 * 注意：计数器name必须唯一，才能保证返回分布式环境下唯一key.
 * @author abel.lee
 */

public interface JunjieCounter {
    
	/**
	 * 获取分布式环境下唯一key
	 * @param counterName 注意：计数器name必须唯一，才能保证返回分布式环境下唯一key.
	 * @return counterName+计数器当前值   
	 * 例如：计数器name=file.计数器当前值为1,则结果为file1.
	 */
    String genUniqueKey(String counterName);
    
    Long genUniqueLong(String counterName);
    Long genUniqueLong(String counterName,long incrementValue);
    /**
     * 
     * 获取分布式环境下唯一key
	 * @param counterName 注意：计数器name必须唯一，才能保证返回分布式环境下唯一key.
	 * @param incrementValue   当半计数器增加值
	 * @return counterName+计数器当前值   
	 * 例如：counterName=file,incrementValue = 5 ,计数器当前值为1,则结果为file6.计数器当前值变为6
     */
    String genUniqueKey(String counterName,long incrementValue);
    /**
     * 重置计数器值为0
     * @param counterName
     */
    void reset(String counterName);
	
}
