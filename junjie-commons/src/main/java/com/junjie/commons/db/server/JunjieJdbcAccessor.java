package com.junjie.commons.db.server;

import javax.sql.DataSource;

/**
 * 根据dbInfokey，获取数据库连接信息
 * @author abel.lee
 *
 */
public interface JunjieJdbcAccessor {
	/** 
	 * 根据dbInfokey，获取数据库连接信息
	 * @param dbInfoKey
	 * @return
	 */
	DataSource genDataSource(String dbInfoKey);
}
