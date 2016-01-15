package com.junjie.commons.db.server;

import com.junjie.commons.db.JunjieDbOptionBean;

/**
 * 数据库操作实现类。
 * @author abel.lee
 *
 */
public interface JunjieJdbcRequest {
	
	/**
	 * 处理数据库操作请求
	 * @param optionBean
	 * @return
	 */
	Object  doWith(JunjieDbOptionBean optionBean);
	/**
	 * 获取当前操作实现的描述
	 * @return
	 */
    String getDescprition();
    
}
