package com.junjie.commons.db.client;

import java.util.concurrent.Future;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spi.Synchronization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.junjie.commons.db.JdbcConstants;
import com.junjie.commons.db.JunjieDbOptionBean;
/**
 * 数据库操作请求bean发送netty实现
 * @author abel.lee
 */
public class JunjieJdbcNettyRequest implements JunjieJdbcRequest {
	private static final Logger log = LoggerFactory
			.getLogger(JunjieJdbcNettyRequest.class);
	private String endpointUri;
	private ProducerTemplate producerTemplate;
	private DataSourceSelecter dataSourceSelecter;
	private int retryTimes = 3;
	private long retryInterval = 100;

	private void genDataSourceKey(JunjieDbOptionBean optionBean) {
		if(optionBean.getDbInfoKey()==null){
			optionBean.setDbInfoKey(dataSourceSelecter.getCurrentDataSourceKey());
		}
	}

	private void checkException(Object object) {
		if (object instanceof Throwable) {
			try {
				log.error("jdbc excute error!", (Throwable) object);
				throw new Exception("jdbc excute error");
			} catch (Exception e) {
				log.error("throw exception jdbc excute error!", e);
			}
		}
	}

	@Override
	public Object sendJdbcMessageSync(JunjieDbOptionBean optionBean) {
		genDataSourceKey(optionBean);
		Object result = null;
		int i = retryTimes;
		for (; i > 0; i--) {
			try {
				result = producerTemplate.requestBody(endpointUri, optionBean);
				if(result.equals(JdbcConstants.NULL)){
					result = null;
				}
				break;
			} catch (Exception e) {
				log.error("request db-server  error. retry  " + i + " in "
						+ retryTimes + " , " + " retryInterval  "
						+ retryInterval, e);
				try {
					Thread.sleep(retryInterval);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (i == 0 && result == null) {
			log.error("retry " + retryTimes + " still-error!");
			return 0;
		}
		checkException(result);
		return result;
	}
	
	@Override
	public Future<Object>  sendJdbcMessageAsync(JunjieDbOptionBean optionBean,
			final JunjieJdbcAsyncCallBack callBack) {
		genDataSourceKey(optionBean);
		Future<Object> result = null;
		int i = retryTimes;
		for (; i > 0; i--) {
			try {
				  result = producerTemplate.asyncCallbackRequestBody(endpointUri, optionBean,new Synchronization(){
					@Override
					public void onComplete(Exchange exchange) {
						 Object object  = exchange.getOut().getBody();
						 callBack.call(object);
					}
					@Override
					public void onFailure(Exchange exchange) {
						log.warn("request " + endpointUri +" error");
					}
				});
				break;
			} catch (Exception e) {
				log.error("request db-server  error. retry  " + i + " in "
						+ retryTimes + " , " + " retryInterval  "
						+ retryInterval, e);
				try {
					Thread.sleep(retryInterval);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (i == 0 && result == null) {
			log.error("retry " + retryTimes + " still-error!");
		}
		return result;
	}

	public String getEndpointUri() {
		return endpointUri;
	}

	public void setEndpointUri(String endpointUri) {
		this.endpointUri = endpointUri;
	}

	public ProducerTemplate getProducerTemplate() {
		return producerTemplate;
	}

	public void setProducerTemplate(ProducerTemplate producerTemplate) {
		this.producerTemplate = producerTemplate;
	}

	public DataSourceSelecter getDataSourceSelecter() {
		return dataSourceSelecter;
	}

	public void setDataSourceSelecter(DataSourceSelecter dataSourceSelecter) {
		this.dataSourceSelecter = dataSourceSelecter;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	public long getRetryInterval() {
		return retryInterval;
	}

	public void setRetryInterval(long retryInterval) {
		this.retryInterval = retryInterval;
	}
}
