package com.junjie.commons.db.server;

import java.util.Map;

/**
 * 数据库请求服务监听接口,注意，自定义操作时，对应的操作key，需要大于20，20以下的key，留做做扩展用。
 * @author abel.lee
 *
 */
public interface JunjieJdbcRequestListener {
	Map<Integer,JunjieJdbcRequest> getMethodsMap();
	
	/**
	 * 
	 * @param key   key值为自定义实现时需要大于20
	 * @param method
	 */
	void addMethod(Integer key,JunjieJdbcRequest method);

}
