package com.junjie.commons.db.client;

/**
 * 异步数据库操作回调接口
 * @author abel.lee
 *
 */
public interface JunjieJdbcAsyncCallBack {
	/**
	 * 
	 * @param object数据库返回结果object
	 */
	void call(Object object);
}
