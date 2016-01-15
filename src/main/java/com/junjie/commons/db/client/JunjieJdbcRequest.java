package com.junjie.commons.db.client;

import java.util.concurrent.Future;

import com.junjie.commons.db.JunjieDbOptionBean;
/**
 * 发送数据库操作bean到数据库服务器。
 * @author abel.lee
 *
 */
public interface JunjieJdbcRequest {
	/**
	 * 
	 * @param optionBean 操作bean描述类
	 * @return
	 */
	Object sendJdbcMessageSync(JunjieDbOptionBean optionBean);
	
	/**
	 * 
	 * @param optionBean操作bean描述类
	 * @param callBack 回调实现
	 * @return
	 */
	Future<Object> sendJdbcMessageAsync(JunjieDbOptionBean optionBean,JunjieJdbcAsyncCallBack callBack);
	/**
	 * 获取因网络异常重试次数
	 * @return
	 */
	public int getRetryTimes();
	/**
	 * 设置因网络异常重试次数
	 * @return
	 */
	public void setRetryTimes(int retryTimes) ;
	/**
	 * 获取camel endPointUrl
	 * @return
	 */
	public String getEndpointUri();
	/**
	 * 设置camel  endPointUrl
	 * @param endpointUri
	 */
	public void setEndpointUri(String endpointUri) ;
	/**
	 * 获取因网络异常重试间隔时间 ,毫秒
	 * @return
	 */
	public long getRetryInterval();
	/**
	 * 设置因网络异常重试间隔时间 ,毫秒
	 * @param retryInterval
	 */
	public void setRetryInterval(long retryInterval) ;
	
	/**
	 * 获取当前会话数据库信息key实现类
	 * @return
	 */
	public DataSourceSelecter getDataSourceSelecter();

	/**
	 * 设置当前会话数据库信息key实现类
	 * @return
	 */
	public void setDataSourceSelecter(DataSourceSelecter dataSourceSelecter) ;
	
}
