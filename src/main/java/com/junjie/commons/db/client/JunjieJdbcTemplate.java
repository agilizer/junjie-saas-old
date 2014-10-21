package com.junjie.commons.db.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.junjie.commons.db.JdbcConstants;
import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.db.JunjieDbOptionBean;

/**
 * 客户端jdbc操作方法，客户端可以自己对这个类进行扩展和方法封装
 * @author abel.lee
 */
@SuppressWarnings("unchecked")
public class JunjieJdbcTemplate implements JunjieJdbcOptions{
	private static final Logger log = LoggerFactory
			.getLogger(JunjieJdbcTemplate.class);
	private String endpointUri;
	private ProducerTemplate producerTemplate;
	private DataSourceSelecter dataSourceSelecter;
	private int retryTimes = 3;
	private long retryInterval =100;

	
	private void genDataSourceKey(JunjieDbOptionBean  optionBean){
		optionBean.setDbInfoKey(dataSourceSelecter.getCurrentDataSourceKey());
	}
	private void  checkException(Object object){
		if(object instanceof Throwable){
			try {
				log.error("jdbc excute error!",(Throwable)object);
				throw new Exception("jdbc excute error");
			} catch (Exception e) {
				log.error("throw exception jdbc excute error!",e);
			}
		}
	}
	@Override
	public JdbcPage queryForList(String sql, String countSql,Map<String, Object> queryParams,
			int max, int offset) {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_COUNT_SQL,countSql);
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		headers.put(JdbcConstants.KEY_MAX,max);
		headers.put(JdbcConstants.KEY_OFFSET,offset);
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		genDataSourceKey(optionBean);
		optionBean.setOption( JdbcConstants.QUERY_FOR_LIST);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result = producerTemplate.requestBody(endpointUri, optionBean);
		checkException(result);
		return (JdbcPage) result;
	}


	@Override
	public Map<String, Object> queryForMap(String sql,
			Map<String, Object> queryParams) throws DataAccessException {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		genDataSourceKey(optionBean);
		optionBean.setOption( JdbcConstants.QUERY_FOR_MAP);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result = producerTemplate.requestBody(endpointUri, optionBean);
		checkException(result);
		return (Map<String, Object>) result;
	}

	@Override
	public int update(String sql, Map<String, Object> queryParams)
			throws DataAccessException {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		genDataSourceKey(optionBean);
		optionBean.setOption( JdbcConstants.UPDATE);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result = null;
		int i = retryTimes;
		for(;i>0;i--){
			try{
				result = producerTemplate.requestBody(endpointUri, optionBean);
				break;
			}catch(Exception e){
				log.error("request db-server  error. retry  "+i +" in " +retryTimes+" , "+ " retryInterval  "+retryInterval,e);
				try {
					Thread.sleep(retryInterval);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(i==0&&result==null){
			log.error("retry "+retryTimes +" still-error!");
			return 0;
		}
		checkException(result);
		return (int)result ;
	}
	@Override
	public int execute(String sql) {
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		optionBean.setOption( JdbcConstants.EXECUTE);
		genDataSourceKey(optionBean);
		Object result = producerTemplate.requestBody(endpointUri, optionBean);
		checkException(result);
		return (int) result;
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
	

}
