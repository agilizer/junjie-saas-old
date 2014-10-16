package com.junjie.commons.db.client;

/**
 * 用于获取当前操作的数据库信息key。
 * @author abel.lee
 *
 */
public interface DataSourceSelecter {
	/**
	 * 获取当前dataSource标识
	 * @return
	 */
	 String getCurrentDataSourceKey();

}
